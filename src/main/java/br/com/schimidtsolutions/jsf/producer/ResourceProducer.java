package br.com.schimidtsolutions.jsf.producer;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResourceProducer {

	@Produces
    Logger newLogger(final InjectionPoint ip) {                           
        return LoggerFactory.getLogger( ip.getMember().getDeclaringClass() );
    }
}
