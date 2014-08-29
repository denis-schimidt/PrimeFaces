package br.com.schimidtsolutions.jsf.managedbean.binding;

import java.math.BigDecimal;

import br.com.schimidtsolutions.jsf.client.dto.ItemDTO;
import br.com.schimidtsolutions.jsf.client.interfaces.ItemMutavel;
import br.com.schimidtsolutions.jsf.client.interfaces.ItemMutavelTemporario;
import br.com.schimidtsolutions.jsf.client.interfaces.ProdutoMutavel;
import br.com.schimidtsolutions.jsf.constantes.ComparacaoObjetos;
import br.com.schimidtsolutions.jsf.entidades.Item;
import br.com.schimidtsolutions.jsf.entidades.NotaFiscal;
import br.com.schimidtsolutions.jsf.entidades.Produto;
import br.com.schimidtsolutions.jsf.interfaces.CopiavelPara;
import br.com.schimidtsolutions.jsf.managedbean.annotation.Binding;

@Binding
public class ItemBinding implements ItemMutavelTemporario, CopiavelPara<Item> {
	private static final long serialVersionUID = -6486643174124113072L;
	
	private Integer idTemporario;
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

	public void setIdTemporario(Integer idTemporario) {
		this.idTemporario = idTemporario;
	}

	@Override
	public void setId(final Long id) {
		itemMutavel.setId(id);
	}

	@Override
	public void setQuantidade(final Integer quantidade) {
		itemMutavel.setQuantidade(quantidade);
	}

	@Override
	public Long getId() {
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
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idTemporario == null) ? 0 : idTemporario.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemBinding other = (ItemBinding) obj;
		if (idTemporario == null) {
			if (other.idTemporario != null)
				return false;
		} else if (!idTemporario.equals(other.idTemporario))
			return false;
		return true;
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

	@Override
	public BigDecimal getValorTotal() {
		return itemMutavel.getValorTotal();
	}

	public Integer getIdTemporario() {
		return idTemporario;
	}
	
	@Override
	public int compareTo( ItemMutavel outroItem ) {
		final ItemBinding outroItemComparavel = (ItemBinding) outroItem;
		final boolean idOutroItemNaoENulo = outroItemComparavel != null && outroItemComparavel.idTemporario != null;
		final boolean idDesteItemNaoENulo = idTemporario != null;
		
		if( idDesteItemNaoENulo ){
			
			if( idOutroItemNaoENulo ){
				return idTemporario.intValue() - outroItemComparavel.idTemporario.intValue();
			}
			
			return ComparacaoObjetos.MENOR.getValor();
			
		}else{
			
			if( idOutroItemNaoENulo ){
				return ComparacaoObjetos.MAIOR.getValor();
			}
			
			return ComparacaoObjetos.IGUAL.getValor();
		}	
	}
}
