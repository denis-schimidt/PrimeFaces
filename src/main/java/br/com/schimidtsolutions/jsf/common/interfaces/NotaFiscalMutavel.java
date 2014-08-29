package br.com.schimidtsolutions.jsf.common.interfaces;

import java.io.Serializable;
import java.util.List;

import org.joda.time.LocalDate;

public interface NotaFiscalMutavel extends Serializable {

	public abstract void setId(Integer id);

	public abstract void setCnpj(String cnpj);

	public abstract void setData(LocalDate data);

	public abstract void setItens(List<ItemMutavel> itens);

	public abstract Integer getId();

	public abstract String getCnpj();

	public abstract LocalDate getData();
	
	public abstract List<ItemMutavel> getItens();

}