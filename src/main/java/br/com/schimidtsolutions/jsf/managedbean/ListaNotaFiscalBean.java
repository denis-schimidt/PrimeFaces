package br.com.schimidtsolutions.jsf.managedbean;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.LazyDataModel;

import br.com.schimidtsolutions.jsf.annotation.qualifier.NotaFiscalDataModel;
import br.com.schimidtsolutions.jsf.modelo.NotaFiscal;

@ViewScoped
@Named
public class ListaNotaFiscalBean implements Serializable {
	private static final long serialVersionUID = 3707925457925673117L;

	@Inject @NotaFiscalDataModel @RequestScoped
	private LazyDataModel<NotaFiscal> dataModel;
	
	public LazyDataModel<NotaFiscal> getDataModel() {
		return dataModel;
	}
}
