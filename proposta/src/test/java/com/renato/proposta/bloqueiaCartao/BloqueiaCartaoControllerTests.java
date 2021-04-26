package com.renato.proposta.bloqueiaCartao;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.renato.proposta.bloqueioCartao.BloqueioRequest;
import com.renato.proposta.bloqueioCartao.BloqueioResponse;
import com.renato.proposta.solicitacaoCartao.IntegracaoCartaoCliente;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class BloqueiaCartaoControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private IntegracaoCartaoCliente MockSolicitaCartao;

	private BloqueioRequest request;
	private String jsonBody;

	@BeforeEach
	void init() throws JsonProcessingException {
		request = new BloqueioRequest("paypal");
		jsonBody = objectMapper.writeValueAsString(request);
	}

	@Test
	public void deveriaBloquearCartaoERetornar200() throws Exception {

		when(MockSolicitaCartao.bloqueiaCartao(eq("5209-1622-1164-1234"), any())).thenReturn(new BloqueioResponse("BLOQUEADO"));

		mockMvc.perform(post("/api/cartoes/2/bloquear")
				.header(HttpHeaders.USER_AGENT, "User-Agent")
				.content(jsonBody)
				.contentType(MediaType.APPLICATION_JSON))
		        .andExpect(status().isOk());
	    }
	
	@Test
	public void deveriaRetornar404ComCartaoNaoEncontrado() throws Exception {
		
		mockMvc.perform(post("/api/cartoes/100/bloquear")
				.header(HttpHeaders.USER_AGENT, "User-Agent")
				.content(jsonBody)
				.contentType(MediaType.APPLICATION_JSON))
		        .andExpect(status().isNotFound());
	    }
	
	@Test
	public void deveriaRetornar422ComCartaoJaBloqueado() throws Exception {
		
		mockMvc.perform(post("/api/cartoes/1/bloquear")
				.header(HttpHeaders.USER_AGENT, "User-Agent")
				.content(jsonBody)
				.contentType(MediaType.APPLICATION_JSON))
		        .andExpect(status().isUnprocessableEntity());
	    }
	}
