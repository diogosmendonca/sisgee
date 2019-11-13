package br.cefetrj.sisgee.model.dao;

import br.cefetrj.sisgee.model.entity.Convenio;
import br.cefetrj.sisgee.model.entity.Empresa;
import br.cefetrj.sisgee.model.entity.Pessoa;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 * Implementacao do padrao DAO para pesquisa especifica de Convenio
 * @author Matheus
 */
public class ConvenioDAO extends GenericDAO<Convenio> {
	
	public ConvenioDAO() {
		super(Convenio.class, PersistenceManager.getEntityManager());
	}
	/**
	 * Método para retornar uma lista de convênios criados entre esse período
	 * @param registroInicio
	 * @param registroFim
	 * @return list
	 */
	public List<Object[]> conveniosFiltrado(Date registroInicio, Date registroFim){
		EntityManagerFactory factory =
				Persistence.createEntityManagerFactory("sisgeePU");
		EntityManager manager = factory.createEntityManager();
			
			Query query = manager
				.createNativeQuery(
						"SELECT c FROM Convenio c WHERE c.dataregistro BETWEEN :registroInicio AND :registroFim");
		
		query.setParameter("registroInicio", registroInicio);
		query.setParameter("registroFim", registroFim);
				
		@SuppressWarnings("unchecked")
		List<Object[]> authors = query.getResultList();
		 
		manager.close();
		factory.close();
		 return  authors;
	}
	
	
        /**
         * MÃ©todo que busca por numero da empresa
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
         * MÃ©todo que busca por numero do convenio
         * @param numeroConvenio
         * @return 
         */
        public Convenio buscarByNumero(String numeroConvenio){
            System.out.println("ENTROU BUSCAR NUMERO CONVENIO DAO");
		return (Convenio) manager.createQuery(
		    "SELECT a FROM Convenio a WHERE a.numero LIKE :numeroConvenio")
		    .setParameter("numeroConvenio", numeroConvenio)
		    .getSingleResult();
	}
        
        /**
         * MÃ©todo que busca por empresa
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
         * MÃ©todo que busca por pessoa
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
