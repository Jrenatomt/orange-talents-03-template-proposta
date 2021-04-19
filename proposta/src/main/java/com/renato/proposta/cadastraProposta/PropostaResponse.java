package com.renato.proposta.cadastraProposta;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.renato.proposta.solicitacaoCartao.CartaoResponse;

public class PropostaResponse {

	private StatusProposta status;
	private String nome;
	private String email;
	private String endereco;
	private BigDecimal salario;
	private LocalDateTime criadoEm;
	private CartaoResponse cartao;

	public PropostaResponse(Proposta proposta) {
		this.nome = proposta.getNome();
		this.status = proposta.getStatus();
		this.email = proposta.getEmail();
		this.endereco = proposta.getEndereco();
		this.salario = proposta.getSalario();
		this.cartao = (proposta.getCartao() == null) ? null : new CartaoResponse(proposta.getCartao());
		this.criadoEm = proposta.getCriadoEm();
	}

	public StatusProposta getStatus() {
		return status;
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public String getEndereco() {
		return endereco;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public LocalDateTime getCriadoEm() {
		return criadoEm;
	}

	public CartaoResponse getCartao() {
		return cartao;
	}
}
