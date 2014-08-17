package br.com.schimidtsolutions.jsf.dao;

import java.io.Serializable;


public interface DAODeletavel<T> extends Serializable {

	public <ID> void apagarPorId( ID IdEntidade );
	
	public void apagar(T entidade);
}