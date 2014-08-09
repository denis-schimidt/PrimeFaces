package br.com.schimidtsolutions.jsf.binding;

import java.math.BigDecimal;

public interface IProduto {

	public abstract Integer getId();

	public abstract String getNome();

	public abstract String getDescricao();

	public abstract BigDecimal getPreco();

}