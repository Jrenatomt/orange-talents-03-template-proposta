package com.renato.proposta.solicitacaoCartao;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.renato.proposta.cadastraProposta.Proposta;

public class SolicitaCartaoResponse {
	
	@JsonProperty("id")
	private String numeroCartao;
	private LocalDateTime emitidoEm;
	private String titular;
	private BigDecimal limite;
    private String idProposta;
    
    @Deprecated
	public SolicitaCartaoResponse() {
	}
    
	public SolicitaCartaoResponse(String numeroCartao, LocalDateTime emitidoEm, String titular, BigDecimal limite,
			String idProposta) {
		this.numeroCartao = numeroCartao;
		this.emitidoEm = emitidoEm;
		this.titular = titular;
		this.limite = limite;
		this.idProposta = idProposta;
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

	public String getIdProposta() {
		return idProposta;
	}

	public Cartao toModel(Proposta proposta) {
		return new Cartao(this.numeroCartao, this.emitidoEm, this.titular, this.limite, proposta);
	}

	@Override
	public String toString() {
		return "CartaoResponse [numeroCartao=" + numeroCartao + ", emitidoEm=" + emitidoEm + ", titular=" + titular
				+ ", limite=" + limite + ", idProposta=" + idProposta + "]";
	}
	
	
}
