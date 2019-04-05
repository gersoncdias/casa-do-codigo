package br.com.casadocodigo.loja.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import br.com.casadocodigo.loja.infra.JsonUtil;
import br.com.casadocodigo.loja.models.Pedido;


@Controller
@RequestMapping("/pedidos")
public class PedidosServicoController {

	@Autowired
	RestTemplate restTemplate;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView listar() {
		List<Pedido> pedidos = carregar();
		ModelAndView modelAndView = new ModelAndView("produtos/pedidos");
		modelAndView.addObject("pedidos", pedidos);
		return modelAndView;
	}

	
	public List<Pedido> carregar() {
		System.out.println("Método Carregar Chamado");

		String uri = "https://book-payment.herokuapp.com/orders";
		ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);
		String json = response.getBody();
		System.out.println("JSON: " + json);

		System.out.println("======================");

		List <Pedido> pedidos = JsonUtil.jsonToJava(json, Pedido.class);
		System.out.println("PEDIDO: " + pedidos);

		return pedidos;
	}
}
