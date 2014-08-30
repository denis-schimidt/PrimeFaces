package br.com.schimidtsolutions.jsf.dao.constant;

import org.apache.commons.lang.StringUtils;

public enum MetodoOrdenacao {
	ASCENDENTE( "ASC" ), DESCENDENTE( "DESC" ), NENHUM;
	
	private String descricao;

	private MetodoOrdenacao(String valor) {
		this.descricao = valor;
	}
	
	private MetodoOrdenacao() {}
	
	public String getDescricao() {
		return descricao;
	}
	
	public boolean isAscendente(){
		return this == ASCENDENTE;
	}
	
	public static MetodoOrdenacao getInstance( String valor ){
		
		if( StringUtils.isBlank( valor ) ){
			return NENHUM;
		}
		
		return valueOf( MetodoOrdenacao.class, valor.toUpperCase() );
	}
}
