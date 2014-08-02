package br.com.schimidtsolutions.jsf;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import br.com.schimidtsolutions.jsf.binding.ProdutoBinding;
import br.com.schimidtsolutions.jsf.dao.DAO;
import br.com.schimidtsolutions.jsf.entity.Produto;

@Named
@RequestScoped
public class ProdutoBean {

	@Inject
	private DAO<Produto> dao;
	
	@Inject
	private ProdutoBinding produtoBinding;
	
	private List<Produto> produtos;
	
	@Transactional
	public void inserirNovoProduto( ActionEvent actionEvent ){
		
		dao.adicionar( produtoBinding.toEntity() );
		
		resetarBindings();
	}
	
	@Transactional
	public List<Produto> getProdutos(){
		
		if( produtos == null ){
			produtos = dao.listarTudo();
		}
		
		return produtos;
	}
	
	public ProdutoBinding getProduto() {
		return produtoBinding;
	}
	
	private void resetarBindings() {
		produtoBinding = new ProdutoBinding();
		produtos = dao.listarTudo();
	}
}