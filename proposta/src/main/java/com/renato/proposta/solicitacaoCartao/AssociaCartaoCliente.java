package com.renato.proposta.solicitacaoCartao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.renato.proposta.cadastraProposta.Proposta;
import com.renato.proposta.cadastraProposta.PropostaRepository;
import com.renato.proposta.cadastraProposta.StatusProposta;

import feign.FeignException;

@Component
public class AssociaCartaoCliente {

	@Autowired
	private PropostaRepository propostaRepository;
	
	@Autowired
	private IntegracaoCartaoCliente solicitaCartaoCliente;

	@Scheduled(fixedDelayString = "${timing.fixedDelay}", initialDelayString = "${timing.initialDelay}")
	public void salvaCartao() {
		List<Proposta> listaPropostaSemCartao = propostaRepository.findAllByStatusAndCartaoIsNull(StatusProposta.ELEGIVEL);

		try {
		for (Proposta proposta : listaPropostaSemCartao) {
			SolicitaCartaoResponse response = solicitaCartaoCliente.solicitaCartao(new SolicitaCartaoRequest(proposta));
			Cartao cartao = response.toModel(proposta);
			System.out.println(cartao);
			proposta.adicionaCartao(cartao);
			propostaRepository.save(proposta);	
		}
		}catch (FeignException e) {
			throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Erro na api de Análise");
		}
	}
}