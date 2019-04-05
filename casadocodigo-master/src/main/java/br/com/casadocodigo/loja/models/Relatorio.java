package br.com.casadocodigo.loja.models;

import java.time.LocalDate;
import java.util.List;

public class Relatorio {

	private String dataGeracao;
	
	private int quantidade;
	
	private List<Produto> produtos ;

	public Relatorio(String dia, int size, List<Produto> produtos) {
		this.dataGeracao = dia;
		this.quantidade = size;
		this.produtos = produtos;
		
	}

	public String getDataGeracao() {
		return dataGeracao;
	}

	public void setDataGeracao(String dataGeracao) {
		this.dataGeracao = dataGeracao;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	
	
}