/*package br.com.schimidtsolutions.jsf.managedbean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.schimidtsolutions.jsf.binding.IUsuario;
import br.com.schimidtsolutions.jsf.binding.UsuarioBinding;
import br.com.schimidtsolutions.jsf.dao.DAO;
import br.com.schimidtsolutions.jsf.entity.Usuario;

@Named
@RequestScoped
public class UsuarioMB {

	@Inject
	private DAO<Usuario> dao;
	
	private UsuarioBinding usuarioBinding;
	
	public Boolean isUsuarioValido(){
		final IUsuario usuario = dao.pesquisarPorCamposIguaisPreenchidos( usuarioBinding.toEntity() );
		
		return usuario != null;
	}
	
	public UsuarioBinding getUsuario() {
		return usuarioBinding;
	}
}*/
