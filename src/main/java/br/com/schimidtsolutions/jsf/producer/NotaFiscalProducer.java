package br.com.schimidtsolutions.jsf.producer;

import javax.enterprise.inject.Produces;

import br.com.schimidtsolutions.jsf.constantes.StatusItemNotaFiscal;

public class NotaFiscalProducer {
	
	@Produces 
	StatusItemNotaFiscal getStatusInicial(){
		return StatusItemNotaFiscal.INCLUSAO;
	}
}
