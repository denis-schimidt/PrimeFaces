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
import br.com.schimidtsolutions.jsf.constantes.StatusItemNotaFiscal;
import br.com.schimidtsolutions.jsf.ejb.NotaFiscalService;
import br.com.schimidtsolutions.jsf.log.Logged;
import br.com.schimidtsolutions.jsf.managedbean.annotation.Binding;
import br.com.schimidtsolutions.jsf.managedbean.binding.ItemBinding;
import br.com.schimidtsolutions.jsf.managedbean.binding.NotaFiscalBinding;
import br.com.schimidtsolutions.jsf.managedbean.binding.ProdutoBinding;

@Named
@ViewScoped
public class NotaFiscalBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Inject @Binding
	private ItemMutavel item; 
	
	@Inject @Binding
	private NotaFiscalMutavel notaFiscal;

	@Inject
	private NotaFiscalService notaFiscalService;
	
	@Inject
	private AtomicInteger geradorIdTemporario;
	 
	@Inject @Default
	private int chaveNaoEncontrada;
	
	@Inject
	private StatusItemNotaFiscal statusItem;

	private ProdutoMutavel produto;
	
	@Logged
	public void salvarNotaFiscal(){
		notaFiscalService.cadastrarNotaFiscal( notaFiscal );
		
		resetarBindingsPagina();
	}
	
	public void salvarItemNotaFiscal(){		
		
		if( statusItem == StatusItemNotaFiscal.ALTERACAO ){
			atualizarItemNaLista();
			
		}else{
			inserirItemNaLista();
		}
		
		resetarBindingsTemporarios();
	}

	private void inserirItemNaLista() {
		setarNovoIdTemporario();
		relacionarItemComProduto();
		
		notaFiscal.getItens().add( item );
	}
	
	public StatusItemNotaFiscal getStatusItem() {
		return statusItem;
	}

	private void atualizarItemNaLista() {
		int posicaoItemAExcluir = localizarPosicaoNaListaItem( item );
		
		if( posicaoItemAExcluir > chaveNaoEncontrada ){
			notaFiscal.getItens().set( posicaoItemAExcluir, item );
		}
	}

	private int localizarPosicaoNaListaItem( ItemMutavel itenProcurado ) {
		return Collections.binarySearch( notaFiscal.getItens(), itenProcurado );
	}
	
	public void cancelarAlteracaoItem(){
		resetarBindingsTemporarios();
	}
	
	private void resetarBindingsPagina(){
		this.notaFiscal = new NotaFiscalBinding();
		resetarBindingsTemporarios();
	}
	
	private void resetarBindingsTemporarios() {
		item = new ItemBinding();
		produto = new ProdutoBinding();
		statusItem = StatusItemNotaFiscal.INCLUSAO;
	}

	private void relacionarItemComProduto() {
		item.setProduto( produto );
		item.setValorUnitario( produto.getPreco() );
	}

	private void setarNovoIdTemporario() {
		ItemBinding itemBinding = (ItemBinding) item;
		itemBinding.setIdTemporario( geradorIdTemporario.getAndIncrement() );
	}
	
	public void selecionarItem( ItemMutavelTemporario item ){
		this.item = item;
		this.produto = item.getProduto();
		statusItem = StatusItemNotaFiscal.ALTERACAO;
	}
	
	public void excluirItem( ItemMutavelTemporario item ){
		int posicaoItemAExcluir = localizarPosicaoNaListaItem( item );
		
		if( posicaoItemAExcluir > chaveNaoEncontrada ){
			notaFiscal.getItens().remove( posicaoItemAExcluir );
		}
		
		resetarBindingsTemporarios();
	}
	
	public NotaFiscalMutavel getNotaFiscal() {
		return notaFiscal;
	}

	public void setNotaFiscal( final NotaFiscalMutavel notaFiscal ) {
		this.notaFiscal = notaFiscal;
	}
	
	public ItemMutavel getItem() {
		return item;
	}
	
	public ProdutoMutavel getProduto() {
		return produto;
	}

	public void setProduto(final ProdutoMutavel produtoBinding) {
		this.produto = produtoBinding;
	}
}
