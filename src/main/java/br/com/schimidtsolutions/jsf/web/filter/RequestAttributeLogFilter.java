package br.com.schimidtsolutions.jsf.web.filter;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;

//@WebFilter(urlPatterns = {"/*"}, description = "Parâmetros do Request" )
public final class RequestAttributeLogFilter implements Filter {
	
	@Inject @Singleton
	private Logger log;

	@Override
	public void destroy() {
		log.info( "Destruindo RequestAttributeLogFilter...");
	}

	@Override
	public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain) throws IOException, ServletException {
		
		final HttpServletRequest httpServletRequest = (HttpServletRequest) request;

		for( final String key : httpServletRequest.getParameterMap().keySet() ){
			
			for( final String value : httpServletRequest.getParameterMap().get(key) ){
				log.info( String.format( "Parametro %s -> %s", key, value ) );
			}
		}
		
		chain.doFilter(request, response);
	}

	@Override
	public void init(final FilterConfig fConfig) throws ServletException {
		log.info( "Iniciando o RequestAttributeLogFilter...");
	}
}
