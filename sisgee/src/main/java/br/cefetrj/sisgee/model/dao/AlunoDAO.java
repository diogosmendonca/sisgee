package br.cefetrj.sisgee.model.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import br.cefetrj.sisgee.model.entity.Aluno;

/**
 * Implementacao do padrao DAO para pesquisa especifica de Aluno
 * 
 * @author Matheus
 */
public class AlunoDAO extends GenericDAO<Aluno> {

	public AlunoDAO() {
		super(Aluno.class, PersistenceManager.getEntityManager());
	}

	/**
	 * Método que busca por matricula no banco.
	 * 
	 * @param matricula
	 * @return
	 */
	public Aluno buscarByMatricula(String matricula) {

		manager.clear();
		TypedQuery<Aluno> query = manager.createQuery("SELECT a FROM Aluno a WHERE a.matricula LIKE :matricula",
				Aluno.class);
		query.setParameter("matricula", matricula);
		query.setMaxResults(1);
		List<Aluno> alunos = query.getResultList();
		return alunos.isEmpty() ? null : alunos.get(0);
	}
}