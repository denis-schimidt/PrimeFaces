package br.com.schimidtsolutions.jsf.managedbean;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;

import br.com.schimidtsolutions.jsf.dao.DAO;
import br.com.schimidtsolutions.jsf.entity.Usuario;
import br.com.schimidtsolutions.jsf.managedbean.binding.UsuarioBindingCopiavel;
import br.com.schimidtsolutions.jsf.managedbean.interfaces.UsuarioBinding;

@Named
@SessionScoped
public class LoginBean implements Serializable {
	private static final long serialVersionUID = 6082721444325328318L;

	@Inject
	private Logger log;

	@Inject
	private DAO<Usuario> dao;
	
	@Inject
	private UsuarioBinding usuarioEmEdicao;
	
	public String logar(){
		final UsuarioBindingCopiavel usuarioInformado = (UsuarioBindingCopiavel) usuarioEmEdicao;
		
		final List<Usuario> usuarios = dao.pesquisarPorCamposIguaisPreenchidos( usuarioInformado.paraEntidade() );
		
		if( usuarios != null && !usuarios.isEmpty() ){
			log.info( "Usuário {} logado com sucesso",  usuarioInformado.getLogin() );
			return "produto";
			
		}else{
			log.info( "Tentativa de login inválido para o usuário {}",  usuarioInformado.getLogin() );
			usuarioEmEdicao = new UsuarioBindingCopiavel();
			return "usuario";
		}
	}
	
	public UsuarioBinding getUsuario() {
		return usuarioEmEdicao;
	}
}
