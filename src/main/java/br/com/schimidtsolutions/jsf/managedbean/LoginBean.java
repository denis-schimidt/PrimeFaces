package br.com.schimidtsolutions.jsf.managedbean;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;

import br.com.schimidtsolutions.jsf.client.interfaces.UsuarioMutavel;
import br.com.schimidtsolutions.jsf.dao.DAO;
import br.com.schimidtsolutions.jsf.entidades.Usuario;
import br.com.schimidtsolutions.jsf.managedbean.annotation.Binding;
import br.com.schimidtsolutions.jsf.managedbean.binding.UsuarioBinding;

@Named
@SessionScoped
public class LoginBean implements Serializable {
	private static final long serialVersionUID = -5459225359063936355L;

	@Inject @Singleton
	private transient Logger log;

	@Inject
	private DAO<Usuario> dao;
	
	@Inject @Binding
	private UsuarioMutavel usuarioSessao;
	
	public String logIn(){
		final UsuarioBinding usuarioBinding = (UsuarioBinding) usuarioSessao; 
		
		final List<Usuario> usuariosPesquisados = dao.pesquisarPorCamposIguaisPreenchidos( usuarioBinding.toEntity() );
		
		try{
			validarUsuario( usuarioBinding, usuariosPesquisados );
			
		}catch( final IllegalAccessException exception ){
			resetUsuario();
			log.error( exception.getMessage() );
			return "login?faces-redirect=true";
		}
		
		return efetuarLoginSessao( usuariosPesquisados.get( 0 ) );
	}

	private String efetuarLoginSessao(final Usuario usuario) {
		usuarioSessao = new UsuarioBinding( usuario );
			
		return "produto?faces-redirect=true";
	}

	private void validarUsuario(final UsuarioMutavel usuarioInformado, final List<Usuario> usuarios) throws IllegalAccessException {
		
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
		usuarioSessao = new UsuarioBinding();
	}
	
	public UsuarioMutavel getUsuario() {
		return usuarioSessao;
	}

	public boolean isLogado() {
		return usuarioSessao.getId() !=null;
	}
}
