package br.com.schimidtsolutions.jsf.managedbean;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.schimidtsolutions.jsf.dao.DAO;
import br.com.schimidtsolutions.jsf.entidades.NotaFiscal;
import br.com.schimidtsolutions.jsf.log.Logged;
import br.com.schimidtsolutions.jsf.managedbean.binding.ItemBindingCopiavel;
import br.com.schimidtsolutions.jsf.managedbean.binding.NotaFiscalBindingCopiavel;
import br.com.schimidtsolutions.jsf.managedbean.interfaces.ItemBinding;
import br.com.schimidtsolutions.jsf.managedbean.interfaces.NotaFiscalBinding;

@Named
@ViewScoped
public class NotaFiscalBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private ItemBinding item;
	
	@Inject
	private NotaFiscalBinding notaFiscal;
	
	@Inject
	private DAO<NotaFiscal> daoNotaFiscal;
	
	@Logged
	public void salvar(){
		final NotaFiscalBindingCopiavel notaCopiavel = (NotaFiscalBindingCopiavel) notaFiscal;
		//daoNotaFiscal.adicionar( notaCopiavel.paraEntidade() );	
		
		resetBindings();
	}
	
	public NotaFiscalBinding getNotaFiscal() {
		return notaFiscal;
	}

	public void setNotaFiscal( final NotaFiscalBinding notaFiscal) {
		this.notaFiscal = notaFiscal;
	}
	
	private void resetBindings(){
		item = new ItemBindingCopiavel();
		notaFiscal = new NotaFiscalBindingCopiavel();
	}
}
