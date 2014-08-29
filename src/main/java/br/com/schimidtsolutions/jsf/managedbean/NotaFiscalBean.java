package br.com.schimidtsolutions.jsf.managedbean;

import java.io.Serializable;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicInteger;

import javax.enterprise.inject.Default;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.schimidtsolutions.jsf.client.interfaces.ItemMutavel;
import br.com.schimidtsolutions.jsf.client.interfaces.ItemMutavelTemporario;
import br.com.schimidtsolutions.jsf.client.interfaces.NotaFiscalMutavel;
import br.com.schimidtsolutions.jsf.client.interfaces.ProdutoMutavel;
import br.com.schimidtsolutions.jsf.ejb.NotaFiscalService;
import br.com.schimidtsolutions.jsf.log.Logged;
import br.com.schimidtsolutions.jsf.managedbean.annotation.Binding;
import br.com.schimidtsolutions.jsf.managedbean.binding.ItemBinding;
import br.com.schimidtsolutions.jsf.managedbean.binding.ProdutoBinding;

@Named
@ViewScoped
public class NotaFiscalBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Inject @Binding
	private ItemMutavel itemMutavel; 
	
	@Inject @Binding
	private NotaFiscalMutavel notaFiscalMutavel;

	@Inject
	private NotaFiscalService notaFiscalService;
	
	@Inject
	private AtomicInteger geradorIdTemporario;
	 
	@Inject @Default
	private int chaveNaoEncontrada;
	
	private ProdutoMutavel produtoMutavel;
	
	@Logged
	public void salvarNotaFiscal(){
		notaFiscalService.cadastrarNotaFiscal( notaFiscalMutavel );
		
		resetarBindings();
	}
	
	public void incluirItemNotaFiscal(){		
		
		if( produtoMutavel != null ){
			setarNovoIdTemporario();
			relacionarItemComProduto();
			
			notaFiscalMutavel.getItens().add( itemMutavel );
		}
		
		resetarBindings();
	}

	private void resetarBindings() {
		itemMutavel = new ItemBinding();
		produtoMutavel = new ProdutoBinding();
	}

	private void relacionarItemComProduto() {
		itemMutavel.setProduto( produtoMutavel );
		itemMutavel.setValorUnitario( produtoMutavel.getPreco() );
	}

	private void setarNovoIdTemporario() {
		ItemBinding itemBinding = (ItemBinding) itemMutavel;
		itemBinding.setIdTemporario( geradorIdTemporario.getAndIncrement() );
	}
	
	public void selecionarItem( ItemMutavelTemporario item ){
		this.itemMutavel = item;
	}
	
	public void excluirItem( ItemMutavelTemporario item ){
		int posicaoItemAExcluir = Collections.binarySearch( notaFiscalMutavel.getItens(), item );
		
		if( posicaoItemAExcluir > -1 ){
			notaFiscalMutavel.getItens().remove( posicaoItemAExcluir );
		}
	}
	
	public NotaFiscalMutavel getNotaFiscal() {
		return notaFiscalMutavel;
	}

	public void setNotaFiscal( final NotaFiscalMutavel notaFiscal ) {
		notaFiscalMutavel = notaFiscal;
	}
	
	public ItemMutavel getItem() {
		return itemMutavel;
	}

	public ProdutoMutavel getProduto() {
		return produtoMutavel;
	}

	public void setProduto(final ProdutoMutavel produtoBinding) {
		this.produtoMutavel = produtoBinding;
	}
}
