package br.com.schimidtsolutions.jsf.binding;

import java.math.BigDecimal;

public class Produto{
	private Integer id;
	private String nome;
	private String descricao;
	private BigDecimal preco;
	
	@Override
	public String toString() {
		return String.format(
				"Produto [id=%s, nome=%s, descricao=%s, preco=%s]", id, nome,
				descricao, preco);
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public BigDecimal getPreco() {
		return preco;
	}
	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}
}
