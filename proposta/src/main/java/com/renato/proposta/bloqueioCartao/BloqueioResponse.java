package com.renato.proposta.bloqueioCartao;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.renato.proposta.solicitacaoCartao.StatusCartao;

public class BloqueioResponse {

	private String resultado;

	@Deprecated
	public BloqueioResponse() {
	}

	@JsonCreator
	public BloqueioResponse(String resultado) {
		this.resultado = resultado;
	}

	public String getResultado() {
		return resultado;
	}

	public StatusCartao atualizaStatusCartao() {
		if (resultado.equals("BLOQUEADO")) {
			return StatusCartao.BLOQUEADO;
		}
		return StatusCartao.ATIVO;
	}
}