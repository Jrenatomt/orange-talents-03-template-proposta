package com.renato.proposta.bloqueioCartao;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonCreator;

public class BloqueioRequest {
	
	@NotBlank
	private String sistemaResponsavel;
	
	@Deprecated
	public BloqueioRequest() {
    }

	@JsonCreator
    public BloqueioRequest(@NotBlank String sistemaResponsavel) {
        this.sistemaResponsavel = sistemaResponsavel;
    }

    public String getSistemaResponsavel() {
        return sistemaResponsavel;
    }
}
