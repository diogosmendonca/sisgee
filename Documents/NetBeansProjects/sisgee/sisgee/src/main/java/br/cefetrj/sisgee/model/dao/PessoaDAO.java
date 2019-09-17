/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetrj.sisgee.model.dao;

import br.cefetrj.sisgee.model.entity.AgenteIntegracao;
import br.cefetrj.sisgee.model.entity.Empresa;
import br.cefetrj.sisgee.model.entity.Pessoa;
import java.util.List;
import javax.persistence.EntityManager;

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
        return (Pessoa) manager.createQuery(
                "SELECT e FROM Pessoa e WHERE e.cpf LIKE :cpf")
                .setParameter("cpf", cpf)
                .getSingleResult();
    }
}
