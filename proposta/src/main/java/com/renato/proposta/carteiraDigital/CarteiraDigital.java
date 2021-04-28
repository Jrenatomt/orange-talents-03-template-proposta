package com.renato.proposta.carteiraDigital;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import com.renato.proposta.solicitacaoCartao.Cartao;

@Entity
public class CarteiraDigital {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private @Email String email;
	@NotBlank
	private String idAssociacao;
	@NotNull @PastOrPresent
	private LocalDateTime associadaEm = LocalDateTime.now();
	@Enumerated(EnumType.STRING)
	private @NotNull Carteira carteira;
	@ManyToOne
	private @Valid @NotNull Cartao cartao;
	
	@Deprecated
	public CarteiraDigital() {
	}

	public CarteiraDigital(@Email String email, @NotBlank String idAssociacao, @NotNull Carteira carteira, @Valid @NotNull Cartao cartao) {
		this.email = email;
		this.idAssociacao = idAssociacao;
		this.carteira = carteira;
		this.cartao = cartao;
		
	}

	public Long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public LocalDateTime getAssociadaEm() {
		return associadaEm;
	}

	public Carteira getCarteiras() {
		return carteira;
	}

	public Cartao getCartao() {
		return cartao;
	}
	
	public String getIdAssociacao() {
		return idAssociacao;
	}
}
