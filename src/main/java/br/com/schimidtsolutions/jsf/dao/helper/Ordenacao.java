package br.com.schimidtsolutions.jsf.dao.helper;

import javax.validation.constraints.NotNull;

import br.com.schimidtsolutions.jsf.dao.constant.MetodoOrdenacao;
import br.com.schimidtsolutions.jsf.validation.NaoVazio;

public class Ordenacao {
	
	@NotNull(message="O método de ordenação não pode ser nulo! ")
	private MetodoOrdenacao metodoOrdenacao;
	
	@NaoVazio(message="O nome do campo ordenado não pode ser nulo ou vazio!")
	private String campoOrdenado;

	public Ordenacao( String campo ) {
		this( MetodoOrdenacao.DESCENDENTE, campo );
	}
	
	public Ordenacao( MetodoOrdenacao metodoOrdenacao, String campo ) {
		this.metodoOrdenacao = metodoOrdenacao;
		this.campoOrdenado = campo;
	}
	
	@Override
	public String toString() {
		return String.format( "Ordenacao [metodoOrdenacao=%s, campoOrdenado=%s]", metodoOrdenacao, campoOrdenado);
	}

	public MetodoOrdenacao getMetodoOrdenacao() {
		return metodoOrdenacao;
	}

	public String getCampoOrdenado() {
		return campoOrdenado;
	}
}
