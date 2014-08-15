package br.com.schimidtsolutions.jsf.constant;

public enum LegendaTelaProduto {
	CADASTRAR_PRODUTO("Cadastrar Produto"), EDITAR_PRODUTO("Editar Produto");
	
	private String descricaoLegenda;

	private LegendaTelaProduto(final String descricaoLegenda) {
		this.descricaoLegenda = descricaoLegenda;
	}

	public String getDescricaoLegenda() {
		return descricaoLegenda;
	}
}
