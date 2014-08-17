package br.com.schimidtsolutions.jsf.managedbean.binding;

import br.com.schimidtsolutions.jsf.entidades.Usuario;
import br.com.schimidtsolutions.jsf.interfaces.BindingCopiavel;
import br.com.schimidtsolutions.jsf.managedbean.interfaces.UsuarioBinding;

public class UsuarioBindingCopiavel implements BindingCopiavel<Usuario>, UsuarioBinding {
	private static final long serialVersionUID = 316932677756750576L;
	private Integer id;
	private String login;
	private String senha;
	
	public UsuarioBindingCopiavel() {}
	
	public UsuarioBindingCopiavel( final Usuario usuario ){
		copiarDaEntidade(usuario);
	}
	
	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(final Integer id) {
		this.id = id;
	}

	@Override
	public String getLogin() {
		return login;
	}

	@Override
	public void setLogin(final String login) {
		this.login = login;
	}

	@Override
	public String getSenha() {
		return senha;
	}

	@Override
	public void setSenha(final String senha) {
		this.senha = senha;
	}

	@Override
	public Usuario paraEntidade() {
		return new Usuario.Builder()
			.comId( getId() )
			.login( getLogin() )
			.senha( getSenha() )
			.create();
	}

	@Override
	public void copiarDaEntidade(final Usuario usuario) {
		setId( usuario.getId() );
		setLogin( usuario.getLogin() );
		setSenha( usuario.getSenha() );
	}
}
