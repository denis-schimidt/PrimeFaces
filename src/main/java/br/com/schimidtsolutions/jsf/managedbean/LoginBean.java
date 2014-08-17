package br.com.schimidtsolutions.jsf.managedbean;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;

import br.com.schimidtsolutions.jsf.dao.DAO;
import br.com.schimidtsolutions.jsf.entidades.Usuario;
import br.com.schimidtsolutions.jsf.managedbean.binding.UsuarioBindingCopiavel;
import br.com.schimidtsolutions.jsf.managedbean.interfaces.UsuarioBinding;

@Named
@SessionScoped
public class LoginBean implements Serializable {
	private static final long serialVersionUID = -5459225359063936355L;

	@Inject @Singleton
	private transient Logger log;

	@Inject
	private transient DAO<Usuario> dao;
	
	@Inject
	private UsuarioBinding usuario;
	
	public String logIn(){
		final UsuarioBindingCopiavel usuarioInformado = (UsuarioBindingCopiavel) usuario;
		
		final List<Usuario> usuariosPesquisados = dao.pesquisarPorCamposIguaisPreenchidos( usuarioInformado.paraEntidade() );
		
		try{
			validarUsuario(usuarioInformado, usuariosPesquisados);
			
		}catch( final IllegalAccessException exception ){
			resetUsuario();
			log.error( exception.getMessage() );
			return "login?faces-redirect=true";
		}
		
		return efetuarLoginSessao(usuariosPesquisados);
	}

	private String efetuarLoginSessao(final List<Usuario> usuariosPesquisados) {
		usuario = new UsuarioBindingCopiavel( usuariosPesquisados.get( 0 ) );
			
		return "produto?faces-redirect=true";
	}

	private void validarUsuario(final UsuarioBindingCopiavel usuarioInformado, final List<Usuario> usuarios) throws IllegalAccessException {
		
		if( StringUtils.isEmpty( usuarioInformado.getLogin() ) || StringUtils.isEmpty( usuarioInformado.getSenha() ) ){
			final String mensagemErro = String.format( "Tentativa de login inválido (%s) para o usuário.",  usuarioInformado.getLogin() );
			throw new IllegalAccessException( mensagemErro );
			
		}else if( usuarios == null || usuarios.isEmpty() ){
			final String mensagemErro = String.format( "O usuário (%s) não está cadastrado no sistema.",  usuarioInformado.getLogin() );
			throw new IllegalAccessException( mensagemErro );
			
		}else if( usuarios.size() > 1 ){
			final String mensagemErro = String.format( "Mais de um usuário com mesmo login (%s) recuperado da base de dados.",  usuarioInformado.getLogin() );
			throw new IllegalAccessException( mensagemErro );
		}
	}
	
	public String logOut(){
		resetUsuario();
		
		return "login?faces-redirect=true";
	}

	private void resetUsuario() {
		usuario = new UsuarioBindingCopiavel();
	}
	
	public UsuarioBinding getUsuario() {
		return usuario;
	}

	public boolean isLogado() {
		return usuario.getId() !=null;
	}
}
