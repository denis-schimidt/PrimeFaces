package br.com.schimidtsolutions.jsf.managedbean.binding;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDate;

import br.com.schimidtsolutions.jsf.client.dto.NotaFiscalDTO;
import br.com.schimidtsolutions.jsf.client.interfaces.ItemMutavel;
import br.com.schimidtsolutions.jsf.client.interfaces.NotaFiscalMutavel;
import br.com.schimidtsolutions.jsf.interfaces.CopiavelPara;
import br.com.schimidtsolutions.jsf.managedbean.annotation.Binding;
import br.com.schimidtsolutions.jsf.modelo.Item;
import br.com.schimidtsolutions.jsf.modelo.NotaFiscal;

@Binding
public class NotaFiscalBinding implements NotaFiscalMutavel, CopiavelPara<NotaFiscal> {
	private static final long serialVersionUID = 5770627898531756852L;
	private final NotaFiscalMutavel notaFiscalMutavel;

	public NotaFiscalBinding() {
		notaFiscalMutavel = new NotaFiscalDTO();
	}

	public NotaFiscalBinding( final NotaFiscal notaFiscal, boolean comItems ) {
		this();
		
		notaFiscalMutavel.setCnpj( notaFiscal.getCnpj() );
		notaFiscalMutavel.setData( notaFiscal.getData() );
		notaFiscalMutavel.setId( notaFiscal.getId() );
		
		if( comItems ){
			notaFiscalMutavel.setItens( new ArrayList<>() );
			
			for (final Item item : notaFiscal.getItens() ) {
				notaFiscalMutavel.getItens().add( new ItemBinding(item) );
			} 
		}
	}
	
	public NotaFiscalBinding( final NotaFiscal notaFiscal ) {
		this( notaFiscal, true );
	}
	
	public NotaFiscalBinding(final NotaFiscalMutavel notaFiscalMutavel) {
		this.notaFiscalMutavel = notaFiscalMutavel;
	}

	@Override
	public void setId(final Integer id) {
		notaFiscalMutavel.setId(id);
	}

	@Override
	public void setCnpj(final String cnpj) {
		notaFiscalMutavel.setCnpj(cnpj);
	}

	@Override
	public void setData(final LocalDate data) {
		notaFiscalMutavel.setData(data);
	}

	@Override
	public void setItens(final List<ItemMutavel> itens) {
		notaFiscalMutavel.setItens(itens);
	}

	@Override
	public Integer getId() {
		return notaFiscalMutavel.getId();
	}

	@Override
	public String getCnpj() {
		return notaFiscalMutavel.getCnpj();
	}

	@Override
	public LocalDate getData() {
		return notaFiscalMutavel.getData();
	}

	@Override
	public List<ItemMutavel> getItens() {
		return notaFiscalMutavel.getItens();
	}

	@Override
	public String toString() {
		return String.format("NotaFiscalBinding [notaFiscalMutavel=%s]",notaFiscalMutavel);
	}

	@Override
	public NotaFiscal toEntity() {
		
		final NotaFiscal notaFiscal = new NotaFiscal.Builder()
			.cnpj( getCnpj() )
			.comId( getId() )
			.naData( getData() )
			.create();
		
		return notaFiscal;
	}
}
