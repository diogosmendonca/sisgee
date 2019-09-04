package br.cefetrj.sisgee.model.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * Implementação do encapsulamento da persistência
 * conforme mostrado nas aulas
 * @author paducantuaria
 * @since 1.0
 */
public class PersistenceManager {
	
	private static EntityManagerFactory factory =
			Persistence.createEntityManagerFactory("sisgeePU");
	private static EntityManager manager = factory.createEntityManager();
	
	/**
	 * Método que retorna um EntityManager.
	 * 
	 * @return entitymanager
	 */
	
	static EntityManager getEntityManager(){
		return manager;
	}
	
	/**
	 * Método que retorna um novo GenericDAO de uma classe qualquer.
	 * 
	 * @param <T>
	 * 
	 * @param t
	 * 
	 * @return GenericDao
	 */
	
	public static <T> GenericDAO<T> createGenericDAO(Class<T> t) {
		return new GenericDAO<T>(t, manager);
	}
	
	/**
	 * Método que retorna um EntityTransaction
	 * 
	 * @return EntityTransaction
	 * 
	 */
	
	public static EntityTransaction getTransaction(){
		return manager.getTransaction();
	}

}
