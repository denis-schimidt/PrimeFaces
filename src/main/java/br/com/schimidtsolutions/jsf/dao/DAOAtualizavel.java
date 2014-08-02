package br.com.schimidtsolutions.jsf.dao;

public interface DAOAtualizavel<T> {

	public void atualizar( T entity );
}