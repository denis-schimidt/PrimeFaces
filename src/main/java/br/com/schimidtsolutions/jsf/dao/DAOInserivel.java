package br.com.schimidtsolutions.jsf.dao;

import java.io.Serializable;



public interface DAOInserivel<T> extends Serializable {

	public T adicionar(T entity);
}