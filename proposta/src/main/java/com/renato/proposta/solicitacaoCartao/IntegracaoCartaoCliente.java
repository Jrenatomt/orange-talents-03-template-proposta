package com.renato.proposta.solicitacaoCartao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.renato.proposta.avisoViagem.AvisoViagemRequest;
import com.renato.proposta.avisoViagem.AvisoViagemResponse;
import com.renato.proposta.bloqueioCartao.BloqueioRequest;
import com.renato.proposta.bloqueioCartao.BloqueioResponse;

@FeignClient(name = "cartaoCliente", url = "${cartao.api}")
public interface IntegracaoCartaoCliente {

	@PostMapping
	public SolicitaCartaoResponse solicitaCartao(@RequestBody SolicitaCartaoRequest request);
	
	@PostMapping("/{id}/bloqueios")
	public BloqueioResponse bloqueiaCartao(@PathVariable String id, @RequestBody BloqueioRequest request);
	
	@PostMapping("/{id}/avisos")
	public AvisoViagemResponse avisarViagem(@PathVariable String id, @RequestBody AvisoViagemRequest request);

}


