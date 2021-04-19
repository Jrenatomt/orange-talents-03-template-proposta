package com.renato.proposta.cadastroBiometria;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.renato.proposta.solicitacaoCartao.Cartao;

public class BiometriaRequest {
	
    @NotBlank
	private String impressaoDigital;

    @Deprecated
	public BiometriaRequest() {
	}
	
	@JsonCreator
	public BiometriaRequest(@NotBlank String impressaoDigital) {
		this.impressaoDigital = impressaoDigital;
	}
	
	public String getImpressaoDigital() {
		return impressaoDigital;
	}

	public Biometria toModel(@Valid Cartao cartao) {
		return new Biometria(impressaoDigital, cartao);
	}
}
