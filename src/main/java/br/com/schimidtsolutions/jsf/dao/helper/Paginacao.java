package br.com.schimidtsolutions.jsf.dao.helper;

import java.util.Map;

import br.com.schimidtsolutions.jsf.validation.Intervalo;

public class Paginacao {
	@Intervalo( menor=1, maior=2000, message="A página inicial deve ter valores entre 1 e 2000!" )
	private int paginaInicial; 
	
	@Intervalo( menor=1, maior=100, message="O tamanho da página deve estar entre 1 e 100!" )
	private int tamanhoPagina;
	
	private Ordenacao ordenacao;
	private Map<String, Object> filtros;
	
	public Paginacao( int paginaInicial, int tamanhoPagina ) {
		this.paginaInicial = paginaInicial;
		this.tamanhoPagina = tamanhoPagina;
	}
	
	public Ordenacao getOrdenacao() {
		return ordenacao;
	}

	public void setOrdenacao(Ordenacao ordenacao) {
		this.ordenacao = ordenacao;
	}
	
	public Map<String, Object> getFiltros() {
		return filtros;
	}

	public void setFiltros(Map<String, Object> filtros) {
		this.filtros = filtros;
	}

	public int getPaginaInicial() {
		return paginaInicial;
	}

	public int getTamanhoPagina() {
		return tamanhoPagina;
	}
}
