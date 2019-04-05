package br.com.casadocodigo.loja.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize
public class Pedido implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private int id;
	private BigDecimal valor;
	
	private Calendar data;
	
	private List<Produto> produtos;
	private String titulo;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	
 	 public String getData() { 
 		 SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
 		 String format = dateFormat.format(this.data.getTime());	
 		 return format;	
	}
	
 	 public void setData(Calendar data) {
		this.data = data;
	}
	public List<Produto> getProdutos() {
		return produtos;
	}
	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	
	
	public String getTitulo() {
		
		List <String> titulos = new ArrayList<>();
		for (Produto produto : produtos) {
		 titulos.add(produto.getTitulo());
		}
		
		StringBuilder str = new StringBuilder();
		for(String elemento : titulos){
		  str.append(elemento).append(", ");
		 }
		String substring = str.toString().substring( 0, str.length() - 2 );
		
		return substring;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	@Override
	public String toString() {
		return "PEDIDO [id:" + id + ", valor:" + valor + ", data:" + data + ", produtos:" + produtos + ", titulo:"
				+ titulo + "]";
	}
	
	
	
	
}
