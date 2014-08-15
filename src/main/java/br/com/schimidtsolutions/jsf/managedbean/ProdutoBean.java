package br.com.schimidtsolutions.jsf.managedbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import org.primefaces.component.datatable.DataTable;

import br.com.schimidtsolutions.jsf.constant.LegendaTelaProduto;
import br.com.schimidtsolutions.jsf.dao.DAO;
import br.com.schimidtsolutions.jsf.entity.Produto;
import br.com.schimidtsolutions.jsf.log.Logged;
import br.com.schimidtsolutions.jsf.managedbean.binding.ProdutoBindingCopiavel;
import br.com.schimidtsolutions.jsf.managedbean.interfaces.ProdutoBinding;

@Named
@ViewScoped
public class ProdutoBean implements Serializable {
	private static final long serialVersionUID = -6530799356937047703L;

	@Inject
	private DAO<Produto> dao;
	
	@Inject
	private ProdutoBinding produtoEditavel;
	
	private List<ProdutoBinding> listaProdutos;
	
	private List<ProdutoBinding> listaProdutosFiltrado;
	
	private LegendaTelaProduto legendaTela = LegendaTelaProduto.CADASTRAR_PRODUTO;
	
	private DataTable dataTable; 
	
	@Transactional
	@Logged
	public void salvarProduto() {
		final ProdutoBindingCopiavel produtoEmEdicao = (ProdutoBindingCopiavel) produtoEditavel;
		
		if( produtoEmEdicao.getId() == null ){
			dao.adicionar( produtoEmEdicao.paraEntidade() );
			
		}else{
			dao.atualizar( produtoEmEdicao.paraEntidade() );
		}

		resetBindings();
	}

	@Transactional
	@Logged
	public void excluirProduto( final ProdutoBindingCopiavel produtoBinding ) {
		dao.apagar( produtoBinding.paraEntidade() );

		resetBindings();
	}
	
	public void cancelarAlteracaoProduto(){
		resetBindings();
	}

	public List<ProdutoBinding> getProdutos() {

		if (listaProdutos == null) {
			atualizarListaProdutosDoBancoDeDados();
		}

		return listaProdutos;
	}
	
	public ProdutoBinding getProduto() {
		return produtoEditavel;
	}

	public void exibirProdutoAlteracao(final ProdutoBinding produto) {
		produtoEditavel = produto;
		legendaTela = LegendaTelaProduto.EDITAR_PRODUTO;
	}
	
	public List<ProdutoBinding> getListaProdutosFiltrado() {
		return listaProdutosFiltrado;
	}
	
	public void setListaProdutosFiltrado( final List<ProdutoBinding> listaProdutosFiltrado) {
		this.listaProdutosFiltrado = listaProdutosFiltrado;
	}
	
	public String getTituloLegenda() {
		return legendaTela.getDescricaoLegenda();
	}
	
	public DataTable getDataTable() {
		return dataTable;
	}

	public void setDataTable(final DataTable dataTable) {
		this.dataTable = dataTable;
	}

	private void atualizarListaProdutosDoBancoDeDados() {
		final List<Produto> produtos = dao.listarTudo();
		listaProdutos = new ArrayList<>( produtos.size() );
		
		for (final Produto produto : produtos) {
			final ProdutoBindingCopiavel produtoBinding = new ProdutoBindingCopiavel();
			produtoBinding.copiarDaEntidade( produto );
			
			listaProdutos.add( produtoBinding );
		}
	} 

	private void resetBindings() {
		produtoEditavel = new ProdutoBindingCopiavel();
		atualizarListaProdutosDoBancoDeDados();
		legendaTela = LegendaTelaProduto.CADASTRAR_PRODUTO;
		listaProdutosFiltrado = listaProdutos;
		dataTable.reset();    
	}
}