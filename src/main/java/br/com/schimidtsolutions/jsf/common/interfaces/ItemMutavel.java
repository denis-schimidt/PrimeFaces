package br.com.schimidtsolutions.jsf.common.interfaces;

import java.io.Serializable;
import java.math.BigDecimal;

public interface ItemMutavel extends Serializable {

	public abstract void setId(Integer id);

	public abstract void setQuantidade(Integer quantidade);

	public abstract Integer getId();

	public abstract Integer getQuantidade();

	public abstract void setValorUnitario(final BigDecimal valorUnitario);

	public abstract BigDecimal getValorUnitario();
	
	public abstract BigDecimal getValorTotal();

	public abstract void setProduto(final ProdutoMutavel produtoMutavel);

	public abstract ProdutoMutavel getProduto();
}