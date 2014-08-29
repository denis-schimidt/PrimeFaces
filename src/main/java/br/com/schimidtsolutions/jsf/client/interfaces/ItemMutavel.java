package br.com.schimidtsolutions.jsf.client.interfaces;

import java.io.Serializable;
import java.math.BigDecimal;

public interface ItemMutavel extends Serializable, Comparable<ItemMutavel> {

	public abstract void setId(Long id);

	public abstract void setQuantidade(Integer quantidade);

	public abstract Long getId();

	public abstract Integer getQuantidade();

	public abstract void setValorUnitario(final BigDecimal valorUnitario);

	public abstract BigDecimal getValorUnitario();
	
	public abstract BigDecimal getValorTotal();

	public abstract void setProduto(final ProdutoMutavel produtoMutavel);

	public abstract ProdutoMutavel getProduto();
}