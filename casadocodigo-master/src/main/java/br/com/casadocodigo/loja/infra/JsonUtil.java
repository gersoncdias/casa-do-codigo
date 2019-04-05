package br.com.casadocodigo.loja.infra;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.casadocodigo.loja.models.Pedido;


public class JsonUtil {
	private static ObjectMapper mapper;
	
	static {
		mapper = new ObjectMapper();
	}
	
	public static List<Pedido> jsonToJava (String jsonString, Class <Pedido> Pedido) {
		System.out.println("Método Json Chamado!");
		
		List <Pedido> result = null;
			
		try {
				
			result = mapper.readValue(jsonString, mapper.getTypeFactory().constructCollectionType(List.class, Pedido));
			
			} catch (JsonParseException e) {
				System.out.println("Erro para converer JSON em objeto: " + e.getMessage());
			} catch (JsonMappingException e) {
				System.out.println("Erro para converer JSON em objeto: " + e.getMessage());
			} catch (IOException e) {
				System.out.println("Erro para converer JSON em objeto: " + e.getMessage());
			}
	
		return result;	
	}

}
