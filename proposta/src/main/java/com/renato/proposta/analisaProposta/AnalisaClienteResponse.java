package com.renato.proposta.analisaProposta;

import com.renato.proposta.cadastraProposta.StatusProposta;

public class AnalisaClienteResponse {

	private StatusAnaliseCliente resultadoAnalise;

	@Deprecated
	public AnalisaClienteResponse() {
	}

	public AnalisaClienteResponse(StatusAnaliseCliente resultadoAnalise) {
		this.resultadoAnalise = resultadoAnalise;
	}

	public StatusAnaliseCliente getResultadoAnalise() {
		return resultadoAnalise;
	}

	public StatusProposta analisaStatus() {
		if (resultadoAnalise == StatusAnaliseCliente.COM_RESTRICAO) {
			return StatusProposta.NAO_ELEGIVEL;
		}
		return StatusProposta.ELEGIVEL;
	}
}
