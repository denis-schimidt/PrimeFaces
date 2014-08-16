package br.com.schimidtsolutions.jsf.managedbean.interfaces;

import java.util.List;

import org.joda.time.LocalDate;

import br.com.schimidtsolutions.jsf.entity.Item;

public interface NotaFiscalBinding {

	public abstract void setId(Integer id);

	public abstract void setCnpj(String cnpj);

	public abstract void setData(LocalDate data);

	public abstract void setItens(List<Item> itens);

	public abstract Integer getId();

	public abstract String getCnpj();

	public abstract LocalDate getData();

	public abstract List<Item> getItens();

}