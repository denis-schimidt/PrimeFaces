package br.com.schimidtsolutions.jsf.managedbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import org.omnifaces.cdi.ViewScoped;

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
	public void excluirProduto( ProdutoBinding produtoAExcluir ) {
		dao.apagar( produtoAExcluir.toEntity() );

		resetBindings();
	}

	public List<ProdutoBinding> getProdutos() {

		if (produtosBinding == null) {
			atualizarProdutosDoBancoDeDados();
		}

		return produtosBinding;
	}
	
	public ProdutoBinding getProduto() {
		return produtoBinding;
	}
	
	public void setProdutoASerAlterado(ProdutoBinding produtoASerAlterado) {
		this.produtoBinding = produtoASerAlterado;
	}

	private void atualizarProdutosDoBancoDeDados() {

		List<Produto> produtos = dao.listarTudo();

		produtosBinding = new ArrayList<ProdutoBinding>( produtos.size() );

		for (Produto produto : produtos) {
			produtosBinding.add(new ProdutoBinding().fromEntity(produto));
		}
	}

	private void resetBindings() {
		produtoBinding = new ProdutoBinding();
		atualizarProdutosDoBancoDeDados();
	}
}