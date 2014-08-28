package br.com.schimidtsolutions.client.dto;

import br.com.schimidtsolutions.jsf.common.interfaces.UsuarioMutavel;

public class UsuarioDTO implements UsuarioMutavel {
	private static final long serialVersionUID = 316932677756750576L;
	private Integer id;
	private String login;
	private String senha;
	
	public UsuarioDTO() {}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (id == null ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final UsuarioDTO other = (UsuarioDTO) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}


	@Override
	public String toString() {
		return String.format("UsuarioDTO [id=%s, login=%s, senha=%s]", id, login, senha);
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
}
