package br.com.schimidtsolutions.jsf.managedbean.binding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.joda.time.LocalDate;

import br.com.schimidtsolutions.jsf.entidades.Item;
import br.com.schimidtsolutions.jsf.entidades.NotaFiscal;
import br.com.schimidtsolutions.jsf.interfaces.BindingCopiavel;
import br.com.schimidtsolutions.jsf.managedbean.interfaces.ItemBinding;
import br.com.schimidtsolutions.jsf.managedbean.interfaces.NotaFiscalBinding;

public class NotaFiscalBindingCopiavel implements BindingCopiavel<NotaFiscal>, NotaFiscalBinding {
	private static final long serialVersionUID = 2194520704899746373L;
	private Integer id;
	private String cnpj;
	private LocalDate data;
	private List<ItemBinding> itensBinding;
	
	@Override
	public NotaFiscal paraEntidade() {
		
		final NotaFiscal notaFiscal = new NotaFiscal.Builder()
			.cnpj(cnpj)
			.comId(id)
			.naData(data)
			.create();
		
		for( final ItemBinding itemBinding : itensBinding ){
			final ItemBindingCopiavel itemCopiavel = (ItemBindingCopiavel) itemBinding;
			itemCopiavel.setNotaFiscal(notaFiscal);
			
			notaFiscal.addItem( itemCopiavel.paraEntidade() );
		}
		
		return notaFiscal;
	}
	
	@Override
	public void copiarDaEntidade(final NotaFiscal entidade) {
		setCnpj( entidade.getCnpj() );
		setData( entidade.getData() );
		setId( entidade.getId() );

		final List<ItemBinding> itensBinding = new ArrayList<>();
		
		for (final Item item : entidade.getItens() ) {
			
			final ItemBindingCopiavel itemBinding = new ItemBindingCopiavel();
			itemBinding.copiarDaEntidade(item);
			
			itensBinding.add(itemBinding);
		}
		
		setItens( itensBinding );
	}
	
	@Override
	public String toString() {
		final int maxLen = 10;
		return String.format("NotaFiscalBindingCopiavel [id=%s, cnpj=%s, data=%s, itensBinding=%s]",
					id, cnpj, data, itensBinding != null ? 
							itensBinding.subList(0, Math.min(itensBinding.size(), maxLen)) : null);
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
	public void setItens(final List<ItemBinding> itens) {
		itensBinding = Collections.unmodifiableList( itens );
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
	public List<ItemBinding> getItens() {
		return itensBinding;
	}
}
