package com.renato.proposta.cadastroBiometria;

import java.time.LocalDateTime;
import java.util.Base64;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.util.Assert;

import com.renato.proposta.solicitacaoCartao.Cartao;

@Entity(name = "tb_biometria")
public class Biometria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private @NotBlank String impressaoDigital;
	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private LocalDateTime criadoEm;
	@ManyToOne
	private @Valid Cartao cartao;

	@Deprecated
	public Biometria() {
	}

	public Biometria(@NotBlank String impressaoDigital, @Valid Cartao cartao) {
		this.impressaoDigital = Base64.getEncoder().encodeToString(impressaoDigital.getBytes());
		this.cartao = cartao;
		Assert.hasLength(impressaoDigital, "Impressão Digital é obrigatória!");
		Assert.notNull(cartao, "Cartão é obrigatório!");
	}

	public Long getId() {
		return id;
	}

	public String getImpressaoDigital() {
		return impressaoDigital;
	}

	public Cartao getCartao() {
		return cartao;
	}
	
	@PrePersist
	public void prePersist() {
		criadoEm = LocalDateTime.now();
	}
}
