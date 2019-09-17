package br.cefetrj.sisgee.model.dao;

import br.cefetrj.sisgee.model.entity.Aluno;

/**
 * Implementacao do padrao DAO para pesquisa especifica de Aluno
 * @author Matheus
 */
public class AlunoDAO extends GenericDAO<Aluno> {
	
	public AlunoDAO() {
		super(Aluno.class, PersistenceManager.getEntityManager());
	}
	
        /**
         * Método que busca por matricula
         * @param matricula
         * @return 
         */
	public Aluno buscarByMatricula(String matricula){
                manager.clear();
		Aluno a=(Aluno) manager.createQuery(
		    "SELECT a FROM Aluno a WHERE a.matricula LIKE :matricula")
		    .setParameter("matricula", matricula)
		    .getSingleResult();
                    
                    return a;
	}

}
