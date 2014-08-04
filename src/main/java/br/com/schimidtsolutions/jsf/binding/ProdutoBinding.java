package br.com.schimidtsolutions.jsf.binding;

import java.math.BigDecimal;

import br.com.schimidtsolutions.jsf.entity.Produto;

public class ProdutoBinding implements Binding<Produto, ProdutoBinding>{
	private static final long serialVersionUID = 3695699891016105184L;
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

	@Override
	public ProdutoBinding fromEntity( Produto produto ){
		this.id = produto.getId();
		this.nome = produto.getNome();
		this.descricao = produto.getDescricao();
		this.preco = produto.getPreco();
		
		return this;
	}
	
	@Override
	public Produto toEntity(){
		return new Produto( id, nome, descricao, preco);
	}
}
