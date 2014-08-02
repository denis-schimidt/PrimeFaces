package br.com.schimidtsolutions.jsf;

import javax.enterprise.context.RequestScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import br.com.schimidtsolutions.jsf.binding.Produto;
import br.com.schimidtsolutions.jsf.dao.DAO;
import br.com.schimidtsolutions.jsf.entity.ProdutoEntity;

@Named
@RequestScoped
public class ProdutoBean {

	@Inject
	private DAO<ProdutoEntity> dao;
	
	private Produto produto;
	
	public ProdutoBean() {
		produto = new Produto();
	}
	
	@Transactional
	public void inserirNovoProduto( ActionEvent actionEvent ){
		dao.adicionar( new ProdutoEntity( produto.getNome(), produto.getDescricao(), produto.getPreco() ) );
		
		this.produto = new Produto();
	}
	
	public Produto getProduto() {
		return produto;
	}
}