package br.com.schimidtsolutions.jsf.producer;

import java.util.concurrent.atomic.AtomicInteger;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.schimidtsolutions.jsf.constantes.StatusItemNotaFiscal;

public class ResourceProducer {

	@Produces
	int getChaveNaoEncontrada(){
		return -1;
	}
	
	@Produces
    Logger newLogger(final InjectionPoint ip) {                           
        return LoggerFactory.getLogger( ip.getMember().getDeclaringClass() );
    }
	
	@Produces
	AtomicInteger newGeradorId(){
		return new AtomicInteger( 0 );
	}
	
	@Produces
	StatusItemNotaFiscal getStatusInicial(){
		return StatusItemNotaFiscal.INCLUSAO;
	}
}
