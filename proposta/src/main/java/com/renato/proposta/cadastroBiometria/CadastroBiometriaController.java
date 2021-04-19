package com.renato.proposta.cadastroBiometria;

import java.net.URI;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.renato.proposta.solicitacaoCartao.Cartao;
import com.renato.proposta.solicitacaoCartao.CartaoRepository;

@RestController
@RequestMapping(value = "/api/cartoes")
public class CadastroBiometriaController {
	
	@Autowired
	private CartaoRepository cartaoRepository;
	@Autowired
	private BiometriaRepository biometriaRepository;
	
	@Transactional
	@PostMapping(value = "/{idCartao}/biometrias")
	public ResponseEntity<?> criaBiometria(@PathVariable Long idCartao,@RequestBody @Valid BiometriaRequest request ){
		Cartao cartao = cartaoRepository.findById(idCartao).get();
		if(cartao == null) {
			return ResponseEntity.badRequest().build();
		}
		
        Biometria NovaBiometria = request.toModel(cartao);
        biometriaRepository.save(NovaBiometria);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(NovaBiometria.getId()).toUri();

		return ResponseEntity.created(uri).build();	
	}
}
