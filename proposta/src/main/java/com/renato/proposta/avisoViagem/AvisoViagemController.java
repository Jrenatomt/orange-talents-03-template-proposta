package com.renato.proposta.avisoViagem;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.renato.proposta.solicitacaoCartao.Cartao;
import com.renato.proposta.solicitacaoCartao.CartaoRepository;
import com.renato.proposta.solicitacaoCartao.IntegracaoCartaoCliente;

import feign.FeignException;

@RestController
@RequestMapping(value = "/api/cartoes")
public class AvisoViagemController {
	
	private final AvisoViagemRepository repository;
	private final CartaoRepository cartaoRepository;
	private final IntegracaoCartaoCliente integracaoCartaoCliente;
	
	public AvisoViagemController(AvisoViagemRepository repository, 
			CartaoRepository cartaoRepository, 
			IntegracaoCartaoCliente integracaoCartaoCliente) {
		this.repository = repository;
		this.cartaoRepository = cartaoRepository;
		this.integracaoCartaoCliente = integracaoCartaoCliente;
	}

	@PostMapping(value = "/{idCartao}/avisos")
	public ResponseEntity<?> avisarViagem(@PathVariable Long idCartao, HttpServletRequest infoRequest,
			@RequestHeader(HttpHeaders.USER_AGENT) String userAgent, @RequestBody @Valid AvisoViagemRequest request) {

		String ipCliente = infoRequest.getRemoteAddr();

		if (userAgent.isEmpty()) {
			System.out.println("user agente não achando");
			return ResponseEntity.badRequest().build();
		}

		if (!StringUtils.hasText(ipCliente)) {
			System.out.println("ip nao encontrado");
			return ResponseEntity.badRequest().build();

		}
		
		Cartao cartao = cartaoRepository.findById(idCartao)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cartão não encontrado"));
		
		try {
		AvisoViagem novoAvisoViagem = request.toModel(cartao, ipCliente, userAgent);
		AvisoViagemResponse response = integracaoCartaoCliente.avisarViagem(cartao.getNumeroCartao(), novoAvisoViagem.toRequest());
		if(response.getResultado().equals("CRIADO"));
		repository.save(novoAvisoViagem);
		System.out.println("chegou aqui!!!");
		}catch(FeignException e) {
			throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Erro na API de avisos");
		}
		return ResponseEntity.ok().build();
	}
}
