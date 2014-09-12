package br.com.schimidtsolutions.jsf.managedbean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import org.slf4j.Logger;

import br.com.schimidtsolutions.jsf.constantes.TemaTela;
import br.com.schimidtsolutions.jsf.dao.DAO;
import br.com.schimidtsolutions.jsf.managedbean.binding.UsuarioBinding;
import br.com.schimidtsolutions.jsf.modelo.Usuario;

@Named
@SessionScoped
public class ThemeBean implements Serializable {
	private static final long serialVersionUID = 8734914354342056817L;

	@Inject @ApplicationScoped
	private Logger log;
	
	@Inject @ApplicationScoped
	private List<TemaTela> listaTemas;
	
	@Inject @RequestScoped
	private DAO<Usuario> dao;
	
	@Inject 
	private LoginBean loginBean;

	private TemaTela tema;

	@Transactional
	public void salvarTema(){
		UsuarioBinding usuario = (UsuarioBinding) loginBean.getUsuario();
		usuario.setTemaTela(tema);
		
		dao.atualizar( usuario.toEntity() );
		log.info( "Tema {} salvo para o usuário", tema );
	}
	
	@PostConstruct
	private void recuperarTemaUsuario(){
		UsuarioBinding usuario = (UsuarioBinding) loginBean.getUsuario();
		tema = usuario.getTemaTela();
	}
	
	public String getTema() {
		
		if( tema == null ){
			tema = TemaTela.BOOTSTRAP;
		}
		
		return tema.getNomeReal();
	}
	
	public void setTema(String nomeReal) {
		this.tema = TemaTela.getInstancePeloNomeReal( nomeReal );
		
		log.info( "Tema {} alterado pelo usuário", tema );
	}
	
	public List<TemaTela> getListaTemas() {
		return listaTemas;
	}
}
