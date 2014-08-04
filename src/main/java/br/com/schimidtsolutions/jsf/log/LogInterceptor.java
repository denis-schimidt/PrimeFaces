package br.com.schimidtsolutions.jsf.log;

import java.util.Arrays;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import org.slf4j.Logger;

@Logged
@Interceptor
public class LogInterceptor {
	private static final String METODO_VOID = "void";
	
	@Inject
	private Logger log;
	
	@AroundInvoke  
    public Object invoke(InvocationContext context) throws Exception{  
  
		log.info( gerarLogEntrada( context ) );
  
		long tempoInicial = System.currentTimeMillis();
		
		Object response = context.proceed();
        
		long tempoFinal = System.currentTimeMillis();
		
		log.info( gerarLogSaida( context, response, tempoInicial, tempoFinal ) );
		
		return response;  
	}
	
	private String gerarLogEntrada( InvocationContext context ){
		return String.format( "\n\n Chamando método %s \n -> Parâmetros(%s)\n", context.getMethod().toString(),
				Arrays.toString( context.getParameters() ) );
	}
	
	private String gerarLogSaida( InvocationContext context, Object response, long tempoInicial, long tempoFinal ){
		long tempoGasto = tempoFinal - tempoInicial;
		
		if( context.getMethod().getReturnType().getName().equals( METODO_VOID ) ){
			return String.format( "\n\n Resposta do método %s em %d milesegundos\n", context.getMethod().toString(), tempoGasto );
		}
		
		return String.format( "\n\n Resposta do método %s -> %s em %d milesegundos\n", context.getMethod().toString(), 
				response, tempoGasto );
	}
}
