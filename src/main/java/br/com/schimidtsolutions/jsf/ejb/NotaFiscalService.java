package br.com.schimidtsolutions.jsf.ejb;

import br.com.schimidtsolutions.jsf.client.interfaces.NotaFiscalMutavel;

public interface NotaFiscalService {

	public abstract void cadastrarNotaFiscal(NotaFiscalMutavel notaFiscalMutavel);
}