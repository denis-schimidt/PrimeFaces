package br.com.schimidtsolutions.jsf.util;

import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MetodoObjetoUtil {
	private static final Pattern PATTERN_NOME_METODO = Pattern.compile( "(^[gs]et|is)(\\w)(.*)" );
	private static final Pattern PATTERN_METODO_GETTER = Pattern.compile( "(^get|is)(\\w)(.*)" );
	
	public static boolean isMetodoGetter( final Method metodoAcessorio ){
		final Matcher matcher = PATTERN_METODO_GETTER.matcher( metodoAcessorio.getName() );
		
		return matcher.find();
	}
	
	public static String obterNomeCampoPeloMetodoAcessorio( final Method metodoAcessorio ){
		final Matcher matcher = PATTERN_NOME_METODO.matcher( metodoAcessorio.getName() );
		String nomeCampo = null;
		
		while (matcher.find()) {
			nomeCampo = String.format( "%s%s", matcher.group( 2 ).toLowerCase(), matcher.group( 3 ) );
		}
		
		return nomeCampo;
	}
}
