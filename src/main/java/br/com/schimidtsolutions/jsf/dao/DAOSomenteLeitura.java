package br.com.schimidtsolutions.jsf.dao;

import java.io.Serializable;
import java.util.List;

import br.com.schimidtsolutions.jsf.dao.helper.Paginacao;

public interface DAOSomenteLeitura<T> extends Serializable {

	public <ID> T pesquisarPorId( ID id );

	public abstract List<T> listarTudo();
	
	public List<T> pesquisarPorCamposIguaisPreenchidos( T entidade );
	
	public List<T> listarComPaginacaoOrdenacaoEFiltros( Paginacao paginacao );
	
	public Long contar();
}