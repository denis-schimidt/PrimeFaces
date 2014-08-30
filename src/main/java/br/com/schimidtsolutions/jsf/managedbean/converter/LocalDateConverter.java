package br.com.schimidtsolutions.jsf.managedbean.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;

@FacesConverter(value="localDateConverter")
@br.com.schimidtsolutions.jsf.annotation.qualifier.LocalDateConverter
public class LocalDateConverter implements Converter {
	private static final String PADRAO_DATA = "dd/MM/yyyy";
	
	@Override
	public Object getAsObject(final FacesContext context, final UIComponent component, final String value) {
		final LocalDate localDate = LocalDate.parse(value, DateTimeFormat.forPattern( PADRAO_DATA ) );
		
		return localDate;
	}

	@Override
	public String getAsString(final FacesContext context, final UIComponent component, final Object value) {
		final LocalDate localDate = (LocalDate) value;
		
		return localDate.toString( DateTimeFormat.forPattern( PADRAO_DATA ) );
	}
}
