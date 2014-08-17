package br.com.schimidtsolutions.jsf.web.filter;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;

//@WebFilter(urlPatterns = { "/*" }, description = "Parâmetros da Sessão")
public class SessionAttributeLogFilter implements Filter {

	@Inject @Singleton
	private Logger log;
	
	@Override
	public void doFilter(final ServletRequest request, final ServletResponse response,
			final FilterChain chain) throws IOException, ServletException {

		chain.doFilter(request, response);

		final HttpSession session = ((HttpServletRequest) request).getSession();
		final Enumeration<String> names = session.getAttributeNames();

		while (names.hasMoreElements()) {

			final String name = names.nextElement();

			final Object attribute = session.getAttribute(name);

			if (attribute instanceof HashMap) {
				log.debug("------ IDS DA ARVORE -------");

				@SuppressWarnings("unchecked")
				final HashMap<String, Object> originalViews = (HashMap<String, Object>) attribute;
				final HashMap<String, Object> views = new LinkedHashMap<String, Object>( originalViews );

				final Set<String> keySet = views.keySet();
				int i = 0;
				
				for (final String key : keySet) {
					log.debug( String.format("%d ID: %s %n" , ++i, key) ); // Bug Log4J2
				}

				log.debug("------ FIM IDS -------");
			}
			
			log.debug("OUTROS ATTRIBUTOS DA SESSAO: " + name);
		}
	}

	@Override
	public void destroy() {
		log.debug( "Destruindo SessionAttributeLogFilter..." );
	}

	@Override
	public void init(final FilterConfig arg0) throws ServletException {
		log.debug( "Iniciando SessionAttributeLogFilter..." );
	}
}
