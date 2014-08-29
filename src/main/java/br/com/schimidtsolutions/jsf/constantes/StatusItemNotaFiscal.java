package br.com.schimidtsolutions.jsf.constantes;

public enum StatusItemNotaFiscal {
	ALTERACAO( "Editar Item", "Alterar", true ), INCLUSAO( "Cadastrar Item", "Adicionar", false );
	
	private String legendaTelaItem;
	private String labelBotaoItem;
	private boolean edicao; 
	
	private StatusItemNotaFiscal(String legendaTelaItem, String labelBotaoItem, boolean isEdicao) {
		this.legendaTelaItem = legendaTelaItem;
		this.labelBotaoItem = labelBotaoItem;
		this.edicao = isEdicao;
	}

	public String getLegendaTelaItem() {
		return legendaTelaItem;
	}

	public String getLabelBotaoItem() {
		return labelBotaoItem;
	}
	
	public boolean isEdicao() {
		return edicao;
	}
}
