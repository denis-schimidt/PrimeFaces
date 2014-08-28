package br.com.schimidtsolutions.jsf.dao;

import java.io.Serializable;


public interface DAOAtualizavel<T> extends Serializable {

	public T atualizar( T entidade );
}