package br.com.schimidtsolutions.jsf.constantes;

public enum StatusItemNotaFiscal {
	ALTERACAO( "Editando item...", "Alterar", true, 1 ), INCLUSAO( "Novo item", "Adicionar", false, 0 );
	
	private String legendaTelaItem;
	private String labelBotaoItem;
	private boolean edicao; 
	private int indiceTab;
	
	private StatusItemNotaFiscal(String legendaTelaItem, String labelBotaoItem, boolean isEdicao, int indiceTab ) {
		this.legendaTelaItem = legendaTelaItem;
		this.labelBotaoItem = labelBotaoItem;
		this.edicao = isEdicao;
		this.indiceTab = indiceTab;
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
	
	public int getIndiceTab() {
		return indiceTab;
	}
}
