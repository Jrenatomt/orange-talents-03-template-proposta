package com.renato.proposta.avisoViagem;

import java.time.LocalDate;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.renato.proposta.solicitacaoCartao.Cartao;

public class AvisoViagemRequest {
	
	@NotBlank
	private String destino;
    @NotNull @Future
	private LocalDate terminoViagem;

	@Deprecated
	public AvisoViagemRequest() {
	}

	public AvisoViagemRequest(@NotBlank String destino, @NotNull @Future LocalDate terminoViagem) {
		this.destino = destino;
		this.terminoViagem = terminoViagem;
	}

	public String getDestino() {
		return destino;
	}

	public LocalDate getTerminoViagem() {
		return terminoViagem;
	}

	public AvisoViagem toModel(Cartao cartao, String ipCliente, String userAgent) {
		return new AvisoViagem(destino, terminoViagem, cartao, ipCliente, userAgent);
	}
}
