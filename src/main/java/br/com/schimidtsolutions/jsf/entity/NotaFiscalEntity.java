package br.com.schimidtsolutions.jsf.entity;

import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import br.com.caelum.stella.bean.validation.CNPJ;

@Entity
@Table(schema="development", name="NOTA_FISCAL")
public class NotaFiscalEntity {

	@Id
	@SequenceGenerator( schema="development", sequenceName="NOTA_FISCAL_SEQ", initialValue=1, allocationSize=1, name="NotaFiscalGenerator")
	@GeneratedValue(generator="NotaFiscalGenerator", strategy=GenerationType.SEQUENCE)
	@Column(name="ID", insertable=true, nullable=false, updatable=false )
	private Integer id;
	
	@CNPJ
	@NotNull(message="O CNPJ não pode ser nulo")
	@Column(name="CNPJ", length=14, insertable=true, nullable=false, updatable=true )
	private String cnpj;
	
	@Temporal(TemporalType.DATE)
	@Past(message="Data precisa ser menor ou igual que a data atual")
	@Column(name="DATA", insertable=true, nullable=false, updatable=true )
	private Calendar data;

	@OneToMany(cascade={CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH, CascadeType.REMOVE}, fetch=FetchType.LAZY, mappedBy="notaFiscal")
	private List<ItemEntity> itens;
	
	NotaFiscalEntity() {}
	
	public NotaFiscalEntity(String cnpj, Calendar data) {
		this.cnpj = cnpj;
		this.data = data;
	}
	
	public NotaFiscalEntity(Integer id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		final int maxLen = 10;
		return String.format("NotaFiscalEntity [id=%s, cnpj=%s, data=%s, itens=%s]",
			id, cnpj, data, itens != null ? itens.subList(0, Math.min(itens.size(), maxLen)) : null);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		NotaFiscalEntity other = (NotaFiscalEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Integer getId() {
		return id;
	}

	public String getCnpj() {
		return cnpj;
	}

	public Calendar getData() {
		return data;
	}
	
	public List<ItemEntity> getItens() {
		return itens;
	}
}
