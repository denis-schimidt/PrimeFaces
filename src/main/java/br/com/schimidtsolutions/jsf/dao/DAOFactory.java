package br.com.schimidtsolutions.jsf.dao;

import java.lang.reflect.ParameterizedType;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class DAOFactory<T> {

	@PersistenceContext(unitName="estudo")
	private EntityManager em;
	
	@Produces
	public DAO<T> newInstanceDAO(InjectionPoint injectionPoint){
		ParameterizedType type = (ParameterizedType) injectionPoint.getType();   
        @SuppressWarnings("unchecked")
		Class<T> clazz = (Class<T>) type.getActualTypeArguments()[0]; 
		
		return new DAOGenerico<T>( clazz, em);
	}
}