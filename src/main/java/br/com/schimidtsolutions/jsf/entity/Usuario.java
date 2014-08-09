package br.com.schimidtsolutions.jsf.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import br.com.schimidtsolutions.jsf.binding.IUsuario;

@Entity
@Table(schema="development", name="USUARIO", uniqueConstraints=@UniqueConstraint(columnNames={"login"}) )
public class Usuario implements IUsuario {
		
	@Id
	@SequenceGenerator( schema="development", sequenceName="USUARIO_SEQ", initialValue=1, allocationSize=1, name="UsuarioGenerator")
	@GeneratedValue(generator="UsuarioGenerator", strategy=GenerationType.SEQUENCE)
	@Column(name="ID", insertable=true, nullable=false, updatable=false )
	private Integer id;
	
	@Column(name="LOGIN", insertable=true, length=50, nullable=false, updatable=true )
	private String login;
	
	@Column(name="SENHA", insertable=true, length=50, nullable=false, updatable=true )
	private String senha;

	Usuario() {}
	
	public Usuario( final Builder builder ) {
		login = builder.login;
		senha = builder.senha;
		id = builder.id;
	}

	@Override
	public String toString() {
		return String.format("Usuario [id=%s, login=%s]", id, login);
	}

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
		final Usuario other = (Usuario) obj;
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
	public Integer getId() {
		return id;
	}

	@Override
	public String getLogin() {
		return login;
	}

	@Override
	public String getSenha() {
		return senha;
	}
	
	public static class Builder{
		private Integer id;
		private String login;
		private String senha;
		
		public Integer getId() {
			return id;
		}
		
		public void setId(final Integer id) {
			this.id = id;
		}
		
		public String getLogin() {
			return login;
		}
		
		public void setLogin(final String login) {
			this.login = login;
		}
		
		public String getSenha() {
			return senha;
		}
		
		public void setSenha(final String senha) {
			this.senha = senha;
		}
		
		public Usuario create(){
			return new Usuario( this );
		}
	}
}
