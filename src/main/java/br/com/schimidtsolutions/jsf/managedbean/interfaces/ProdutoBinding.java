package br.com.schimidtsolutions.jsf.managedbean.interfaces;

import java.io.Serializable;
import java.math.BigDecimal;

public interface ProdutoBinding extends Serializable {

	public abstract Integer getId();

	public abstract void setId(Integer id);

	public abstract String getNome();

	public abstract void setNome(String nome);

	public abstract String getDescricao();

	public abstract void setDescricao(String descricao);

	public abstract BigDecimal getPreco();

	public abstract void setPreco(BigDecimal preco);
}