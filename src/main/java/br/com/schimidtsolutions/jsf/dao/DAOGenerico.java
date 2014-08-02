package br.com.schimidtsolutions.jsf.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;

class DAOGenerico<T> implements DAO<T> {

	@NotNull(message="A classe genérica do DAO não pode ser nula!")
	private Class<T> classeEntidade;
	
	@NotNull(message="O EntityManager não pode ser nulo para o DAOGenérico!")
	private EntityManager em;

	public DAOGenerico(Class<T> classEntity, EntityManager em) {
		this.classeEntidade = classEntity;
		this.em = em;
	}
	
	@Override
	public T adicionar( T entity ){
		em.persist( entity );
		
		return entity;
	}
	
	@Override
	public void apagar( T entidade ){
		T entidadeManaged = pesquisarPorId( entidade );
		
		if( entidadeManaged != null ){
			em.remove( entidadeManaged );
		}
	}
	
	@Override
	public <ID> T pesquisarPorId( ID identity ){
		return em.find( classeEntidade, identity );
	}
	
	
	@Override
	public List<T> listarComPaginacao( int paginaInicial, int tamanhoPagina ){
		
		TypedQuery<T> query = em.createQuery( JpqlHelper.gerarSelect( classeEntidade ), classeEntidade );
		
		query.setFirstResult( paginaInicial );
		query.setMaxResults( tamanhoPagina );
		
		return query.getResultList();
	}
	

	@Override
	public List<T> listarTudo(){
		TypedQuery<T> query = em.createQuery( JpqlHelper.gerarSelect( classeEntidade ), classeEntidade );
		
		return query.getResultList();
	}
	
	private static class JpqlHelper {
		private static final String SELECT_GENERICO = "SELECT e FROM %s e";
		
		private static <T> String gerarSelect( Class<T> classeEntidade ){
			return String.format( SELECT_GENERICO, classeEntidade.getSimpleName() );
		}
	}
	
	@Override
	public void atualizar( T entity ){
		em.merge( entity );
	}
}