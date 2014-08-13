package br.com.schimidtsolutions.jsf.managedbean.interfaces;

public interface UsuarioBinding {

	public abstract Integer getId();

	public abstract void setId(Integer id);

	public abstract String getLogin();

	public abstract void setLogin(String login);

	public abstract String getSenha();

	public abstract void setSenha(String senha);

}