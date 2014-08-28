package br.com.schimidtsolutions.jsf.managedbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import org.primefaces.component.datatable.DataTable;

import br.com.schimidtsolutions.jsf.common.interfaces.ProdutoMutavel;
import br.com.schimidtsolutions.jsf.constantes.LegendaTelaProduto;
import br.com.schimidtsolutions.jsf.dao.DAO;
import br.com.schimidtsolutions.jsf.entidades.Produto;
import br.com.schimidtsolutions.jsf.log.Logged;
import br.com.schimidtsolutions.jsf.managedbean.annotation.Binding;
import br.com.schimidtsolutions.jsf.managedbean.binding.ProdutoBinding;

@Named
@ViewScoped
public class ProdutoBean implements Serializable {
	private static final long serialVersionUID = -6530799356937047703L;

	@Inject @Binding
	private ProdutoBinding produtoEditavel;
	
	private List<ProdutoMutavel> listaProdutos;
	
	private List<ProdutoMutavel> listaProdutosFiltrado;
	
	private LegendaTelaProduto legendaTela = LegendaTelaProduto.CADASTRAR_PRODUTO;

	@Inject
	private DAO<Produto> dao;
	
	private DataTable dataTable; 
	
	@Transactional
	@Logged
	public void salvarProduto() {
		
		if( produtoEditavel.getId() == null ){
			dao.adicionar( produtoEditavel.toEntity() );
			
		}else{
			dao.atualizar( produtoEditavel.toEntity() );
		}

		resetBindings();
	}

	@Transactional
	@Logged
	public void excluirProduto( final ProdutoBinding produtoBinding ) {
		dao.apagar( produtoBinding.toEntity() );

		resetBindings();
	}
	
	public void cancelarAlteracaoProduto(){
		resetBindings();
	}

	public List<ProdutoMutavel> getProdutos() {	
		
		if (listaProdutos == null) {
			atualizarListaProdutosDoBancoDeDados();
		}

		return listaProdutos;
	}
	
	public ProdutoMutavel getProduto() {
		return produtoEditavel;
	}

	public void exibirProdutoAlteracao(final ProdutoMutavel produto) {
		produtoEditavel = (ProdutoBinding) produto;
		legendaTela = LegendaTelaProduto.EDITAR_PRODUTO;
	}
	
	public List<ProdutoMutavel> getListaProdutosFiltrado() {
		return listaProdutosFiltrado;
	}
	
	public void setListaProdutosFiltrado( final List<ProdutoMutavel> listaProdutosFiltrado) {
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
			final ProdutoMutavel produtoBinding = new ProdutoBinding( produto );
			
			listaProdutos.add( produtoBinding );
		}
	} 

	private void resetBindings() {
		produtoEditavel = new ProdutoBinding();
		atualizarListaProdutosDoBancoDeDados();
		legendaTela = LegendaTelaProduto.CADASTRAR_PRODUTO;
		listaProdutosFiltrado = listaProdutos;
		dataTable.reset();    
	}
}