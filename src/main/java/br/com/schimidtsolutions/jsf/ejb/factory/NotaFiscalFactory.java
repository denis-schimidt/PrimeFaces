package br.com.schimidtsolutions.jsf.ejb.factory;

import java.util.Map;

import br.com.schimidtsolutions.jsf.client.interfaces.ItemMutavel;
import br.com.schimidtsolutions.jsf.client.interfaces.NotaFiscalMutavel;
import br.com.schimidtsolutions.jsf.managedbean.binding.NotaFiscalBinding;
import br.com.schimidtsolutions.jsf.modelo.Item;
import br.com.schimidtsolutions.jsf.modelo.NotaFiscal;
import br.com.schimidtsolutions.jsf.modelo.Produto;

public class NotaFiscalFactory {

	public static NotaFiscal getInstanceNotaFiscal( final NotaFiscalMutavel notaFiscalMutavel, final Map<Integer,Produto> produtos ){
		
		final NotaFiscalBinding notaFiscalCopiavel = (NotaFiscalBinding) notaFiscalMutavel;
		final NotaFiscal notaFiscal = notaFiscalCopiavel.toEntity();
		
		for( final ItemMutavel itemMutavel : notaFiscalMutavel.getItens() ){
			final Integer idProduto = itemMutavel.getProduto().getId();
			final Produto produto = produtos.get(idProduto);
			
			final Item item = new Item.Builder()
				.comId( itemMutavel.getId() )
				.custandoUnitariamente( produto.getPreco() )
				.naQuantidade( itemMutavel.getQuantidade() )
				.tendoComoProduto( produto )
				.create();
			
			notaFiscal.addItem(item);
		}
		
		return notaFiscal;
	}
}
