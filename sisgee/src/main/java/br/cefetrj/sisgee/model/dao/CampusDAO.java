package br.cefetrj.sisgee.model.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import br.cefetrj.sisgee.model.entity.Campus;

/**
 * Implementacao do padrao DAO para pesquisa especifica de Campus
 * 
 * @author Paulo Cantuária
 */
public class CampusDAO extends GenericDAO<Campus> {

	public CampusDAO() {
		super(Campus.class, PersistenceManager.getEntityManager());
	}

	/**
	 * Método que busca pelo nome do campus no banco.
	 * 
	 * @param siglaCurso
	 * @return
	 */
	public Campus buscarByNome(String nomeCampus) {

		manager.clear();
		TypedQuery<Campus> query = manager.createQuery("SELECT c FROM Campus c WHERE c.nomeCampus LIKE :nomeCampus",
				Campus.class);
		query.setParameter("nomeCampus", nomeCampus);
		query.setMaxResults(1);
		List<Campus> resultado = query.getResultList();
		return resultado.isEmpty() ? null : resultado.get(0);
	}
}