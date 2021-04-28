package com.renato.proposta.carteiraDigital;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.renato.proposta.solicitacaoCartao.Cartao;
import com.renato.proposta.solicitacaoCartao.CartaoRepository;
import com.renato.proposta.solicitacaoCartao.IntegracaoCartaoCliente;

import feign.FeignException;

@RestController
@RequestMapping(value = "/api/cartoes")
public class CarteiraDigitalController {

	private final CartaoRepository cartaoRepository;
	private final CarteiraDigitalRepository carteiraRepository;
	private final IntegracaoCartaoCliente cartaoCliente;

	public CarteiraDigitalController(CartaoRepository cartaoRepository, CarteiraDigitalRepository carteiraRepository,
			IntegracaoCartaoCliente cartaoCliente) {
		this.cartaoRepository = cartaoRepository;
		this.carteiraRepository = carteiraRepository;
		this.cartaoCliente = cartaoCliente;
	}

	@PostMapping(value = "/{idCartao}/carteiras")
	public ResponseEntity<?> cadastraCarteira(@PathVariable Long idCartao,
			@RequestBody @Valid CarteiraDigitalRequest request) {

		Cartao cartao = cartaoRepository.findById(idCartao)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cartão não encontrado"));

		if (cartao.verificaSeEstaAssociado(request.getCarteira())) {
			throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "cartão já associado");
		}

		try {
			CarteiraDigitalResponse response = cartaoCliente.associarCarteira(cartao.getNumeroCartao(), request);

			if (response.getResultado().equals("ASSOCIADA")) {
				
				CarteiraDigital novaCarteira = request.toModel(cartao, response.getIdAssociacao());
				carteiraRepository.save(novaCarteira);
				
				URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
						.buildAndExpand(novaCarteira.getId()).toUri();

				return ResponseEntity.created(uri).build();
			}
			throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Erro ao associar o cartão");

		} catch (FeignException e) {
			throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Erro na api, tente novamente");
		}
	}
}