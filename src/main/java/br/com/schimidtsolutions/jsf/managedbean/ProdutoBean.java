package br.com.schimidtsolutions.jsf.managedbean;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import br.com.schimidtsolutions.jsf.binding.ProdutoBinding;
import br.com.schimidtsolutions.jsf.dao.DAO;
import br.com.schimidtsolutions.jsf.entity.Produto;
import br.com.schimidtsolutions.jsf.log.Logged;

@Named
@RequestScoped
public class ProdutoBean {

	@Inject
	private DAO<Produto> dao;
	
	@Inject
	private ProdutoBinding produtoBinding;
	private List<ProdutoBinding> produtosBinding;

	private void atualizarProdutosDoBancoDeDados() {

		List<Produto> produtos = dao.listarTudo();

		produtosBinding = new ArrayList<ProdutoBinding>( produtos.size() );

		for (Produto produto : produtos) {
			produtosBinding.add(new ProdutoBinding().fromEntity(produto));
		}
	}

	@Transactional
	@Logged
	public void incluirProduto() {
		dao.adicionar( produtoBinding.toEntity() );

		resetBindings();
	}

	@Transactional
	@Logged
	public void excluirProduto( ProdutoBinding produtoBinding ) {
		dao.apagar( produtoBinding.getId() );

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

	private void resetBindings() {
		produtoBinding = new ProdutoBinding();
		atualizarProdutosDoBancoDeDados();
	}
}