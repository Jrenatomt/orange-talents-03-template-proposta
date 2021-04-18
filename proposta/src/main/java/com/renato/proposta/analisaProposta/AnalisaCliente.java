package com.renato.proposta.analisaProposta;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "analiseCliente", url = "${analisa.proposta.api}")
public interface AnalisaCliente {

	@PostMapping
	public AnalisaClienteResponse consultaAnalise(@RequestBody AnalisaClienteRequest request);
	
}