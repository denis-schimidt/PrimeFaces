package br.com.schimidtsolutions.jsf.dao;

import java.io.Serializable;
import java.util.List;

public interface DAOSomenteLeitura<T> extends Serializable {

	public <ID> T pesquisarPorId( ID identity );

	public abstract List<T> listarTudo();

	public abstract List<T> listarComPaginacao(int paginaInicial, int tamanhoPagina);
	
	public List<T> pesquisarPorCamposIguaisPreenchidos(final T entidade);
}