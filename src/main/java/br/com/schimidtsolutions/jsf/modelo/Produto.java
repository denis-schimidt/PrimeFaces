package br.com.schimidtsolutions.jsf.modelo;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(schema="development", name="PRODUTO")
public class Produto {
	
	@Id
	@SequenceGenerator( schema="development", sequenceName="PRODUTO_SEQ", initialValue=1, allocationSize=1, name="ProdutoGenerator")
	@GeneratedValue(generator="ProdutoGenerator", strategy=GenerationType.SEQUENCE)
	@Column(name="ID", insertable=true, nullable=false, updatable=false )
	private Integer id;
	
	@Column(name="NOME", insertable=true, length=50, nullable=false, updatable=true )
	private String nome;
	
	@Column(name="DESCRICAO", insertable=true, length=255, nullable=false, updatable=true )
	private String descricao;
	
	@Column(name="PRECO", insertable=true, nullable=false, updatable=true, precision=10, scale=2)
	private BigDecimal preco;

	Produto() {}
	
	private Produto( final Builder builder ) {
		id = builder.id;
		nome = builder.nome;
		descricao = builder.descricao;
		preco = builder.preco;
	}
	
	@Override
	public String toString() {
		return String.format( "ProdutoEntity [id=%s, nome=%s, descricao=%s, preco=%s]", id,
			nome, descricao, preco);
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
		final Produto other = (Produto) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

	public Integer getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public BigDecimal getPreco() {
		return preco;
	}
	
	public static class Builder{
		private Integer id;
		private String nome;
		private String descricao;
		private BigDecimal preco;
		
		public Builder() {}

		public Builder( final Produto produto ){
			id = produto.id;
			nome = produto.nome;
			descricao = produto.descricao;
			preco = produto.preco;
		}
		
		public Builder comId(final Integer id) {
			this.id = id;
			
			return this;
		}

		public Builder nome(final String nome) {
			this.nome = nome;
			
			return this;
		}

		public Builder descritoComo(final String descricao) {
			this.descricao = descricao;
			
			return this;
		}

		public Builder preco(final BigDecimal preco) {
			this.preco = preco;
			
			return this;
		}

		public Integer getId() {
			return id;
		}

		public String getNome() {
			return nome;
		}

		public String getDescricao() {
			return descricao;
		}

		public BigDecimal getPreco() {
			return preco;
		}

		public Produto create(){
			return new Produto( this );
		}
	}
}
