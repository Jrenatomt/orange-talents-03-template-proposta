package com.renato.proposta.avisoViagem;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import com.renato.proposta.solicitacaoCartao.Cartao;

@Entity
public class AvisoViagem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private @NotBlank String destino;
	private @NotNull @Future LocalDate terminoViagem;
	private @NotBlank String ipCliente;
	private @NotBlank String userAgent;
	@NotNull @PastOrPresent
	private LocalDateTime instateAvisoViagem = LocalDateTime.now();
	@ManyToOne
	private @NotNull @Valid Cartao cartao;
	
	@Deprecated
	public AvisoViagem() {
	}

	public AvisoViagem( @NotBlank String destino, @NotNull @Future LocalDate terminoViagem,
			@NotNull @Valid Cartao cartao, @NotBlank String ipCliente, @NotBlank String userAgent) {
		this.destino = destino;
		this.terminoViagem = terminoViagem;
		this.cartao = cartao;
		this.ipCliente = ipCliente;
		this.userAgent = userAgent;
	}

	public Long getId() {
		return id;
	}

	public String getDestino() {
		return destino;
	}

	public LocalDate getTerminoViagem() {
		return terminoViagem;
	}

	public String getIpCliente() {
		return ipCliente;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public LocalDateTime getInstateAvisoViagem() {
		return instateAvisoViagem;
	}

	public Cartao getCartao() {
		return cartao;
	}

	public AvisoViagemRequest toRequest() {
		return new AvisoViagemRequest(this);
	}
}
