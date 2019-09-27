package br.cefetrj.sisgee.model.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import br.cefetrj.sisgee.model.entity.Empresa;

/**
 * Implementacao do padrao DAO para pesquisa especifica de Empresa
 * @author Matheus
 */
public class EmpresaDAO extends GenericDAO<Empresa> {

	public EmpresaDAO() {
		super(Empresa.class, PersistenceManager.getEntityManager());
	}
	
        /**
         * Método que busca por cnpj.
         * 
         * @param cnpj
         * 
         * @return Empresa
         * 
         */
	public Empresa buscarByCnpj(String cnpj){
		return (Empresa) manager.createQuery(
		    "SELECT e FROM Empresa e WHERE e.cnpjEmpresa LIKE :cnpj")
		    .setParameter("cnpj", cnpj)
		    .getSingleResult();
	}
	
        /**
         * Método que busca por uma lista de empresas de mesmo nome
         * 
         * @param nomeX
         * 
         * @return lista de empresas
         * 
         */
	public List<Empresa> buscarByNomeList(String nomeX){

		TypedQuery<Empresa> query = manager.createQuery(
				 "SELECT e FROM Empresa e WHERE LOWER (e.razaoSocial) LIKE LOWER (:nomeX)", Empresa.class);
                
			    query.setParameter("nomeX", "%"+nomeX+"%");
               
                List<Empresa> empresas = query.getResultList();
                return empresas.isEmpty() ?  null : empresas;
	}
	
        
        /**
         * Método que busca por nome da empresa.
         * 
         * @param nomeX
         * 
         * @return empresa
         * 
         */
        public Empresa buscarByNome(String nomeX){
		return (Empresa) manager.createQuery(
		    "SELECT e FROM Empresa e WHERE LOWER (e.razaoSocial) LIKE LOWER (:nomeX)")
                    
		    .setParameter("nomeX", nomeX)
		    .getSingleResult();
	}

}
