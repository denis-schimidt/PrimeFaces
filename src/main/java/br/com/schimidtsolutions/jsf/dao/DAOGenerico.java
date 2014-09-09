package br.com.schimidtsolutions.jsf.dao;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.constraints.NotNull;

import org.hibernate.jpa.criteria.OrderImpl;
import org.slf4j.Logger;

import br.com.schimidtsolutions.jsf.dao.helper.Paginacao;
import br.com.schimidtsolutions.jsf.util.MetodoObjetoUtil;

public class DAOGenerico<T> implements DAO<T> {
	private static final long serialVersionUID = 6020966045581794498L;

	@Inject @Singleton
	private Logger log;
	
	@NotNull(message="A classe genérica do DAO não pode ser nula!")
	private final Class<T> classeEntidade;
	
	@NotNull(message="O EntityManager não pode ser nulo para o DAOGenérico!")
	private final EntityManager em;
	
	public DAOGenerico(final Class<T> classEntity, final EntityManager em) {
		this.classeEntidade = classEntity;
		this.em = em;
	}
	
	@Override
	public T adicionar( final T entidade ){
		em.persist( entidade );
		
		return entidade;
	}
	
	@Override
	public <ID> T pesquisarPorId( final ID id ){
		return em.find( classeEntidade, id );
	}
	
	@Override
	public Long contarComFiltro( Map<String, Object> filtros ){
		final CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery( Long.class );
		
		final Root<T> tabela = query.from( classeEntidade );
		
		final Predicate where = criarPredicatePaginacao( filtros, builder, tabela );
		
		query = query.select( builder.count( tabela ) );
		
		if( where != null ){
			query = query.where( where );
		}
		
		return em.createQuery( query ).getSingleResult();
	}
	
	@Override
	public Long contarTudo(){
		final CriteriaBuilder builder = em.getCriteriaBuilder();
		final CriteriaQuery<Long> query = builder.createQuery( Long.class );
		
		query.select( builder.countDistinct( query.from( classeEntidade ) ) );
		
		return em.createQuery( query ).getSingleResult();
	}
	
	public List<T> listarComPaginacaoOrdenacaoEFiltros( Paginacao paginacao ){
		return criarTypedQueryParaPaginacao(paginacao).getResultList();
	}

	private TypedQuery<T> criarTypedQueryParaPaginacao( Paginacao paginacao ) {
		final CriteriaBuilder builder = em.getCriteriaBuilder();
		final CriteriaQuery<T> query = builder.createQuery( classeEntidade );
		
		final Root<T> tabela = query.from( classeEntidade );
		
		final Order ordem = criarOrdemPaginacao( paginacao, tabela );
		final Predicate where = criarPredicatePaginacao( paginacao.getFiltros(), builder, tabela );
		
		return criarTypedQueryPorParametros( query, tabela, ordem, where, paginacao );
	}

	private TypedQuery<T> criarTypedQueryPorParametros(final CriteriaQuery<T> query, final Root<T> tabela, Order ordem, Predicate where, Paginacao paginacao) {
		
		CriteriaQuery<T> queryPaginacao = query.select( tabela );
		
		if( where != null ){
			queryPaginacao = queryPaginacao.where( where );
		}
		
		if( ordem != null ){
			queryPaginacao = queryPaginacao.orderBy( ordem );
		}
		
		return em.createQuery( queryPaginacao )
				.setFirstResult( paginacao.getPaginaInicial() )
				.setMaxResults( paginacao.getTamanhoPagina() );		
	}

	private Predicate criarPredicatePaginacao( Map<String,Object> filtros, final CriteriaBuilder builder, final Root<T> tabela ) {
		Predicate where = null;
		
		if( filtros != null && !filtros.isEmpty() ){
			where = builder.and();
			
			for( String nomeCampoTabela : filtros.keySet() ){
				Expression<String> campoTabela = tabela.get( nomeCampoTabela );
				Object valorProcurado = filtros.get( nomeCampoTabela );
				
				if( campoTabela.getJavaType().equals( String.class ) ){
					where = builder.and( where, 
						builder.like( 
							builder.lower( campoTabela ), 
								String.format("%%%s%%", ((String) valorProcurado).toLowerCase() ) ) );
						
				}else{
					where = builder.and( where, builder.equal( campoTabela, valorProcurado ) );
				}
			}
		}
		
		return where;
	}

	private Order criarOrdemPaginacao(Paginacao paginacao, final Root<T> tabela) {
		
		if( paginacao.getOrdenacao() != null && paginacao.getOrdenacao().getMetodoOrdenacao() != null ){
			return new OrderImpl( tabela.get( paginacao.getOrdenacao().getCampoOrdenado() ), 
					paginacao.getOrdenacao().getMetodoOrdenacao().isAscendente() );
		}
		
		return null;
	}
		
	@Override
	public List<T> listarTudo(){
		final TypedQuery<T> typedQuery = gerarTypedQuerySemWhere();
		
		return typedQuery.getResultList();
	}

	private TypedQuery<T> gerarTypedQuerySemWhere() {
		final CriteriaBuilder builder = em.getCriteriaBuilder();
		final CriteriaQuery<T> query = builder.createQuery( classeEntidade );
		
		final Root<T> from = query.from( classeEntidade );
		
		final TypedQuery<T> typedQuery = em.createQuery(  query.select( from ) );
		
		return typedQuery;
	}
	
	@Override
	public T atualizar( final T entidade ){
		return em.merge( entidade );
	}

	@Override
	public <ID> void apagarPorId(final ID IdEntidade) {
		em.remove( pesquisarPorId( IdEntidade ) );		
	}

	@Override
	public void apagar(final T entidade) {
		em.remove( em.merge( entidade ) );
	}

	@Override
	public List<T> pesquisarPorCamposIguaisPreenchidos(final T entidade) {
		final CriteriaBuilder builder = em.getCriteriaBuilder();
		final CriteriaQuery<T> query = builder.createQuery( classeEntidade );
		final Root<T> from = query.from( classeEntidade );
		
		final Predicate predicate = gerarPredicateDinamicoCamposPreenchidos(builder, from, entidade );
		
		final TypedQuery<T> typedQuery = em.createQuery( query.select( from ).where( predicate ) );	
		
		return typedQuery.getResultList();
	}

	private Predicate gerarPredicateDinamicoCamposPreenchidos( final CriteriaBuilder builder, final Root<T> from, final T entidade ) {
		
		Predicate predicate = builder.and();
		
		for( final Method method : entidade.getClass().getDeclaredMethods() ){
					
			try {
				if( MetodoObjetoUtil.isMetodoGetter( method ) ){
					final Object valorCampo = method.invoke( entidade, (Object[]) null);
				
					if( valorCampo != null ){
						final String nomeCampo = MetodoObjetoUtil.obterNomeCampoPeloMetodoAcessorio( method );						
						
						predicate = builder.and( predicate, 
								builder.equal( from.get( nomeCampo ), valorCampo ) );
					}
				}
				
			} catch (final IllegalArgumentException | InvocationTargetException | IllegalAccessException e) {
				log.warn( "Erro no método DAOGenerico<T>.gerarPredicateDinamicoCamposPreenchidos", e );
			}
		}
		
		return predicate;
	}
}
