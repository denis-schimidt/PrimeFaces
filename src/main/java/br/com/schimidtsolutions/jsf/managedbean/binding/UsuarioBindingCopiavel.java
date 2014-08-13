package br.com.schimidtsolutions.jsf.managedbean.binding;

import br.com.schimidtsolutions.jsf.entity.Usuario;
import br.com.schimidtsolutions.jsf.interfaces.BindingSimplesCopiavel;
import br.com.schimidtsolutions.jsf.managedbean.interfaces.UsuarioBinding;

public class UsuarioBindingCopiavel implements BindingSimplesCopiavel<Usuario>, UsuarioBinding {
	private Integer id;
	private String login;
	private String senha;
	
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
