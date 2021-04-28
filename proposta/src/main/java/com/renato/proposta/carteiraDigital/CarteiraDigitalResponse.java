package com.renato.proposta.carteiraDigital;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CarteiraDigitalResponse {
	
	private String resultado;
	@JsonProperty("id")
	private String idAssociacao;
	
	@Deprecated
	public CarteiraDigitalResponse() {
	}

	@JsonCreator
	public CarteiraDigitalResponse(String resultado, String idAssociacao ) {
		this.resultado = resultado;
		this.idAssociacao = idAssociacao;
	}

	public String getResultado() {
		return resultado;
	}

	public String getIdAssociacao() {
		return idAssociacao;
	}
}
