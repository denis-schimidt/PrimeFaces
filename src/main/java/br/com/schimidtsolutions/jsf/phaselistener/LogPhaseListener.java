package br.com.schimidtsolutions.jsf.phaselistener;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.inject.Inject;

import org.slf4j.Logger;

public class LogPhaseListener implements PhaseListener {
	private static final long serialVersionUID = -2212446767366571892L;
	
	@Inject
	private Logger log;

	@PostConstruct
	public void init(){
		log.debug( "Iniciando LogPhaseListener..." );
	}
	
	@Override
	public void afterPhase(final PhaseEvent event) {
		log.debug( "Depois da Fase: " + event.getPhaseId() );
	}

	@Override
	public void beforePhase(final PhaseEvent event) {
		log.debug( "Antes da Fase: " + event.getPhaseId() );
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.ANY_PHASE;
	}
	
	@PreDestroy
	public void destroy(){
		log.debug( "Destruindo LogPhaseListener..." );
	}
}
