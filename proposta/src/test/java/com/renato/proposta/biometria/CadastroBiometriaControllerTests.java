/**
 * 
 */
package com.renato.proposta.biometria;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.renato.proposta.cadastroBiometria.BiometriaRequest;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class CadastroBiometriaControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void criaBiometriaDeveriaCriarNovaBiometriaComDadosValidos() throws JsonProcessingException, Exception {

		BiometriaRequest biometria = new BiometriaRequest("1627265721");
		String jsonBody = objectMapper.writeValueAsString(biometria);

		mockMvc.perform(post("/api/cartoes/1/biometrias")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonBody))
				.andExpect(status().isCreated()).andExpect(header().exists("Location"));
	}
	
	@Test
	public void criaBiometriaNaoDeveriaCriarNovaBiometriaComDadosNulos() throws JsonProcessingException, Exception {

		BiometriaRequest biometria = new BiometriaRequest(" ");
		String jsonBody = objectMapper.writeValueAsString(biometria);

		mockMvc.perform(post("/api/cartoes/1/biometrias")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonBody))
				.andExpect(status().isBadRequest());
	}
	
	@Test
	public void criaBiometriaNaoDeveriaCriarNovaBiometriaComCartaoInvalido() throws JsonProcessingException, Exception {

		BiometriaRequest biometria = new BiometriaRequest("1627265721");
		String jsonBody = objectMapper.writeValueAsString(biometria);

		mockMvc.perform(post("/api/cartoes/5/biometrias")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonBody))
				.andExpect(status().isUnprocessableEntity());
	}
}
