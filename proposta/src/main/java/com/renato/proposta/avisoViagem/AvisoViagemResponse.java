package com.renato.proposta.avisoViagem;

import com.fasterxml.jackson.annotation.JsonCreator;

public class AvisoViagemResponse {
	
	private String resultado;

	@JsonCreator
	public AvisoViagemResponse(String resultado) {
		this.resultado = resultado;
	}

	public String getResultado() {
		return resultado;
	}
}
