package com.renato.proposta.analisaProposta;

import com.renato.proposta.cadastraProposta.Proposta;

public class AnalisaClienteRequest {
	
	private String documento;
	private String nome;
	private String idProposta;

	public AnalisaClienteRequest(Proposta novaProposta) {
		this.documento = novaProposta.getDocumento();
		this.nome = novaProposta.getNome();
		this.idProposta = novaProposta.getId().toString();
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