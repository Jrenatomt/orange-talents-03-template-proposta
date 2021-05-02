package com.renato.proposta.carteira;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.renato.proposta.carteiraDigital.Carteira;
import com.renato.proposta.carteiraDigital.CarteiraDigitalRequest;
import com.renato.proposta.carteiraDigital.CarteiraDigitalResponse;
import com.renato.proposta.solicitacaoCartao.IntegracaoCartaoCliente;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@ActiveProfiles("test")
public class CarteiraDigitalControllerTests {
	
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private IntegracaoCartaoCliente mockCartao;
	
	@Test
	public void deveriaAssociarCarteiraERetornar201() throws Exception {
		
		CarteiraDigitalRequest request = new CarteiraDigitalRequest("email@email.com", Carteira.PAYPAL );
		String jsonBody = objectMapper.writeValueAsString(request);

		when(mockCartao.associarCarteira(eq("5209-1622-1164-1234"), any())).thenReturn(new CarteiraDigitalResponse("ASSOCIADA", "ugdt-p7nc-16tr-12lk"));

		mockMvc.perform(post("/api/cartoes/2/carteiras")
	                .content(jsonBody)
	                .contentType(MediaType.APPLICATION_JSON))
		            .andExpect(status().isCreated())
		            .andExpect(header().exists("Location"));
	}
	
	@Test
	public void naoDeveriaAssociarCarteiraComCartaoJaAssociado() throws Exception {
		
		CarteiraDigitalRequest request = new CarteiraDigitalRequest("email@email.com", Carteira.PAYPAL );
		String jsonBody = objectMapper.writeValueAsString(request);
		
		mockMvc.perform(post("/api/cartoes/3/carteiras")
	                .content(jsonBody)
	                .contentType(MediaType.APPLICATION_JSON))
		            .andExpect(status().isUnprocessableEntity());
	}
	
	@Test
	public void NaoDeveriaAssociarCarteiraComEmailInvalido() throws Exception {
		
		CarteiraDigitalRequest request = new CarteiraDigitalRequest("email.com", Carteira.PAYPAL );
		String jsonBody = objectMapper.writeValueAsString(request);

		mockMvc.perform(post("/api/cartoes/2/carteiras")
	                .content(jsonBody)
	                .contentType(MediaType.APPLICATION_JSON))
		            .andExpect(status().isBadRequest());	
	}
	
	@Test
	public void deveriaAssociarCarteiraERetornar201QuandoAssociarOutraCarteira() throws Exception {
		
		CarteiraDigitalRequest request = new CarteiraDigitalRequest("email@email.com", Carteira.SAMSUNG_PAY );
		String jsonBody = objectMapper.writeValueAsString(request);

		when(mockCartao.associarCarteira(eq("5209-1622-1164-1234"), any())).thenReturn(new CarteiraDigitalResponse("ASSOCIADA", "ugdt-p7nc-16tr-12lk"));

		mockMvc.perform(post("/api/cartoes/2/carteiras")
	                .content(jsonBody)
	                .contentType(MediaType.APPLICATION_JSON))
		            .andExpect(status().isCreated())
		            .andExpect(header().exists("Location"));
	}
}
