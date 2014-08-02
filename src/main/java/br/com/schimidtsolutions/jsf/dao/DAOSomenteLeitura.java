package br.com.schimidtsolutions.jsf.dao;

public interface DAOSomenteLeitura<T> {

	public <ID> T pesquisarPorId( ID identity );
}