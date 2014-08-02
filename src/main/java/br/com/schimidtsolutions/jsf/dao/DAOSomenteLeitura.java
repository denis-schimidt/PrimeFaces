package br.com.schimidtsolutions.jsf.dao;

import java.util.List;

public interface DAOSomenteLeitura<T> {

	public <ID> T pesquisarPorId( ID identity );

	public abstract List<T> listarTudo();

	public abstract List<T> listarComPaginacao(int paginaInicial, int tamanhoPagina);
}