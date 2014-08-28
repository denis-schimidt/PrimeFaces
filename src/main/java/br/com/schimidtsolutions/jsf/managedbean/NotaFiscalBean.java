package br.com.schimidtsolutions.jsf.managedbean;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.schimidtsolutions.jsf.common.interfaces.ItemMutavel;
import br.com.schimidtsolutions.jsf.common.interfaces.NotaFiscalMutavel;
import br.com.schimidtsolutions.jsf.common.interfaces.ProdutoMutavel;
import br.com.schimidtsolutions.jsf.ejb.NotaFiscalServiceLocal;
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
	private ItemMutavel itemMutavel;
	
	@Inject @Binding
	private NotaFiscalMutavel notaFiscalMutavel;

	@Inject
	private NotaFiscalServiceLocal notaFiscalService;
	
	private ProdutoMutavel produtoBinding;
	
	@Logged
	public void salvarNotaFiscal(){
		notaFiscalService.cadastrarNotaFiscal( notaFiscalMutavel );
		
		notaFiscalMutavel = new NotaFiscalBinding();
	}
	
	public void incluirItemNotaFiscal(){		
		
		if( produtoBinding != null ){
			itemMutavel.setProduto( produtoBinding );
			notaFiscalMutavel.getItens().add( itemMutavel );
		}
		
		itemMutavel = new ItemBinding();
		produtoBinding = new ProdutoBinding();
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

	public void setItem(final ItemMutavel item) {
		itemMutavel = item;
	}

	public ProdutoMutavel getProdutoBinding() {
		return produtoBinding;
	}

	public void setProdutoBinding(final ProdutoMutavel produtoBinding) {
		this.produtoBinding = produtoBinding;
	}
}
