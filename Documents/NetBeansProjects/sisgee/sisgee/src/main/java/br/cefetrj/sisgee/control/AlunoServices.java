package br.cefetrj.sisgee.control;

import java.util.List;

import br.cefetrj.sisgee.model.dao.AlunoDAO;
import br.cefetrj.sisgee.model.dao.GenericDAO;
import br.cefetrj.sisgee.model.dao.PersistenceManager;
import br.cefetrj.sisgee.model.entity.Aluno;

/**
 * Serviços de alunos. Trata a lógica de negócios
 * associada com a entidade Aluno.
 * 
 * @author Paulo Cantuária
 * @since 1.0
 */
public class AlunoServices {
	
	/**
	 * Recupera todos os alunos e retorna em uma lista.
	 * 
	 * @return lista com todos os alunos
	 */
	public static List<Aluno> listarAlunos(){
		GenericDAO<Aluno> alunoDao = PersistenceManager.createGenericDAO(Aluno.class);
		return alunoDao.buscarTodos();
	}
	
        /**
         * Método que busca um aluno
         * @param aluno
         * @return 
         */
	public static Aluno buscarAluno(Aluno aluno) {
		GenericDAO<Aluno> alunoDao = PersistenceManager.createGenericDAO(Aluno.class);
		return alunoDao.buscar(aluno.getIdAluno());
	}
	
        /**
         * Método que inclui um aluno
         * @param aluno 
         */
	public static void incluirAluno(Aluno aluno){
		GenericDAO<Aluno> alunoDao = PersistenceManager.createGenericDAO(Aluno.class);		
		PersistenceManager.getTransaction().begin();
		try{
			alunoDao.incluir(aluno);
			PersistenceManager.getTransaction().commit();
		}catch(Exception e){
			PersistenceManager.getTransaction().rollback();
		}
	}
	
        /**
         * Método que busca aluno pela matrícula
         * @param matricula
         * @return 
         */
	public static Aluno buscarAlunoByMatricula(String matricula) {
		AlunoDAO alunoDao = new AlunoDAO();
		try{
			Aluno a = alunoDao.buscarByMatricula(matricula);
			return a;
		}catch(Exception e){
			return null;
		}
	}
	

}
