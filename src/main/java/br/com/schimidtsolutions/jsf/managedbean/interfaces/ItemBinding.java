package br.com.schimidtsolutions.jsf.managedbean.interfaces;

import java.io.Serializable;
import java.math.BigDecimal;

public interface ItemBinding extends Serializable {

	public abstract void setId(Integer id);

	public abstract void setProduto(ProdutoBinding produto);

	public abstract void setQuantidade(Integer quantidade);

	public abstract void setValorUnitario(BigDecimal valorUnitario);

	public abstract Integer getId();

	public abstract ProdutoBinding getProduto();

	public abstract Integer getQuantidade();

	public abstract BigDecimal getValorUnitario();

}