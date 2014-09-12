package br.com.schimidtsolutions.jsf.producer;

import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;

import br.com.schimidtsolutions.jsf.constantes.TemaTela;

import com.google.common.collect.ImmutableList;

public class ThemeProducer {

	@Produces
	@SessionScoped
	List<TemaTela> newListTemas() {
		return ImmutableList.copyOf( TemaTela.values() );
	}
}
