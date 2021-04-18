package com.renato.proposta.solicitacaoCartao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "cartaoCliente", url = "${cartao.api}")
public interface SolicitaCartaoCliente {

	@PostMapping
	public SolicitaCartaoResponse solicitaCartao(@RequestBody SolicitaCartaoRequest request);
}
