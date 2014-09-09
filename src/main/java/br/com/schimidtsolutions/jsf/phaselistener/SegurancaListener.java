package br.com.schimidtsolutions.jsf.phaselistener;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;

import br.com.schimidtsolutions.jsf.managedbean.LoginBean;

public class SegurancaListener implements PhaseListener {
	private static final long serialVersionUID = -1180055334626039453L;
	private static final String LOGIN_PAGE = "/login.xhtml";
	
	@Inject @ApplicationScoped
	private Logger log;
	
	@Inject @SessionScoped
	private LoginBean loginBean;
	
	@Override
	public void afterPhase(final PhaseEvent event) {
		final FacesContext facesContext = event.getFacesContext();

		if( LOGIN_PAGE.equals( facesContext.getViewRoot().getViewId() ) ){
			return;
		}
		
		if( !loginBean.isLogado() ){
			logarTentativaDePularTelaLogin( facesContext, loginBean );
			redirecionarUsuarioParaTelaLogin(facesContext);
		}
	}

	private void redirecionarUsuarioParaTelaLogin( final FacesContext facesContext ) {
		final NavigationHandler navigationHandler = facesContext.getApplication().getNavigationHandler();
		navigationHandler.handleNavigation(facesContext, null, LOGIN_PAGE );
		facesContext.renderResponse();
	}
	
	private void logarTentativaDePularTelaLogin( final FacesContext facesContext, final LoginBean loginBean ){
		final HttpServletRequest request = (HttpServletRequest) facesContext
				.getExternalContext().getRequest();
		
		if( StringUtils.isBlank( loginBean.getUsuario().getLogin() ) || StringUtils.isBlank( loginBean.getUsuario().getSenha() ) ){
			log.warn( "Tentativa de pular tela de login pelo IP: {}", request.getRemoteAddr() );
		}
	}

	@Override
	public void beforePhase(final PhaseEvent event) {}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}
}
