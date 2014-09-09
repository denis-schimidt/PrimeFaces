package br.com.schimidtsolutions.jsf.phaselistener;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.inject.Inject;

import org.slf4j.Logger;

public class LogPhaseListener implements PhaseListener {
	private static final long serialVersionUID = -2212446767366571892L;
	
	@Inject @ApplicationScoped
	private Logger log;

	@PostConstruct
	public void init(){
		log.info( "Iniciando LogPhaseListener..." );
	}
	
	@Override
	public void afterPhase(final PhaseEvent event) {
		log.info( "Depois da Fase: " + event.getPhaseId() );
		
		final List<FacesMessage> messageList = event.getFacesContext().getMessageList();

		for( final FacesMessage facesMessage : messageList ){
			log.error( facesMessage.getDetail() );
		}
	}

	@Override
	public void beforePhase(final PhaseEvent event) {
		log.info( "Antes da Fase: " + event.getPhaseId() );
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.ANY_PHASE;
	}
	
	@PreDestroy
	public void destroy(){
		log.info( "Destruindo LogPhaseListener..." );
	}
}
