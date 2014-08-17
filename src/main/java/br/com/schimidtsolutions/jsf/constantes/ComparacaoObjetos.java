package br.com.schimidtsolutions.jsf.constantes;

public enum ComparacaoObjetos {
	MENOR(-1), IGUAL(0), MAIOR(1);
	
	private int valor;
	
	private ComparacaoObjetos( final int valor ) {
		this.valor = valor;
	}
	
	public int getValor() {
		return valor;
	}
}
