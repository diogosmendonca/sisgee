package br.cefetrj.sisgee.model.dao;

import java.util.List;

import javax.persistence.EntityManager;

/**
 * Implementação do padrão DAO conforme mostrado nas aulas
 * @author Paulo Cantuária
 * @since 1.0
 *
 * @param <T> Tipo da classe
 */
public class GenericDAO<T> {

	protected EntityManager manager;
	protected Class<T> t;
	
	GenericDAO(Class<T> t, EntityManager manager){
		this.t = t;
		this.manager = manager;
                
	}
	
        /**
         * Método que busca e retorna uma lista
         * @return 
         */
	public List<T> buscarTodos(){
		@SuppressWarnings("unchecked")
		List<T> lista = manager.createQuery("from " + t.getName()).getResultList();
				
		return lista;
	}
	
        /**
         * Método padrão de busca
         * @param id
         * @return 
         */
	public T buscar(Integer id){
            T t1 = manager.find(t, id);
            return t1;
	}
	
        /**
         * Método padrão de inclusão
         * @param entidade 
         */
	public void incluir(T entidade){
		manager.persist(entidade);
	}
	
        /**
         * Método padrão de exclusão
         * @param entidade 
         */
	public void excluir(T entidade){
		manager.remove(entidade);
	}
	
        /**
         * Método padrão de alteração
         * @param entidade 
         */
	public void alterar(T entidade){
		manager.merge(entidade);
	}
	
	
	
}
