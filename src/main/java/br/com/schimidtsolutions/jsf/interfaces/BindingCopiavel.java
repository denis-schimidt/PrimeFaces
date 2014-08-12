package br.com.schimidtsolutions.jsf.interfaces;

public interface BindingCopiavel<Entidade> {

	public Entidade paraEntidade();
	
	public void copiarDaEntidade( Entidade entidade );
}
