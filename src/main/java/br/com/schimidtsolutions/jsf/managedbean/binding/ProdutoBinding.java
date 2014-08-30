package br.com.schimidtsolutions.jsf.managedbean.binding;

import java.math.BigDecimal;

import br.com.schimidtsolutions.jsf.client.dto.ProdutoDTO;
import br.com.schimidtsolutions.jsf.client.interfaces.ProdutoMutavel;
import br.com.schimidtsolutions.jsf.interfaces.CopiavelPara;
import br.com.schimidtsolutions.jsf.managedbean.annotation.Binding;
import br.com.schimidtsolutions.jsf.modelo.Produto;

@Binding
public class ProdutoBinding implements ProdutoMutavel, CopiavelPara<Produto> {
	private static final long serialVersionUID = 7907409851575219810L;
	private final ProdutoMutavel produtoMutavel;

	public ProdutoBinding() {
		produtoMutavel = new ProdutoDTO();
	}
	
	public ProdutoBinding(final Produto produto) {
		this();
		
		produtoMutavel.setDescricao( produto.getDescricao() );
		produtoMutavel.setId( produto.getId() );
		produtoMutavel.setNome( produto.getNome() );
		produtoMutavel.setPreco( produto.getPreco() );
	}
	
	public ProdutoBinding(final ProdutoBinding produtoMutavel) {
		this.produtoMutavel = produtoMutavel;
	}

	@Override
	public String toString() {
		return String.format("ProdutoBinding [produtoMutavel=%s]",
				produtoMutavel);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ (produtoMutavel == null ? 0 : produtoMutavel.hashCode());
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
		final ProdutoBinding other = (ProdutoBinding) obj;
		if (produtoMutavel == null) {
			if (other.produtoMutavel != null) {
				return false;
			}
		} else if (!produtoMutavel.equals(other.produtoMutavel)) {
			return false;
		}
		return true;
	}

	@Override
	public Integer getId() {
		return produtoMutavel.getId();
	}

	@Override
	public void setId(final Integer id) {
		produtoMutavel.setId(id);
	}

	@Override
	public String getNome() {
		return produtoMutavel.getNome();
	}

	@Override
	public void setNome(final String nome) {
		produtoMutavel.setNome(nome);
	}

	@Override
	public String getDescricao() {
		return produtoMutavel.getDescricao();
	}

	@Override
	public void setDescricao(final String descricao) {
		produtoMutavel.setDescricao(descricao);
	}

	@Override
	public BigDecimal getPreco() {
		return produtoMutavel.getPreco();
	}

	@Override
	public void setPreco(final BigDecimal preco) {
		produtoMutavel.setPreco(preco);
	}

	@Override
	public Produto toEntity() {
		return new Produto.Builder()
			.comId(getId())
			.descritoComo(getDescricao())
			.nome(getNome())
			.preco(getPreco())
			.create();
	}
}
