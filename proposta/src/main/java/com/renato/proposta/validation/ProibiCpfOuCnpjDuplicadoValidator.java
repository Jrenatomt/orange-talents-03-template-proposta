package com.renato.proposta.validation;

import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.renato.proposta.cadastraProposta.Proposta;
import com.renato.proposta.cadastraProposta.PropostaRepository;
import com.renato.proposta.cadastraProposta.PropostaRequest;

public class ProibiCpfOuCnpjDuplicadoValidator implements ConstraintValidator<ProibiCpfOuCnpjDuplicadoValid, PropostaRequest> {

	@Autowired
	private PropostaRepository repository;

	@Override
	public void initialize(ProibiCpfOuCnpjDuplicadoValid ann) {
	}

	@Override
	public boolean isValid(PropostaRequest request, ConstraintValidatorContext context) {
		
		Optional<Proposta> documentoExistente = repository.findByHashDocumento(DigestUtils.sha256Hex(request.getDocumento()));

		if (documentoExistente.isPresent()) {
			throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Já existe uma proposta em andamento para este cliente");
		}
		return true;
	}
}
