package br.com.schimidtsolutions.jsf.managedbean.binding;

import java.math.BigDecimal;

import br.com.schimidtsolutions.client.dto.ItemDTO;
import br.com.schimidtsolutions.jsf.common.interfaces.ItemMutavel;
import br.com.schimidtsolutions.jsf.common.interfaces.ProdutoMutavel;
import br.com.schimidtsolutions.jsf.entidades.Item;
import br.com.schimidtsolutions.jsf.entidades.NotaFiscal;
import br.com.schimidtsolutions.jsf.entidades.Produto;
import br.com.schimidtsolutions.jsf.interfaces.CopiavelPara;
import br.com.schimidtsolutions.jsf.managedbean.annotation.Binding;

@Binding
public class ItemBinding implements ItemMutavel, CopiavelPara<Item> {
	private static final long serialVersionUID = -6486643174124113072L;
	
	private ItemMutavel itemMutavel;
	private NotaFiscal notaFiscal;
	private Produto produto;
	
	public ItemBinding() {
		itemMutavel = new ItemDTO();
	}
	
	public ItemBinding( final ItemMutavel item ){
		itemMutavel = item;
	}
	
	public ItemBinding( final Item item ){
		this();
		
		itemMutavel.setId( getId() );
		itemMutavel.setProduto( new ProdutoBinding( item.getProduto() ) );
		itemMutavel.setQuantidade( getQuantidade() );
		itemMutavel.setValorUnitario( getValorUnitario() );
	}
	
	@Override
	public String toString() {
		return itemMutavel.toString();
	}

	@Override
	public void setId(final Integer id) {
		itemMutavel.setId(id);
	}

	@Override
	public void setQuantidade(final Integer quantidade) {
		itemMutavel.setQuantidade(quantidade);
	}

	@Override
	public Integer getId() {
		return itemMutavel.getId();
	}

	@Override
	public Integer getQuantidade() {
		return itemMutavel.getQuantidade();
	}

	public ItemMutavel getItemMutavel() {
		return itemMutavel;
	}

	public void setItemMutavel(final ItemMutavel itemMutavel) {
		this.itemMutavel = itemMutavel;
	}

	@Override
	public int hashCode() {
		return itemMutavel.hashCode();
	}

	@Override
	public boolean equals(final Object obj) {
		return itemMutavel.equals(obj);
	}

	@Override
	public Item toEntity() {
		
		return new Item.Builder()
			.comId( getId() )
			.tendoComoProduto( produto )
			.naNotaFiscal( notaFiscal )
			.naQuantidade( getQuantidade() )
			.create();
	}

	@Override
	public void setValorUnitario(final BigDecimal valorUnitario) {
		itemMutavel.setValorUnitario(valorUnitario);
	}

	@Override
	public BigDecimal getValorUnitario() {
		return itemMutavel.getValorUnitario();
	}

	@Override
	public void setProduto(final ProdutoMutavel produto) {
		itemMutavel.setProduto(produto);
	}

	@Override
	public ProdutoMutavel getProduto() {
		return itemMutavel.getProduto();
	}
	
	void setProduto(final Produto produto) {
		this.produto = produto;
	}

	void setNotaFiscal(final NotaFiscal notaFiscal) {
		this.notaFiscal = notaFiscal;
	}
}
