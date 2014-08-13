package br.com.schimidtsolutions.jsf.managedbean;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.schimidtsolutions.jsf.dao.DAO;
import br.com.schimidtsolutions.jsf.entity.Usuario;
import br.com.schimidtsolutions.jsf.managedbean.binding.UsuarioBindingCopiavel;
import br.com.schimidtsolutions.jsf.managedbean.interfaces.UsuarioBinding;

@Named
@RequestScoped
public class UsuarioMB {

	@Inject
	private DAO<Usuario> dao;
	
	@Inject
	private UsuarioBinding usuarioEmEdicao;
	
	public Boolean isUsuarioValido(){
		final UsuarioBindingCopiavel usuarioInformado = (UsuarioBindingCopiavel) usuarioEmEdicao;
		
		final List<Usuario> usuarios = dao.pesquisarPorCamposIguaisPreenchidos( usuarioInformado.paraEntidade() );
		
		return Boolean.valueOf( usuarios != null && !usuarios.isEmpty() );
	}
	
	public UsuarioBinding getUsuario() {
		return usuarioEmEdicao;
	}
}
