package br.com.schimidtsolutions.jsf.managedbean.binding;

import java.math.BigDecimal;

import br.com.schimidtsolutions.jsf.entity.Produto;
import br.com.schimidtsolutions.jsf.interfaces.BindingSimplesCopiavel;
import br.com.schimidtsolutions.jsf.managedbean.interfaces.ProdutoBinding;

public class ProdutoBindingCopiavel implements ProdutoBinding, BindingSimplesCopiavel<Produto>{
	private static final long serialVersionUID = -9132635464789704783L;
	private Integer id;
	private String nome;
	private String descricao;
	private BigDecimal preco;
	
	public ProdutoBindingCopiavel() {}
	
	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(final Integer id) {
		this.id = id;
	}

	@Override
	public String getNome() {
		return nome;
	}

	@Override
	public void setNome(final String nome) {
		this.nome = nome;
	}

	@Override
	public String getDescricao() {
		return descricao;
	}

	@Override
	public void setDescricao(final String descricao) {
		this.descricao = descricao;
	}

	@Override
	public BigDecimal getPreco() {
		return preco;
	}

	@Override
	public void setPreco(final BigDecimal preco) {
		this.preco = preco;
	}

	@Override
	public Produto paraEntidade() {
		return new Produto.Builder()
			.comId( getId() )
			.descritoComo( getDescricao() )
			.nome( getNome() )
			.preco( getPreco() )
			.create();
	}

	@Override
	public void copiarDaEntidade(final Produto produto) {
		setId( produto.getId() );
		setDescricao( produto.getDescricao() );
		setNome( produto.getNome() );
		setPreco( produto.getPreco() );
	}
}
