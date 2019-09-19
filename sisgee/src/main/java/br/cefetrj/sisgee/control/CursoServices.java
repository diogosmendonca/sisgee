package br.cefetrj.sisgee.control;

import br.cefetrj.sisgee.model.dao.CursoDAO;
import br.cefetrj.sisgee.model.dao.PersistenceManager;
import br.cefetrj.sisgee.model.entity.Curso;

/**
 * Serviços de alunos. Trata a lógica de negócios
 * associada com a entidade Curso.
 * 
 * @author Paulo Cantuária
 * 
 */
public class CursoServices {
			
        /**
         * Método que inclui um curso
         * @param curso 
         */
	public static void incluirCurso(Curso curso){		

		PersistenceManager.getTransaction().begin();
		try{
			new CursoDAO().incluir(curso);
			PersistenceManager.getTransaction().commit();
		}catch(Exception e){
			PersistenceManager.getTransaction().rollback();
		}
	}
}