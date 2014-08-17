package br.com.schimidtsolutions.jsf.managedbean.interfaces;

import java.io.Serializable;
import java.util.List;

import org.joda.time.LocalDate;

public interface NotaFiscalBinding extends Serializable {

	public abstract void setId(Integer id);

	public abstract void setCnpj(String cnpj);

	public abstract void setData(LocalDate data);

	public abstract void setItens(List<ItemBinding> itens);

	public abstract Integer getId();

	public abstract String getCnpj();

	public abstract LocalDate getData();

	public abstract List<ItemBinding> getItens();

}