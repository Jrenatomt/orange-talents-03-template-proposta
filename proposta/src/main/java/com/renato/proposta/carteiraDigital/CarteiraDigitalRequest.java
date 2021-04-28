package com.renato.proposta.carteiraDigital;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.renato.proposta.solicitacaoCartao.Cartao;

public class CarteiraDigitalRequest {
	
	@Email
	@NotBlank
	private String email;
	@NotNull
	private Carteira carteira;
	
	@Deprecated
	public CarteiraDigitalRequest() {
	}
	
	public CarteiraDigitalRequest(@Email String email, @NotNull Carteira carteira) {
		this.email = email;
		this.carteira = carteira;
	}

	public String getEmail() {
		return email;
	}

	public Carteira getCarteira() {
		return carteira;
	}

	public CarteiraDigital toModel(Cartao cartao, String idAssociacao) {
		return new CarteiraDigital(email, idAssociacao, carteira, cartao);
	}
}
