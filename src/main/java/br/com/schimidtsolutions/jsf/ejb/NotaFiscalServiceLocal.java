package br.com.schimidtsolutions.jsf.ejb;

import br.com.schimidtsolutions.jsf.common.interfaces.NotaFiscalMutavel;

public interface NotaFiscalServiceLocal {

	public abstract void cadastrarNotaFiscal(NotaFiscalMutavel notaFiscalMutavel);
}