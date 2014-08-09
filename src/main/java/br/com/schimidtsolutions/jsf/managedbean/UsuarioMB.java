package br.com.schimidtsolutions.jsf.managedbean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.schimidtsolutions.jsf.dao.DAO;
import br.com.schimidtsolutions.jsf.entity.Usuario;

@Named
@RequestScoped
public class UsuarioMB {

	@Inject
	private DAO<Usuario> dao;
	
	private final Usuario.Builder usuarioEmEdicao = new Usuario.Builder();
	
	public Boolean isUsuarioValido(){
		final Usuario usuario = dao.pesquisarPorCamposIguaisPreenchidos( usuarioEmEdicao.create() );
		
		return Boolean.valueOf( usuario != null );
	}
	
	public Usuario.Builder getUsuario() {
		return usuarioEmEdicao;
	}
}
