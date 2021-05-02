package com.renato.proposta.avisoViagem;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.renato.proposta.solicitacaoCartao.IntegracaoCartaoCliente;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@ActiveProfiles("test")
public class AvisoViagemControllerTest {
	
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private IntegracaoCartaoCliente mockCartao;
	
	@Test
	public void deveriaCriarAvisoViagemCartaoERetornar200() throws Exception {
		
		AvisoViagemRequest request = new AvisoViagemRequest("Campina Grande", LocalDate.MAX);
		String jsonBody = objectMapper.writeValueAsString(request);

		when(mockCartao.avisarViagem(eq("5209-1622-1164-1234"), any())).thenReturn(new AvisoViagemResponse("CRIADO"));

		mockMvc.perform(post("/api/cartoes/2/avisos")
				 .header(HttpHeaders.USER_AGENT, "User-Agent")
	                .content(jsonBody)
	                .contentType(MediaType.APPLICATION_JSON)
	        ).andExpect(status().isOk());
	}
	
	@Test
	public void naoDeveriaCriarAvisoViagemComDataDoPassado() throws Exception {
		
		AvisoViagemRequest request = new AvisoViagemRequest("Campina Grande", LocalDate.MIN);
		String jsonBody = objectMapper.writeValueAsString(request);
		
		mockMvc.perform(post("/api/cartoes/2/avisos")
				 .header(HttpHeaders.USER_AGENT, "User-Agent")
	                .content(jsonBody)
	                .contentType(MediaType.APPLICATION_JSON)
	        ).andExpect(status().isBadRequest());
	}
	
	@Test
	public void naoDeveriaCriarAvisoViagemComDestinoInvalido() throws Exception {
		
		AvisoViagemRequest request = new AvisoViagemRequest(" ", LocalDate.MAX);
		String jsonBody = objectMapper.writeValueAsString(request);

		mockMvc.perform(post("/api/cartoes/2/avisos")
				 .header(HttpHeaders.USER_AGENT, "User-Agent")
	                .content(jsonBody)
	                .contentType(MediaType.APPLICATION_JSON)
	        ).andExpect(status().isBadRequest());
	}
	
	@Test
	public void naoDeveriaCriarAvisoViagemComCartaoInexistente() throws Exception {
		
		AvisoViagemRequest request = new AvisoViagemRequest("Campina Grande", LocalDate.MAX);
		String jsonBody = objectMapper.writeValueAsString(request);

		mockMvc.perform(post("/api/cartoes/100/avisos")
				 .header(HttpHeaders.USER_AGENT, "User-Agent")
	                .content(jsonBody)
	                .contentType(MediaType.APPLICATION_JSON)
	        ).andExpect(status().isNotFound());
	}
}
