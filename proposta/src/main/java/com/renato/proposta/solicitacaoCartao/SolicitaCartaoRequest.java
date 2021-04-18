package com.renato.proposta.solicitacaoCartao;

import com.renato.proposta.cadastraProposta.Proposta;

public class SolicitaCartaoRequest {

	private String documento;
	private String nome;
	private String idProposta;
	
	@Deprecated
	public SolicitaCartaoRequest() {
	}

	public SolicitaCartaoRequest(Proposta proposta) {
		this.documento = proposta.getDocumento();
		this.nome = proposta.getNome();
		this.idProposta = proposta.getId().toString();
	}

	public String getDocumento() {
		return documento;
	}

	public String getNome() {
		return nome;
	}

	public String getIdProposta() {
		return idProposta;
	}
}
