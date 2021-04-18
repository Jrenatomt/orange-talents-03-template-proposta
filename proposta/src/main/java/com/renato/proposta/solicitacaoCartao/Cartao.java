package com.renato.proposta.solicitacaoCartao;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.renato.proposta.cadastraProposta.Proposta;

@Entity
public class Cartao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String numeroCartao;
	private LocalDateTime emitidoEm;
	private String titular;
	private BigDecimal limite;
	@OneToOne
	private Proposta proposta;
	
	@Deprecated
	public Cartao(){
	}

	public Cartao( String numeroCartao, LocalDateTime emitidoEm, String titular, BigDecimal limite,
			Proposta proposta) {
		this.numeroCartao = numeroCartao;
		this.emitidoEm = emitidoEm;
		this.titular = titular;
		this.limite = limite;
		this.proposta = proposta;
	}

	public Long getId() {
		return id;
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

	public Proposta getProposta() {
		return proposta;
	}

	@Override
	public String toString() {
		return "Cartao [id=" + id + ", numeroCartao=" + numeroCartao + ", emitidoEm=" + emitidoEm + ", titular="
				+ titular + ", limite=" + limite + ", proposta=" + proposta + "]";
	}
	
	
}

