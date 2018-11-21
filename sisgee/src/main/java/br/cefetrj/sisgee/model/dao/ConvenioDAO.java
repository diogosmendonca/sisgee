package br.cefetrj.sisgee.model.dao;

import br.cefetrj.sisgee.model.entity.Convenio;
import br.cefetrj.sisgee.model.entity.Empresa;
import br.cefetrj.sisgee.model.entity.Pessoa;
import java.util.List;

/**
 * Implementacao do padrao DAO para pesquisa especifica de Convenio
 * @author Matheus
 */
public class ConvenioDAO extends GenericDAO<Convenio> {
	
	public ConvenioDAO() {
		super(Convenio.class, PersistenceManager.getEntityManager());
	}
	
        /**
         * Método que busca por numero da empresa
         * @param numero
         * @param emp
         * @return 
         */
	public Convenio buscarByNumeroEmpresa(String numero, Empresa emp){
		return (Convenio) manager.createQuery(
		    "SELECT c FROM Convenio c WHERE c.numeroConvenio LIKE :numero AND c.empresa = :empresa")
		    .setParameter("numero", numero)
		    .setParameter("empresa", emp)
		    .getSingleResult();
	}
        
        /**
         * Método que busca por numero do convenio
         * @param numeroConvenio
         * @return 
         */
        public Convenio buscarByNumero(String numeroConvenio){
            System.out.println("ENTROU BUSCAR NUMERO CONVENIO DAO");
            System.out.println(numeroConvenio);
		return (Convenio) manager.createQuery(
		    "SELECT a FROM Convenio a WHERE a.numero LIKE :numeroConvenio")
		    .setParameter("numeroConvenio", numeroConvenio)
		    .getSingleResult();
	}
        
        /**
         * Método que busca por empresa
         * @param emp
         * @return 
         */
        public Convenio buscarByEmpresa(Empresa emp){
		return (Convenio) manager.createQuery(
		    "SELECT a FROM Convenio a WHERE a.empresa = :emp")
		    .setParameter("emp", emp)
		    .getSingleResult();
	}
        
        /**
         * Método que busca por pessoa
         * @param pess
         * @return 
         */
        public Convenio buscarByPessoa(Pessoa pess){
		return (Convenio) manager.createQuery(
		    "SELECT a FROM Convenio a WHERE a.pessoa = :pess")
		    .setParameter("pess", pess)
		    .getSingleResult();
	}
}
