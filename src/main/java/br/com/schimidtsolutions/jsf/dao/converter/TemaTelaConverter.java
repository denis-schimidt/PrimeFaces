package br.com.schimidtsolutions.jsf.dao.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import br.com.schimidtsolutions.jsf.constantes.TemaTela;

@Converter(autoApply=true)
public class TemaTelaConverter implements AttributeConverter<TemaTela, String> {

	@Override
	public String convertToDatabaseColumn(TemaTela temaTela) {
		return temaTela.getNomeReal();
	}

	@Override
	public TemaTela convertToEntityAttribute(String nomeRealTema) {
		return TemaTela.getInstancePeloNomeReal( nomeRealTema );
	}
}
