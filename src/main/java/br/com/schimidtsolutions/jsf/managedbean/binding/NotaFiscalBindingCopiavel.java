package br.com.schimidtsolutions.jsf.managedbean.binding;

import java.util.Collections;
import java.util.List;

import org.joda.time.LocalDate;

import br.com.schimidtsolutions.jsf.entity.Item;
import br.com.schimidtsolutions.jsf.entity.NotaFiscal;
import br.com.schimidtsolutions.jsf.interfaces.BindingSimplesCopiavel;
import br.com.schimidtsolutions.jsf.managedbean.interfaces.NotaFiscalBinding;

public class NotaFiscalBindingCopiavel implements BindingSimplesCopiavel<NotaFiscal>, NotaFiscalBinding {
	private Integer id;
	private String cnpj;
	private LocalDate data;
	private List<Item> itens;
	
	@Override
	public NotaFiscal paraEntidade() {
		return new NotaFiscal.Builder()
			.cnpj(cnpj)
			.comId(id)
			.naData(data)
			.tendoComoItens(itens)
			.create();
	}
	
	@Override
	public void copiarDaEntidade(final NotaFiscal entidade) {
		setCnpj( entidade.getCnpj() );
		setData( entidade.getData() );
		setId( entidade.getId() );
		setItens( entidade.getItens() );
	}
	
	@Override
	public String toString() {
		final int maxLen = 10;
		return String.format("NotaFiscalBindingCopiavel [id=%s, cnpj=%s, data=%s, itens=%s]",
					id, cnpj, data, itens != null ? itens.subList(0,Math.min(itens.size(), maxLen)) : null);
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
	public void setItens(final List<Item> itens) {
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
	public List<Item> getItens() {
		return itens;
	}
}
