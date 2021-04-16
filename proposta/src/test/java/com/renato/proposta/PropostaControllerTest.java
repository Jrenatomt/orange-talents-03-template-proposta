package com.renato.proposta;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.renato.proposta.cadastraProposta.PropostaRequest;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@ActiveProfiles("test")
public class PropostaControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper mapper;
	
	private String nome;
	private String email;
	private String documentoValido;
	private String documentoInvalido;
	private String endereco;
	private BigDecimal salario;
	private BigDecimal salarioNegativo;
	
	@BeforeEach
	public void setUp() throws Exception {
		nome = "Renato marques";
		email = "renato@email.com";
		documentoValido = "09936398017";
		documentoInvalido = "099363980";
		endereco = "Rua das Rosas 230, Queimadas, Paraiba";
		salario = new BigDecimal(1000);
		salarioNegativo = new BigDecimal(-1);
	}
	
	@Test
	public void criaPropostaDeveriaCriarNovaPropostaComSucesso() throws JsonProcessingException, Exception {
		
		PropostaRequest propostaSucesso = new PropostaRequest(nome, email, documentoValido, endereco, salario);
		
		mockMvc.perform(post("/api/propostas").contentType("application/json")
			.content(mapper.writeValueAsString(propostaSucesso))).andExpect(status().isCreated());
	}
	
	@Test
	public void criaPropostaNaoDeveriaCriarPropostaComDocumentoJaExistente() throws JsonProcessingException, Exception{	
		
		PropostaRequest proposta = new PropostaRequest(nome, email, documentoValido , endereco, salario);

		mockMvc.perform(post("/api/propostas").contentType("application/json")
				.content(mapper.writeValueAsString(proposta))).andExpect(status().isCreated());
		
		mockMvc.perform(post("/api/propostas").contentType("application/json")
				.content(mapper.writeValueAsString(proposta))).andExpect(status().isUnprocessableEntity());
	}
	
	@Test
	public void criaPropostaNaoDeveriaCriarPropostaComDocumentoInvalido() throws JsonProcessingException, Exception {
		
		PropostaRequest propostaDocumentoInvalido = new PropostaRequest(nome, email, documentoInvalido , endereco, salario);
		
		mockMvc.perform(post("/api/propostas").contentType("application/json")
			.content(mapper.writeValueAsString(propostaDocumentoInvalido))).andExpect(status().isBadRequest());
	}
	
	@Test
	public void criaPropostaNaoDeveriaCriarPropostaComSalarioNegativo() throws JsonProcessingException, Exception {
		
		PropostaRequest proposta = new PropostaRequest(nome, email, documentoValido, endereco, salarioNegativo);
		
		mockMvc.perform(post("/api/propostas").contentType("application/json")
			.content(mapper.writeValueAsString(proposta))).andExpect(status().isBadRequest());
	}
	
	@Test
	public void criaPropostaNaoDeveriaCriarPropostaComNomeNulo() throws JsonProcessingException, Exception {
		
		PropostaRequest proposta = new PropostaRequest("", email, documentoValido , endereco, salario);
		
		mockMvc.perform(post("/api/propostas").contentType("application/json")
			.content(mapper.writeValueAsString(proposta))).andExpect(status().isBadRequest());
	}
	
	@Test
	public void criaPropostaNaoDeveriaCriarPropostaComEmailNulo() throws JsonProcessingException, Exception {
		
		PropostaRequest proposta = new PropostaRequest(nome, "", documentoValido , endereco, salario);
		
		mockMvc.perform(post("/api/propostas").contentType("application/json")
			.content(mapper.writeValueAsString(proposta))).andExpect(status().isBadRequest());
	}
	
	@Test
	public void criaPropostaNaoDeveriaCriarPropostaComEmailInvalido() throws JsonProcessingException, Exception {
		
		PropostaRequest proposta = new PropostaRequest(nome, "renatoemail.com", documentoValido , endereco, salario);
		
		mockMvc.perform(post("/api/propostas").contentType("application/json")
			.content(mapper.writeValueAsString(proposta))).andExpect(status().isBadRequest());
	}
	
	@Test
	public void criaPropostaNaoDeveriaCriarPropostaComEnderecoNulo() throws JsonProcessingException, Exception {
		
		PropostaRequest proposta = new PropostaRequest(nome, email, documentoValido , "", salario);
		
		mockMvc.perform(post("/api/propostas").contentType("application/json")
			.content(mapper.writeValueAsString(proposta))).andExpect(status().isBadRequest());
	}
}

