package br.com.schimidtsolutions.jsf.client.dto;

import java.math.BigDecimal;

import br.com.schimidtsolutions.jsf.client.interfaces.ProdutoMutavel;

public class ProdutoDTO implements ProdutoMutavel{
	private static final long serialVersionUID = -9132635464789704783L;
	private Integer id;
	private String nome;
	private String descricao;
	private BigDecimal preco;
	
	public ProdutoDTO() {}
	
	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(final Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return String.format( "ProdutoDTO [id=%s, nome=%s, descricao=%s, preco=%s]", id, nome, descricao, preco);
	}

	@Override
	public String getNome() {
		return nome;
	}

	@Override
	public void setNome(final String nome) {
		this.nome = nome;
	}

	@Override
	public String getDescricao() {
		return descricao;
	}

	@Override
	public void setDescricao(final String descricao) {
		this.descricao = descricao;
	}

	@Override
	public BigDecimal getPreco() {
		return preco;
	}

	@Override
	public void setPreco(final BigDecimal preco) {
		this.preco = preco;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (id == null ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final ProdutoDTO other = (ProdutoDTO) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}
}
