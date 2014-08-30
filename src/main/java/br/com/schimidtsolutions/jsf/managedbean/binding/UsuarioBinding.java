package br.com.schimidtsolutions.jsf.managedbean.binding;

import br.com.schimidtsolutions.jsf.client.dto.UsuarioDTO;
import br.com.schimidtsolutions.jsf.client.interfaces.UsuarioMutavel;
import br.com.schimidtsolutions.jsf.interfaces.CopiavelPara;
import br.com.schimidtsolutions.jsf.managedbean.annotation.Binding;
import br.com.schimidtsolutions.jsf.modelo.Usuario;

@Binding
public class UsuarioBinding implements UsuarioMutavel, CopiavelPara<Usuario> {
	private static final long serialVersionUID = -4910729432803285800L;
	
	private final UsuarioMutavel usuarioMutavel;

	public UsuarioBinding() {
		usuarioMutavel = new UsuarioDTO();
	}
	
	public UsuarioBinding( final Usuario usuario ){
		this();
		
		usuarioMutavel.setId(getId());
		usuarioMutavel.setLogin(getLogin());
		usuarioMutavel.setSenha(getSenha());
	}
	
	public UsuarioBinding( final UsuarioMutavel usuario ) {
		usuarioMutavel = usuario;
	}

	@Override
	public Integer getId() {
		return usuarioMutavel.getId();
	}

	@Override
	public void setId(final Integer id) {
		usuarioMutavel.setId(id);
	}

	@Override
	public String getLogin() {
		return usuarioMutavel.getLogin();
	}

	@Override
	public void setLogin(final String login) {
		usuarioMutavel.setLogin(login);
	}

	@Override
	public String getSenha() {
		return usuarioMutavel.getSenha();
	}

	@Override
	public void setSenha(final String senha) {
		usuarioMutavel.setSenha(senha);
	}

	@Override
	public String toString() {
		return usuarioMutavel.toString();
	}

	@Override
	public Usuario toEntity() {
		return new Usuario.Builder()
			.comId(getId())
			.login(getLogin())
			.senha(getSenha())
			.create();
	}
}
