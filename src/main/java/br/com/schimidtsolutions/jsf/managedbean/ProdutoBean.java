package br.com.schimidtsolutions.jsf.managedbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import br.com.schimidtsolutions.jsf.binding.ProdutoBinding;
import br.com.schimidtsolutions.jsf.dao.DAO;
import br.com.schimidtsolutions.jsf.entity.Produto;
import br.com.schimidtsolutions.jsf.log.Logged;

@Named
@ViewScoped
public class ProdutoBean implements Serializable {
	private static final long serialVersionUID = -3828914192996921905L;
	
	@Inject
	private DAO<Produto> dao;
	
	@Inject
	private ProdutoBinding produtoBinding;
	private List<ProdutoBinding> produtosBinding;
	
	@Transactional
	@Logged
	public void salvarProduto() {
		
		if( produtoBinding.getId() == null ){
			dao.adicionar( produtoBinding.toEntity() );
			
		}else{
			dao.atualizar( produtoBinding.toEntity() );
		}

		resetBindings();
	}

	@Transactional
	@Logged
	public void excluirProduto( final ProdutoBinding produto ) {
		dao.apagar( produto.toEntity() );

		resetBindings();
	}
	
	public void cancelarAlteracaoProduto(){
		resetBindings();
	}

	public List<ProdutoBinding> getProdutos() {

		if (produtosBinding == null) {
			atualizarListaProdutosDoBancoDeDados();
		}

		return produtosBinding;
	}
	
	public ProdutoBinding getProduto() {
		return produtoBinding;
	}

	public void exibirProdutoAlteracao(final ProdutoBinding produtoAAlterar) {
		produtoBinding = produtoAAlterar;
	}
	
	private void atualizarListaProdutosDoBancoDeDados() {

		final List<Produto> produtos = dao.listarTudo();

		produtosBinding = new ArrayList<ProdutoBinding>( produtos.size() );
		
		for (final Produto produto : produtos) {
			produtosBinding.add(new ProdutoBinding().fromEntity(produto));
		}
	} 

	private void resetBindings() {
		produtoBinding = new ProdutoBinding();
		atualizarListaProdutosDoBancoDeDados();
	}
}