package br.com.schimidtsolutions.jsf.managedbean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.LazyDataModel;

import br.com.schimidtsolutions.jsf.annotation.qualifier.NotaFiscalDataModel;
import br.com.schimidtsolutions.jsf.modelo.NotaFiscal;

@RequestScoped
@Named
public class ListaNotaFiscalBean {
	
	@Inject @NotaFiscalDataModel @RequestScoped
	private LazyDataModel<NotaFiscal> dataModel;
	
	public LazyDataModel<NotaFiscal> getDataModel() {
		return dataModel;
	}
}
