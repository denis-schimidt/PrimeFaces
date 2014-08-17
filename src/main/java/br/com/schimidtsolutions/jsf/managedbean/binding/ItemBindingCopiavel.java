package br.com.schimidtsolutions.jsf.managedbean.binding;

import java.math.BigDecimal;

import br.com.schimidtsolutions.jsf.entidades.Item;
import br.com.schimidtsolutions.jsf.entidades.NotaFiscal;
import br.com.schimidtsolutions.jsf.interfaces.BindingCopiavel;
import br.com.schimidtsolutions.jsf.managedbean.interfaces.ItemBinding;
import br.com.schimidtsolutions.jsf.managedbean.interfaces.ProdutoBinding;

public class ItemBindingCopiavel implements BindingCopiavel<Item>, ItemBinding {
	private static final long serialVersionUID = -6675679396380650885L;
	private Integer id;
	private ProdutoBinding produtoBinding;
	private NotaFiscal notaFiscal;
	private Integer quantidade;
	private BigDecimal valorUnitario;

	@Override
	public String toString() {
		return String.format("ItemBindingCopiavel [id=%s, produtoBinding=%s, notaFiscal=%s, quantidade=%s, valorUnitario=%s]",
					id, produtoBinding, notaFiscal, quantidade, valorUnitario);
	}
	
	@Override
	public void setId(final Integer id) {
		this.id = id;
	}
	@Override
	public void setProduto(final ProdutoBinding produto) {
		produtoBinding = produto;
	}
	@Override
	public void setQuantidade(final Integer quantidade) {
		this.quantidade = quantidade;
	}
	@Override
	public void setValorUnitario(final BigDecimal valorUnitario) {
		this.valorUnitario = valorUnitario;
	}
	@Override
	public Integer getId() {
		return id;
	}
	@Override
	public ProdutoBinding getProduto() {
		return produtoBinding;
	}
	@Override
	public Integer getQuantidade() {
		return quantidade;
	}
	@Override
	public BigDecimal getValorUnitario() {
		return valorUnitario;
	}

	void setNotaFiscal(final NotaFiscal notaFiscal) {
		this.notaFiscal = notaFiscal;
	}

	@Override
	public Item paraEntidade() {
		final ProdutoBindingCopiavel produtoCopiavel = (ProdutoBindingCopiavel) produtoBinding;
		
		return new Item.Builder()
			.comId( id )
			.naNotaFiscal( notaFiscal )
			.tendoComoProduto( produtoCopiavel.paraEntidade() )
			.naQuantidade( quantidade )
			.custandoUnitariamente( valorUnitario )
			.create();
	}

	@Override
	public void copiarDaEntidade(final Item entidade) {
		setId( entidade.getId() );
		
		final ProdutoBindingCopiavel produtoCopiavel = (ProdutoBindingCopiavel) produtoBinding;
		produtoCopiavel.copiarDaEntidade( entidade.getProduto() );
		setProduto( produtoCopiavel );
		
		setQuantidade( entidade.getQuantidade() );
		setValorUnitario( entidade.getValorUnitario() );
	}
}
