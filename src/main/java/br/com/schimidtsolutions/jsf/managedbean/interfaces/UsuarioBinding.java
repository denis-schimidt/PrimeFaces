package br.com.schimidtsolutions.jsf.managedbean.interfaces;

import java.io.Serializable;

public interface UsuarioBinding extends Serializable {

	public abstract Integer getId();

	public abstract void setId(Integer id);

	public abstract String getLogin();

	public abstract void setLogin(String login);

	public abstract String getSenha();

	public abstract void setSenha(String senha);

}