package br.com.schimidtsolutions.jsf.datamodel;

import java.util.List;
import java.util.Map;

import javax.faces.convert.Converter;
import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.schimidtsolutions.jsf.annotation.qualifier.LocalDateConverter;
import br.com.schimidtsolutions.jsf.annotation.qualifier.NotaFiscalDataModel;
import br.com.schimidtsolutions.jsf.annotation.qualifier.PaginacaoInicial;
import br.com.schimidtsolutions.jsf.dao.DAO;
import br.com.schimidtsolutions.jsf.dao.constant.MetodoOrdenacao;
import br.com.schimidtsolutions.jsf.dao.helper.Ordenacao;
import br.com.schimidtsolutions.jsf.dao.helper.Paginacao;
import br.com.schimidtsolutions.jsf.modelo.NotaFiscal;

@NotaFiscalDataModel 
public class ListaNotaFiscalDataModel extends LazyDataModel<NotaFiscal> {
	private static final long serialVersionUID = -8736259536338390027L;
	
	private static final String CAMPO_DATA = "data"; 
	
	@Inject
	private DAO<NotaFiscal> dao;
	
	@Inject @LocalDateConverter
	private Converter localDateConverter;

	@Inject
	private void init( @PaginacaoInicial int tamanhoInicialPaginacao ){
		setRowCount( dao.contarTudo().intValue() );
		setPageSize( tamanhoInicialPaginacao );
	}
	
	@Override
	public List<NotaFiscal> load( final int first, final int pageSize, final String sortField, final SortOrder sortOrder, final Map<String, Object> filters ) {
		
		final Paginacao paginacao = new Paginacao(first, pageSize);
		
		if( StringUtils.isNotBlank( sortField ) ){
			setarOrdenacao(sortField, sortOrder, paginacao);
		}
		
		setarFiltros(filters, paginacao);
		setRowCount( dao.contarComFiltro( paginacao.getFiltros() ).intValue() );
		
		return dao.listarComPaginacaoOrdenacaoEFiltros( paginacao );
	}

	private void setarFiltros(final Map<String, Object> filters, final Paginacao paginacao) {
		ajustarFiltrosPesquisa(filters);
		
		paginacao.setFiltros(filters);
	}

	private void ajustarFiltrosPesquisa(final Map<String, Object> filters) {
		
		if( filters != null && filters.containsKey( CAMPO_DATA ) ){
			
			try{
				Object localDate = localDateConverter.getAsObject( null, null, filters.get( CAMPO_DATA ).toString() );
				filters.put( CAMPO_DATA, localDate );
				
			}catch( Exception e ){
				filters.remove( CAMPO_DATA );
			}
		}
	}

	private void setarOrdenacao(final String sortField, final SortOrder sortOrder, final Paginacao paginacao) {
		MetodoOrdenacao metodoOrdenacao =  MetodoOrdenacao.DESCENDENTE;
		
		if( sortOrder != null && sortOrder == SortOrder.ASCENDING ){
			metodoOrdenacao = MetodoOrdenacao.ASCENDENTE;
		}
		
		Ordenacao ordenacao = new Ordenacao( metodoOrdenacao, sortField );
		paginacao.setOrdenacao(ordenacao);
	}
}
