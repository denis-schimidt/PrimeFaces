package br.com.schimidtsolutions.jsf.ejb;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.Local;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Singleton;

import org.slf4j.Logger;

import br.com.schimidtsolutions.jsf.client.interfaces.ItemMutavel;
import br.com.schimidtsolutions.jsf.client.interfaces.NotaFiscalMutavel;
import br.com.schimidtsolutions.jsf.client.interfaces.NotaFiscalServiceRemote;
import br.com.schimidtsolutions.jsf.dao.DAO;
import br.com.schimidtsolutions.jsf.ejb.factory.NotaFiscalFactory;
import br.com.schimidtsolutions.jsf.entidades.NotaFiscal;
import br.com.schimidtsolutions.jsf.entidades.Produto;
import br.com.schimidtsolutions.jsf.managedbean.binding.NotaFiscalBinding;

@Stateless
@Remote( NotaFiscalServiceRemote.class )
@Local( NotaFiscalService.class )
public class NotaFiscalServiceBean implements NotaFiscalService, NotaFiscalServiceRemote {
	
	@Inject
	private DAO<NotaFiscal> daoNotaFiscal;

	@Inject
	private DAO<Produto> daoProduto;
	
	@Inject @Singleton
	private Logger log;
	
	@Override
	@Lock(LockType.WRITE)
	public void cadastrarNotaFiscal( final NotaFiscalMutavel notaFiscalMutavel ){
		
		final NotaFiscal notaFiscal = getNotaFiscal(notaFiscalMutavel);

		daoNotaFiscal.adicionar(notaFiscal);
		
		log.info( "Nota fiscal {} gravada com sucesso!", notaFiscal.toString() );
	}

	private NotaFiscal getNotaFiscal( final NotaFiscalMutavel notaFiscalMutavel ) {
		
		final Map<Integer, Produto> mapProdutos = new HashMap<>();
		
		for ( final ItemMutavel item : notaFiscalMutavel.getItens() ) {
			final Integer idProduto = item.getProduto().getId();
			mapProdutos.put( idProduto, daoProduto.pesquisarPorId( idProduto ) );
		}
		
		return NotaFiscalFactory.getInstanceNotaFiscal( 
				getNotaFiscalBinding( notaFiscalMutavel ), mapProdutos );
	}

	private NotaFiscalBinding getNotaFiscalBinding( final NotaFiscalMutavel notaFiscalMutavel ) {
		NotaFiscalBinding notaFiscalBindingl = null;
		
		if( notaFiscalMutavel instanceof NotaFiscalBinding ){
			notaFiscalBindingl = (NotaFiscalBinding) notaFiscalMutavel;
			
		}else{
			notaFiscalBindingl = new NotaFiscalBinding( notaFiscalMutavel );
		}
		
		return notaFiscalBindingl;
	}
}
