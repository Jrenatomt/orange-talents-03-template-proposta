package com.renato.proposta.cadastraProposta;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.renato.proposta.analisaProposta.AnalisaCliente;

@RestController
@RequestMapping(value = "/api/propostas")
public class PropostaController {

	@Autowired
	private PropostaRepository repository;
	@Autowired
	private AnalisaCliente analiseCliente;

	@PostMapping
	@Transactional
	public ResponseEntity<?> criaProposta(@RequestBody @Valid PropostaRequest request) {
		Proposta novaProposta = request.toModel();
		
		repository.save(novaProposta);
		
		novaProposta.analisaProposta(analiseCliente);
       System.out.println("cadastrando");
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(novaProposta.getId()).toUri();

		return ResponseEntity.created(uri).build();
	}
	
	@GetMapping(value = "/{idProposta}")
	public ResponseEntity<PropostaResponse> detalheProposta(@PathVariable Long idProposta){
		Optional<Proposta> proposta = repository.findById(idProposta);
		if(!proposta.isPresent()) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok().body(new PropostaResponse(proposta.get()));	
	}
}
