package br.com.schimidtsolutions.jsf.dao;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;

import br.com.schimidtsolutions.jsf.util.MetodoObjetoUtil;

class DAOGenerico<T> implements DAO<T> {
	private static final long serialVersionUID = 6020966045581794498L;

	@Inject @Singleton
	private transient Logger log;
	
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
	public <ID> T pesquisarPorId( final ID entidade ){
		return em.find( classeEntidade, entidade );
	}
	
	
	@Override
	public List<T> listarComPaginacao( final int paginaInicial, final int tamanhoPagina ){
		
		final TypedQuery<T> query = gerarTypedQuerySemWhere();
		
		query.setFirstResult( paginaInicial );
		query.setMaxResults( tamanhoPagina );
		
		return query.getResultList();
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
	public void atualizar( final T entity ){
		em.merge( entity );
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
