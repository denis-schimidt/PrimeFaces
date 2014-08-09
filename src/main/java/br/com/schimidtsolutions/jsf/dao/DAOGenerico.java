package br.com.schimidtsolutions.jsf.dao;

import java.lang.reflect.Field;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;

class DAOGenerico<T> implements DAO<T> {
	private static final long serialVersionUID = -4243971431843540736L;

	@Inject
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
	public T adicionar( final T entity ){
		em.persist( entity );
		
		return entity;
	}
	
	@Override
	public <ID> T pesquisarPorId( final ID identity ){
		return em.find( classeEntidade, identity );
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
	public T pesquisarPorCamposIguaisPreenchidos(final T entidade) {
		final CriteriaBuilder builder = em.getCriteriaBuilder();
		final CriteriaQuery<T> query = builder.createQuery( classeEntidade );
		final Root<T> from = query.from( classeEntidade );
		
		final Predicate predicate = gerarPredicateDinamicoCamposPreenchidos(builder, from, entidade );
		
		final TypedQuery<T> typedQuery = em.createQuery( query.select( from ).where( predicate ) );
		
		return typedQuery.getSingleResult();
	}

	private Predicate gerarPredicateDinamicoCamposPreenchidos(final CriteriaBuilder builder, final Root<T> from, final T entidade ) {
		Predicate predicate = builder.and();
		@SuppressWarnings("unchecked")
		final Class<T> entidadeConvertida = (Class<T>) entidade;
		
		for( final Field campo : entidadeConvertida.getDeclaredFields() ){
			
			if( campo.isAccessible() ){
				
				try {
					final Object valorCampo = campo.get(campo);
					
					if( valorCampo != null ){
						final String nomeCampo = campo.getName();
						
						predicate = builder.and( predicate, 
								builder.equal( from.get( nomeCampo ), valorCampo ) );
					}
					
				} catch (final IllegalAccessException e) {
					log.warn( "Erro no método DAOGenerico<T>.gerarPredicateDinamicoCamposPreenchidos", e );
				}
			}
		}
		
		return predicate;
	}
}
