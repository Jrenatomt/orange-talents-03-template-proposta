package com.renato.proposta.bloqueioCartao;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.util.Assert;

import com.renato.proposta.solicitacaoCartao.Cartao;

@Entity
public class BloqueioCartao {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    @NotNull @OneToOne 
	private Cartao cartao;
	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private LocalDateTime instanteBloqueio;
	@NotBlank
	private String ipCliente;
	@NotBlank
	private String userAgent;
	
	public BloqueioCartao() {
	}

	public BloqueioCartao(@NotNull @Valid Cartao cartao, @NotBlank String ipCliente, @NotBlank String userAgent) {
		this.cartao = cartao;
		this.ipCliente = ipCliente;
		this.userAgent = userAgent;
        Assert.notNull(cartao, "Cartão é obrigatório!");
        Assert.hasLength(ipCliente, "Ip do cliente é obrigatório!");
        Assert.hasLength(userAgent, "UserAgent do usuário é obrigatório!");
	}

	public Long getId() {
		return id;
	}

	public Cartao getCartao() {
		return cartao;
	}

	public LocalDateTime getInstanteBloqueio() {
		return instanteBloqueio;
	}

	public String getIpCliente() {
		return ipCliente;
	}

	public String getUserAgente() {
		return userAgent;
	}
	
	@PrePersist
	public void prePersist() {
		instanteBloqueio = LocalDateTime.now();
	}
}
