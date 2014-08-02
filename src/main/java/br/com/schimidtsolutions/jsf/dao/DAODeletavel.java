package br.com.schimidtsolutions.jsf.dao;

public interface DAODeletavel<T> {

	public abstract void apagar(T identity);
}