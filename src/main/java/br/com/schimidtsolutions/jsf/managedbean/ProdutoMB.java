package br.com.schimidtsolutions.jsf.managedbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import br.com.schimidtsolutions.jsf.dao.DAO;
import br.com.schimidtsolutions.jsf.entity.Produto;
import br.com.schimidtsolutions.jsf.log.Logged;

@Named
@ViewScoped
public class ProdutoMB implements Serializable {
	private static final long serialVersionUID = -3828914192996921905L;
	
	@Inject
	private DAO<Produto> dao;
	
	private Produto.Builder produtoEmEdicao = new Produto.Builder();

	private List<Produto.Builder> listaProdutos;
	
	@Transactional
	@Logged
	public void salvarProduto() {
		final Produto produto = produtoEmEdicao.create();
		
		if( produto.getId() == null ){
			dao.adicionar( produto );
			
		}else{
			dao.atualizar( produto );
		}

		resetBindings();
	}

	@Transactional
	@Logged
	public void excluirProduto( final Produto.Builder produto ) {
		dao.apagar( produto.create() );

		resetBindings();
	}
	
	public void cancelarAlteracaoProduto(){
		resetBindings();
	}

	public List<Produto.Builder> getProdutos() {

		if (listaProdutos == null) {
			atualizarListaProdutosDoBancoDeDados();
		}

		return listaProdutos;
	}
	
	public Produto.Builder getProduto() {
		return produtoEmEdicao;
	}

	public void exibirProdutoAlteracao(final Produto.Builder produto) {
		produtoEmEdicao = produto;
	}
	
	private void atualizarListaProdutosDoBancoDeDados() {
		final List<Produto> produtos = dao.listarTudo();
		listaProdutos = new ArrayList<>( produtos.size() );
		
		for (final Produto produto : produtos) {
			listaProdutos.add( new Produto.Builder(produto) );
		}
	} 

	private void resetBindings() {
		produtoEmEdicao = new Produto.Builder();
		atualizarListaProdutosDoBancoDeDados();
	}
}