package br.cefetrj.sisgee.model.dao;

import br.cefetrj.sisgee.model.entity.Empresa;
import java.util.List;

/**
 * Implementacao do padrao DAO para pesquisa especifica de Empresa
 * @author Matheus
 */
public class EmpresaDAO extends GenericDAO<Empresa> {

	public EmpresaDAO() {
		super(Empresa.class, PersistenceManager.getEntityManager());
	}
	
        /**
         * Método que busca por cnpj
         * @param cnpj
         * @return 
         */
	public Empresa buscarByCnpj(String cnpj){
		return (Empresa) manager.createQuery(
		    "SELECT e FROM Empresa e WHERE e.cnpjEmpresa LIKE :cnpj")
		    .setParameter("cnpj", cnpj)
		    .getSingleResult();
	}
	
        /**
         * Método que busca por uma lista de nome
         * @param nomeX
         * @return 
         */
	public List<Empresa> buscarByNomeList(String nomeX){
		return (List<Empresa>) manager.createQuery(
		    "SELECT e FROM Empresa e WHERE LOWER (e.razaoSocial) LIKE LOWER (:nomeX)")
                    
		    .setParameter("nomeX", "%"+nomeX+"%")
		    .getResultList();
	}
        
        /**
         * Método que busca por nome da empresa
         * @param nomeX
         * @return 
         */
        public Empresa buscarByNome(String nomeX){
		return (Empresa) manager.createQuery(
		    "SELECT e FROM Empresa e WHERE LOWER (e.razaoSocial) LIKE LOWER (:nomeX)")
                    
		    .setParameter("nomeX", nomeX)
		    .getSingleResult();
	}

}
