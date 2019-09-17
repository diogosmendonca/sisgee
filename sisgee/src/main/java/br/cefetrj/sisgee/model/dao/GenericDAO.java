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
         * Método que busca e retorna uma lista de objetos da mesma classe.
         * 
         * @return lista de objetos
         * 
         */
	
	public List<T> buscarTodos(){
		@SuppressWarnings("unchecked")
		List<T> lista = manager.createQuery("from " + t.getName()).getResultList();
				
		return lista;
	}
	
        /**
         * Método padrão de busca por id
         * 
         * @param id
         * 
         * @return objeto
         * 
         */
	
	public T buscar(Integer id){
            T t1 = manager.find(t, id);
            return t1;
	}
	
        /**
         * Método padrão de persistência de um novo objeto no banco de dados.
         * 
         * @param entidade 
         * 
         * @return 
         * 
         */
	
	public void incluir(T entidade){
		manager.persist(entidade);
	}
	
        /**
         * Método padrão de persistência da exclusão de um objeto no banco de dados.
         * 
         * @param entidade 
         * 
         * @return
         */
	
	public void excluir(T entidade){
		manager.remove(entidade);
	}
	
        /**
         * Método padrão de persistência de uma alteração em um objeto no banco de dados.
         * 
         * @param entidade
         * 
         * @return
         */
	
	public void alterar(T entidade){
		manager.merge(entidade);
	}
	
	
	
}
