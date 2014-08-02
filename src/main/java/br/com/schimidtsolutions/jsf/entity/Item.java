package br.com.schimidtsolutions.jsf.entity;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(schema="development", name="ITEM")
public class Item {

	@Id
	@SequenceGenerator( schema="development", sequenceName="ITEM_SEQ", initialValue=1, allocationSize=1, name="ItemGenerator")
	@GeneratedValue(generator="ItemGenerator", strategy=GenerationType.SEQUENCE)
	@Column(name="ID", insertable=true, nullable=false, updatable=false )
	private Integer id;
	
	@ManyToOne(optional=false, cascade=CascadeType.ALL)
	private NotaFiscal notaFiscal;
	
	@ManyToOne(optional=false, cascade=CascadeType.PERSIST)
	private Produto produto;
	
	@Column(name="QUANTIDADE", insertable=true, nullable=false, updatable=true )
	private Integer quantidade;
	
	@Column(name="VALOR_UNITARIO", insertable=true, nullable=false, updatable=true )
	private BigDecimal valorUnitario;

	Item() {}
	
	public Item(Integer id) {
		this.id = id;
	}

	public Item(NotaFiscal notaFiscal, Produto produto, Integer quantidade, BigDecimal valorUnitario) {
		this.notaFiscal = notaFiscal;
		this.produto = produto;
		this.quantidade = quantidade;
		this.valorUnitario = valorUnitario;
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
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((notaFiscal == null) ? 0 : notaFiscal.hashCode());
		result = prime * result + ((produto == null) ? 0 : produto.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (notaFiscal == null) {
			if (other.notaFiscal != null)
				return false;
		} else if (!notaFiscal.equals(other.notaFiscal))
			return false;
		if (produto == null) {
			if (other.produto != null)
				return false;
		} else if (!produto.equals(other.produto))
			return false;
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
}
