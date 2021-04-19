package com.renato.proposta.solicitacaoCartao;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CartaoResponse {
	
	private String numeroCartao;
	private LocalDateTime emitidoEm;
	private String titular;
	private BigDecimal limite;
	
	public CartaoResponse(Cartao cartao) {
		this.numeroCartao = cartao.getNumeroCartao();
		this.emitidoEm = cartao.getEmitidoEm();
		this.titular = cartao.getTitular();
		this.limite = cartao.getLimite();
	}

	public String getNumeroCartao() {
		return numeroCartao;
	}

	public LocalDateTime getEmitidoEm() {
		return emitidoEm;
	}

	public String getTitular() {
		return titular;
	}

	public BigDecimal getLimite() {
		return limite;
	}
}
