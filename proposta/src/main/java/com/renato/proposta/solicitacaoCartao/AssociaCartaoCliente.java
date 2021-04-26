package com.renato.proposta.solicitacaoCartao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.renato.proposta.cadastraProposta.Proposta;
import com.renato.proposta.cadastraProposta.PropostaRepository;
import com.renato.proposta.cadastraProposta.StatusProposta;

@Component
public class AssociaCartaoCliente {

	@Autowired
	private PropostaRepository propostaRepository;
	
	@Autowired
	private IntegracaoCartaoCliente solicitaCartaoCliente;

	@Scheduled(fixedDelayString = "${timing.fixedDelay}", initialDelayString = "${timing.initialDelay}")
	public void salvaCartao() {
		System.out.println("iniciando");
		List<Proposta> listaPropostaSemCartao = propostaRepository.findAllByStatusAndCartaoIsNull(StatusProposta.ELEGIVEL);

		for (Proposta proposta : listaPropostaSemCartao) {
			SolicitaCartaoResponse response = solicitaCartaoCliente.solicitaCartao(new SolicitaCartaoRequest(proposta));
			Cartao cartao = response.toModel(proposta);
			System.out.println(cartao);
			proposta.adicionaCartao(cartao);
			propostaRepository.save(proposta);
			
			System.out.println("Executando minha operação");
		}
	}
}