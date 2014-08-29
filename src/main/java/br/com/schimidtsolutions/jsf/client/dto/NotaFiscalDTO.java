package br.com.schimidtsolutions.jsf.client.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.joda.time.LocalDate;

import br.com.schimidtsolutions.jsf.client.interfaces.ItemMutavel;
import br.com.schimidtsolutions.jsf.client.interfaces.NotaFiscalMutavel;

public class NotaFiscalDTO implements NotaFiscalMutavel {
	private static final long serialVersionUID = 2194520704899746373L;
	private Integer id;
	private String cnpj;
	private LocalDate data;
	private List<ItemMutavel> itens = new ArrayList<>();
	
	@Override
	public String toString() {
		final int maxLen = 10;
		return String.format("NotaFiscalBindingCopiavel [id=%s, cnpj=%s, data=%s, itensBinding=%s]",
					id, cnpj, data, itens != null ? itens.subList(0, Math.min(itens.size(), maxLen)) : null);
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
		final NotaFiscalDTO other = (NotaFiscalDTO) obj;
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
	public void setCnpj(final String cnpj) {
		this.cnpj = cnpj;
	}
	@Override
	public void setData(final LocalDate data) {
		this.data = data;
	}
	@Override
	public void setItens(final List<ItemMutavel> itens) {
		this.itens = Collections.unmodifiableList( itens );
	}
	@Override
	public Integer getId() {
		return id;
	}
	@Override
	public String getCnpj() {
		return cnpj;
	}
	@Override
	public LocalDate getData() {
		return data;
	}
	@Override
	public List<ItemMutavel> getItens() {
		return itens;
	}
}
