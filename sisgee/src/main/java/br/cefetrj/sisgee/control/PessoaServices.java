/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetrj.sisgee.control;

import br.cefetrj.sisgee.model.dao.EmpresaDAO;
import br.cefetrj.sisgee.model.dao.GenericDAO;
import br.cefetrj.sisgee.model.dao.PersistenceManager;
import br.cefetrj.sisgee.model.dao.PessoaDAO;
import br.cefetrj.sisgee.model.entity.Empresa;
import br.cefetrj.sisgee.model.entity.Pessoa;
import java.util.List;

/**
 * Serviços da Pessoa Fisica Trata a lógica de negócios associada com a entidade
 * PessoaFisica.
 *
 * @author Lucas Lima
 */
public class PessoaServices {

    /**
     * Método que busca pesso pelo cpf
     *
     * @param cpf
     * @return
     */
    public static Pessoa buscarPessoaByCpf(String cpf) {
        PessoaDAO pessoaDao = new PessoaDAO();
        try {
            Pessoa e = pessoaDao.buscarByCpf(cpf);
            return e;
        } catch (Exception e) {
            return null;
        }

    }

    /**
     * Método que busca pessoa pelo nome
     *
     * @param nome
     * @return
     */
    public static List<Pessoa> buscarPessoaByNomeList(String nome) {
        System.out.println("BUSCOU A PESSOA PELO NOME");
        PessoaDAO pessoaDao = new PessoaDAO();
        try {
            List<Pessoa> e = pessoaDao.buscarByNomeList(nome);
            return e;
        } catch (Exception e) {
            return null;
        }

    }

    /**
     * Método que inclui uma pessoa
     *
     * @param pessoa
     */
    public static void incluirPessoa(Pessoa pessoa) {
        System.out.println("ENTROU NO INCLUIR PESSOA SERVICE");
        GenericDAO<Pessoa> pessoaDao = PersistenceManager.createGenericDAO(Pessoa.class);
        PersistenceManager.getTransaction().begin();
        try {
            pessoaDao.incluir(pessoa);
            PersistenceManager.getTransaction().commit();
        } catch (Exception e) {
            PersistenceManager.getTransaction().rollback();
        }
    }

}
