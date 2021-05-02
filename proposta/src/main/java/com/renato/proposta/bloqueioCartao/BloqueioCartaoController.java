package com.renato.proposta.bloqueioCartao;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.renato.proposta.solicitacaoCartao.Cartao;
import com.renato.proposta.solicitacaoCartao.CartaoRepository;
import com.renato.proposta.solicitacaoCartao.IntegracaoCartaoCliente;

import feign.FeignException;

@RestController
@RequestMapping(value = "/api/cartoes")
public class BloqueioCartaoController {

	private CartaoRepository cartaoRepository;
    private BloqueioCartaoRepository bloqueioCartaoRepository;
	private IntegracaoCartaoCliente solicitaCartaoCliente;
	
	public BloqueioCartaoController(CartaoRepository cartaoRepository,
			BloqueioCartaoRepository bloqueioCartaoRepository,
			IntegracaoCartaoCliente solicitaCartaoCliente) {
		this.cartaoRepository = cartaoRepository;
		this.bloqueioCartaoRepository = bloqueioCartaoRepository;
		this.solicitaCartaoCliente = solicitaCartaoCliente;
	}

	@Transactional
	@PostMapping("/{idCartao}/bloquear")
	public ResponseEntity<?> bloqueiaCartao(@PathVariable Long idCartao, HttpServletRequest infoRequest,
			@RequestHeader (HttpHeaders.USER_AGENT) String userAgent, @RequestBody @Valid BloqueioRequest request) {

		String ipCliente = infoRequest.getRemoteAddr();

		if (userAgent.isEmpty()) {
			return ResponseEntity.badRequest().build();
		}

		if (!StringUtils.hasText(ipCliente)) {
            return ResponseEntity.badRequest().build();
	    }

		Cartao cartaoParaBloqueio = cartaoRepository.findById(idCartao)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cartão não encontrado"));

		if (cartaoParaBloqueio.bloqueado()) {
			return ResponseEntity.unprocessableEntity().build();
		}

		try {
			cartaoParaBloqueio.atualizaStatus(solicitaCartaoCliente, request);
			BloqueioCartao cartao = new BloqueioCartao(cartaoParaBloqueio, ipCliente, userAgent);
			bloqueioCartaoRepository.save(cartao);
		} catch (FeignException e) {
			throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Erro na api de bloqueio");
		}

		return ResponseEntity.ok().build();
	}
}
