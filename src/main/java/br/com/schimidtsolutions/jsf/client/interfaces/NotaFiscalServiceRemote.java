package br.com.schimidtsolutions.jsf.client.interfaces;

import javax.ejb.Remote;


@Remote
public interface NotaFiscalServiceRemote {

	public abstract void cadastrarNotaFiscal(NotaFiscalMutavel notaFiscalMutavel);
}