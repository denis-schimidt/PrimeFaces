package br.com.schimidtsolutions.jsf.dao;

public interface DAODeletavel {

	public <ID> void apagar( ID IdEntidade );
}