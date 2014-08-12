package br.com.schimidtsolutions.jsf.interfaces;

public interface BindingSimplesCopiavel<Entidade> {

	public Entidade paraEntidade();
	
	public void copiarDaEntidade( Entidade entidade );
}
