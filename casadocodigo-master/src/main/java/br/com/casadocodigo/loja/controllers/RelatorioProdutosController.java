package br.com.casadocodigo.loja.controllers;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.casadocodigo.loja.dao.ProdutoDAO;
import br.com.casadocodigo.loja.models.Produto;
import br.com.casadocodigo.loja.models.Relatorio;

@Controller
@RequestMapping("/relatorio-produtos")
public class RelatorioProdutosController {

	@Autowired
	private ProdutoDAO dao;

	@ResponseBody
	@RequestMapping(value = "", method = RequestMethod.GET)
	public Relatorio relatorio(@RequestParam(required = false) String data) {
		Relatorio relatorio;

		if (data != null) {
			relatorio = relatorioProdutosPorData(data);
			return relatorio;
		}

		relatorio = relatorioProdutos();
		return relatorio;

	}

	public Relatorio relatorioProdutos() {
		System.out.println("1");
		List<Produto> produtos = dao.listar();
		Relatorio relatorio = new Relatorio(LocalDate.now().toString(), produtos.size(), produtos);
		return relatorio;
	}

	public Relatorio relatorioProdutosPorData(String data) {
		System.out.println("2");
		String[] calendario = data.split("-");
		Calendar busca = new Calendar.Builder()
				.setDate(Integer.valueOf(calendario[0]), Integer.valueOf(calendario[1]), Integer.valueOf(calendario[2]))
				.build();
		System.out.println(busca);
		List<Produto> produtos = dao.relatorioPorData(busca);
		Relatorio relatorio = new Relatorio(LocalDate.now().toString(), produtos.size(), produtos);
		return relatorio;
	}
}