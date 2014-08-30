package br.com.schimidtsolutions.jsf.managedbean.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;

import br.com.schimidtsolutions.jsf.client.interfaces.ProdutoMutavel;
import br.com.schimidtsolutions.jsf.dao.DAOSomenteLeitura;
import br.com.schimidtsolutions.jsf.managedbean.binding.ProdutoBinding;
import br.com.schimidtsolutions.jsf.modelo.Produto;


@FacesConverter("produtoConverter")
public class ProdutoConverter implements Converter {
	
	@Inject
	private DAOSomenteLeitura<Produto> dao;
	
	@Override
	public ProdutoMutavel getAsObject(final FacesContext context, final UIComponent component, final String value) {
		
		if( StringUtils.isNotBlank( value ) && !value.startsWith( "Selecione" ) ){
			final Produto produto = dao.<Integer>pesquisarPorId( Integer.valueOf( value ) );
			
			return new ProdutoBinding( produto );
		}
		
		return new ProdutoBinding();
	}

	@Override
	public String getAsString(final FacesContext context, final UIComponent component, final Object value) {
		
		if( value instanceof ProdutoMutavel ){
			final ProdutoMutavel binding = (ProdutoMutavel) value;
			
			if( binding != null &&  binding.getId() != null ){
				return binding.getId().toString();
			}
		}
		
		return null;
	}
}
