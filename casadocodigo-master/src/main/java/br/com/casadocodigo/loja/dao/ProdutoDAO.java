package br.com.casadocodigo.loja.dao;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.casadocodigo.loja.models.Produto;
import br.com.casadocodigo.loja.models.TipoPreco;

@Repository
@Transactional
public class ProdutoDAO {

	@PersistenceContext
	private EntityManager manager;
	
	public void gravar(Produto produto) {
		manager.persist(produto);
	}

	public List<Produto> listar() {
		return manager.createQuery("select distinct(p) from Produto p join fetch p.precos", Produto.class)
				.getResultList();
	}

	public Produto find(Integer id) {
        return manager.createQuery("select distinct(p) from Produto p join fetch p.precos precos where p.id = :id", 
        		Produto.class).setParameter("id", id)
        		.getSingleResult();
	}
	
	
	public List<Produto> findByDate (Calendar data) {
        return manager.createQuery("select p from Produto p where p.dataLancamento >= :p.DataLancamento", 
        		Produto.class).setParameter("p.DataLancamento", data)
        		.getResultList();
	}
	
	public BigDecimal somaPrecosPorTipo(TipoPreco tipoPreco){
	    TypedQuery<BigDecimal> query = manager.createQuery("select sum(preco.valor) from Produto p join p.precos preco "
	    		+ "where preco.tipo = :tipoPreco", BigDecimal.class);
	    query.setParameter("tipoPreco", tipoPreco);
	    return query.getSingleResult();
	}

public List<Produto> relatorioPorData(Calendar date) {
		
		List<Produto> produtos = manager.createQuery("select distinct(p) from Produto p join fetch p.precos "
				+ "where p.dataLancamento >= :data" , Produto.class ).setParameter("data", date).getResultList();	
		
		return produtos;
	}
}