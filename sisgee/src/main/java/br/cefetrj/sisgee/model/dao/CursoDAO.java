package br.cefetrj.sisgee.model.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import br.cefetrj.sisgee.model.entity.Curso;

/**
 * Implementacao do padrao DAO para pesquisa especifica de Curso
 * 
 * @author Paulo Cantuária
 */
public class CursoDAO extends GenericDAO<Curso> {

	public CursoDAO() {
		super(Curso.class, PersistenceManager.getEntityManager());
	}

	/**
	 * Método que busca pelo código do curso no banco.
	 * 
	 * @param siglaCurso
	 * @return
	 */
	public Curso buscarByCodigo(String codigoCurso) {

		manager.clear();
		TypedQuery<Curso> query = manager.createQuery("SELECT c FROM Curso c WHERE c.codigoCurso LIKE :codigoCurso",
				Curso.class);
		query.setParameter("codigoCurso", codigoCurso);
		query.setMaxResults(1);
		List<Curso> resultado = query.getResultList();
		return resultado.isEmpty() ? null : resultado.get(0);
	}
}