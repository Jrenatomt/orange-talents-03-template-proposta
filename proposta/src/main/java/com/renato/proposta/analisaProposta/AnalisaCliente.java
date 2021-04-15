package com.renato.proposta.analisaProposta;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "analiseCliente", url = "${analisa.proposta.api}")
@Component
public interface AnalisaCliente {

	@PostMapping
	AnalisaClienteResponse consultaAnalise(@RequestBody AnalisaClienteRequest request);
}