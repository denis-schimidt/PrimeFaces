package br.com.schimidtsolutions.client.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;

import br.com.schimidtsolutions.jsf.common.interfaces.ItemMutavel;
import br.com.schimidtsolutions.jsf.common.interfaces.ProdutoMutavel;

public class ItemDTO implements ItemMutavel {
	private static final long serialVersionUID = -6675679396380650885L;
	private Integer id;
	private ProdutoMutavel produtoMutavel;
	private Integer quantidade;
	private BigDecimal valorUnitario;

	@Override
	public String toString() {
		return String.format("ItemDTO [id=%s, produtoMutavel=%s, quantidade=%s, valorUnitario=%s]",
						id, produtoMutavel, quantidade, valorUnitario);
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
		final ItemDTO other = (ItemDTO) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}



	@Override
	public void setId(final Integer id) {
		this.id = id;
	}

	@Override
	public void setQuantidade(final Integer quantidade) {
		this.quantidade = quantidade;
	}

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public Integer getQuantidade() {
		return quantidade;
	}
	
	@Override
	public BigDecimal getValorUnitario() {
		return valorUnitario;
	}

	@Override
	public void setValorUnitario(final BigDecimal valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	@Override
	public void setProduto(final ProdutoMutavel produtoMutavel) {
		this.produtoMutavel = produtoMutavel;
	}

	@Override
	public ProdutoMutavel getProduto() {
		return produtoMutavel;
	}

	@Override
	public BigDecimal getValorTotal() {
		return BigDecimal.valueOf( quantidade )
				.multiply( valorUnitario )
					.setScale( 2, RoundingMode.HALF_UP);
	}
}
