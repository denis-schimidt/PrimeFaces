package br.com.schimidtsolutions.jsf.entidades;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.schimidtsolutions.jsf.constantes.ComparacaoObjetos;

@Entity
@Table(schema="development", name="ITEM")
public class Item implements Comparable<Item> {

	@Id
	@SequenceGenerator( schema="development", sequenceName="ITEM_SEQ", initialValue=1, allocationSize=1, name="ItemGenerator")
	@GeneratedValue(generator="ItemGenerator", strategy=GenerationType.SEQUENCE)
	@Column(name="ID", insertable=true, nullable=false, updatable=false )
	@OrderBy
	private Integer id;
	
	@ManyToOne
	private NotaFiscal notaFiscal;
	
	@ManyToOne(cascade=CascadeType.PERSIST )
	private Produto produto;
	
	@Column(name="QUANTIDADE", insertable=true, nullable=false, updatable=true )
	private Integer quantidade;
	
	@Column(name="VALOR_UNITARIO", insertable=true, nullable=false, updatable=true )
	private BigDecimal valorUnitario;

	Item() {}
	
	private Item( final Builder builder ){
		id = builder.id;
		notaFiscal = builder.notaFiscal;
		produto = builder.produto;
		quantidade = builder.quantidade;
		valorUnitario = builder.valorUnitario;
	}
	
	void setNotaFiscal(final NotaFiscal notaFiscal) {
		this.notaFiscal = notaFiscal;
	}

	@Override
	public String toString() {
		return String.format("ItemEntity [id=%s, notaFiscal=%s, produto=%s, quantidade=%s, valorUnitario=%s]",
			id, notaFiscal, produto, quantidade, valorUnitario);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (produto == null ? 0 : produto.hashCode());
		result = prime * result + (quantidade == null ? 0 : quantidade.hashCode());
		result = prime * result	+ (valorUnitario == null ? 0 : valorUnitario.hashCode());
		
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
		
		final Item other = (Item) obj;
		if (produto == null) {
			if (other.produto != null) {
				return false;
			}
		} else if (!produto.equals(other.produto)) {
			return false;
		}
		if (quantidade == null) {
			if (other.quantidade != null) {
				return false;
			}
		} else if (!quantidade.equals(other.quantidade)) {
			return false;
		}
		if (valorUnitario == null) {
			if (other.valorUnitario != null) {
				return false;
			}
		} else if (!valorUnitario.equals(other.valorUnitario)) {
			return false;
		}
		return true;
	}

	public Integer getId() {
		return id;
	}

	public NotaFiscal getNotaFiscal() {
		return notaFiscal;
	}

	public Produto getProduto() {
		return produto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public BigDecimal getValorUnitario() {
		return valorUnitario;
	}
	
	public static class Builder{
		private Integer id;
		private NotaFiscal notaFiscal;
		private Produto produto;
		private Integer quantidade;
		private BigDecimal valorUnitario;
		
		public Builder comId(final Integer id) {
			this.id = id;
			
			return this;
		}
		
		public Builder naNotaFiscal(final NotaFiscal notaFiscal) {
			this.notaFiscal = notaFiscal;
			
			return this;
		}
		
		public Builder tendoComoProduto(final Produto produto) {
			this.produto = produto;
			
			return this;
		}
		
		public Builder naQuantidade(final Integer quantidade) {
			this.quantidade = quantidade;
			
			return this;
		}
		
		public Builder custandoUnitariamente(final BigDecimal valorUnitario) {
			this.valorUnitario = valorUnitario;
			
			return this;
		}
		
		public Item create(){
			return new Item( this );
		}
	}

	@Override
	public int compareTo(final Item outroItem) {
		final boolean idOutroItemNaoENulo = outroItem != null && outroItem.id != null;
		final boolean idDesteItemNaoENulo = id != null;
		
		if( idDesteItemNaoENulo ){
			
			if( idOutroItemNaoENulo ){
				return id.intValue() - outroItem.id.intValue();
			}
			
			return ComparacaoObjetos.MENOR.getValor();
			
		}else{
			
			if( idOutroItemNaoENulo ){
				return ComparacaoObjetos.MAIOR.getValor();
			}
			
			return ComparacaoObjetos.IGUAL.getValor();
		}			
	}
}
