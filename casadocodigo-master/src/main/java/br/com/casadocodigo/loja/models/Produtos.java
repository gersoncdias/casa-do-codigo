package br.com.casadocodigo.loja.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

public class Produtos {

	private String titulo;

	private String descricao;

	private int paginas;

	private List<Preco> precos = new ArrayList<>();

	@DateTimeFormat
	private Calendar dataLancamento;

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getPaginas() {
		return paginas;
	}

	public void setPaginas(int paginas) {
		this.paginas = paginas;
	}

	public List<Preco> getPrecos() {
		return precos;
	}

	public void setPrecos(List<Preco> precos) {
		this.precos = precos;
	}

	public Calendar getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(Calendar dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	public Produtos criarProduto(int paginas, Calendar data, String titulo, String descricao, String[] precos) {
		Produtos produto = new Produtos();
		produto.setPaginas(paginas);
		produto.setTitulo(titulo);
		produto.setDataLancamento(data);
		produto.setDescricao(descricao);
		Preco ebook = new Preco(new BigDecimal(precos[0]), TipoPreco.EBOOK);
		Preco impresso = new Preco(new BigDecimal(precos[1]), TipoPreco.IMPRESSO);
		Preco combo = new Preco(new BigDecimal(precos[2]), TipoPreco.COMBO);
		produto.setPrecos(Arrays.asList(ebook, impresso, combo));
		return produto;
	}

}
