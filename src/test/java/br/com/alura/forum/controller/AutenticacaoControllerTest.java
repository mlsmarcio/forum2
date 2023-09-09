package br.com.alura.forum.controller;

import java.net.URI;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

//Como é um projeto do spring boot
@RunWith(SpringRunner.class)

// PARA CONTROLLER O spring carrega as classes da parte MVC, Controller, RestController, ControllerAdvanced
// @WebMvcTest

// Carrega todas as classes necessárias
@SpringBootTest

// Necessário para utilizar a classe MockMvc
@AutoConfigureMockMvc

//Quando rodar a classe de teste forçar a ativação do profile test
@ActiveProfiles("test") 

public class AutenticacaoControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	// TESTANDO SE O RETORNO SERÁ 400 QUANDO OS DADOS DE AUTENTICAÇÃO FOREM INCORRETOS
	@Test
	public void deveriaDevolver400CasoDadosDeAutenticacaoEstejamIncorretos() throws Exception {
		URI uri = new URI("/auth");
		String json = "{\"email\":\"invalido@email.com\", \"senha\":\"123\"}";
		
		mockMvc
			.perform(MockMvcRequestBuilders
				.post(uri)
				.content(json)
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers
				.status()
				.is(400));
	}
}
