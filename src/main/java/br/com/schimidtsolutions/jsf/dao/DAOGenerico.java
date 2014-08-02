package br.com.schimidtsolutions.jsf.dao;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotNull;

class DAOGenerico<T> implements DAO<T> {

	@NotNull(message="A classe genérica do DAO não pode ser nula!")
	private Class<T> classEntity;
	
	@NotNull(message="O EntityManager não pode ser nulo para o DAOGenérico!")
	private EntityManager em;

	public DAOGenerico(Class<T> classEntity, EntityManager em) {
		this.classEntity = classEntity;
		this.em = em;
	}
	
	@Override
	public T adicionar( T entity ){
		em.persist( entity );
		
		return entity;
	}
	
	@Override
	public void apagar( T identity ){
		T entityManaged = pesquisarPorId( identity );
		
		if( entityManaged != null ){
			em.remove( entityManaged );
		}
	}
	
	@Override
	public <ID> T pesquisarPorId( ID identity ){
		return em.find( classEntity, identity );
	}
	
	@Override
	public void atualizar( T entity ){
		em.merge( entity );
	}
}
