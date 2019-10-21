/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetrj.sisgee.model.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import br.cefetrj.sisgee.model.entity.Pessoa;

/**
 * Implementacao do padrao DAO para pesquisa especifica de Pessoa
 *
 * @author Lucas Lima
 */
public class PessoaDAO extends GenericDAO<Pessoa> {

    public PessoaDAO() {
        super(Pessoa.class, PersistenceManager.getEntityManager());
    }

    /**
     * Método que busca por lista de nome
     * @param nomeX
     * @return 
     */
    public List<Pessoa> buscarByNomeList(String nomeX) {
        return (List<Pessoa>) manager.createQuery(
                "SELECT e FROM Pessoa e WHERE LOWER (e.nome) LIKE LOWER (:nomeX)")
                .setParameter("nomeX", nomeX + "%")
                .getResultList();
    }

    /**
     * Método que busca por cpf 
     * @param cpf
     * @return 
     */
    public Pessoa buscarByCpf(String cpf) {
    	manager.clear();
    	
    	TypedQuery<Pessoa> query = manager.createQuery("SELECT e FROM Pessoa e WHERE e.cpf LIKE :cpf",
				Pessoa.class);
		query.setParameter("cpf", cpf);
		query.setMaxResults(1);
		List<Pessoa> resultado = query.getResultList();
		return resultado.isEmpty() ? null : resultado.get(0);
		
    }
    
    /**
     * Método de busca pelo nome da Pessoa
     * @param nome
     * @return a primeira ocorrência de pessoa com o nome buscado
     */
    public Pessoa buscarByNome(String nome) {

		manager.clear();
		TypedQuery<Pessoa> query = manager.createQuery("SELECT p FROM Pessoa p WHERE p.nome LIKE :nome",
				Pessoa.class);
		query.setParameter("nome", nome);
		query.setMaxResults(1);
		List<Pessoa> resultado = query.getResultList();
		return resultado.isEmpty() ? null : resultado.get(0);
	}
}