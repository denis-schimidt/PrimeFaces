package br.com.schimidtsolutions.jsf.phaselistener;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;

import br.com.schimidtsolutions.jsf.log.Logged;
import br.com.schimidtsolutions.jsf.managedbean.MenuBean;

public class MenuPhaseListener implements PhaseListener {
	private static final long serialVersionUID = -2212446767366571892L;
	
	@Inject @ApplicationScoped
	private Logger log;

	@Inject @ViewScoped
	private MenuBean menuBean;
	
	@PostConstruct
	public void init(){
		log.info( "Iniciando MenuPhaseListener..." );
	}
	
	@Override @Logged
	public void beforePhase(final PhaseEvent event) {
		String urlRequestAtual = ((HttpServletRequest) event.getFacesContext().getExternalContext().getRequest()).getRequestURI();
		String nomePaginaAtual = urlRequestAtual.substring( urlRequestAtual.lastIndexOf( "/" ) + 1 );

		menuBean.atualizarExibicaoMenu( nomePaginaAtual );
		
		log.info( "Menu atualizado para a tela {} na Phase {}", "listarnotafiscal.xhtml", getPhaseId() );
	}	
	
	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RENDER_RESPONSE;
	}
	
	@PreDestroy
	public void destroy(){
		log.info( "Destruindo MenuPhaseListener..." );
	}

	@Override
	public void afterPhase( final PhaseEvent event ) {}
}
