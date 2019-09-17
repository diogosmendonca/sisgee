package br.cefetrj.sisgee.control;

import java.util.List;

import br.cefetrj.sisgee.model.dao.GenericDAO;
import br.cefetrj.sisgee.model.dao.PersistenceManager;
import br.cefetrj.sisgee.model.entity.ProfessorOrientador;

/**
 * Serviços de Professores. Trata a lógica de negócios
 * associada com a entidade ProfessorOrientador.
 * 
 * @author Paulo Cantuária
 * @since 1.0
 */
public class ProfessorOrientadorServices {
	
	/**
	 * Recupera todos os professores e retorna em uma lista.
	 * 
	 * @return lista com todos os professores
	 */
	public static List<ProfessorOrientador> listarProfessorOrientador(){
		GenericDAO<ProfessorOrientador> professorOrientadorDao = PersistenceManager.createGenericDAO(ProfessorOrientador.class);
		return professorOrientadorDao.buscarTodos();
	}
	
        /**
         * Método para buscar um professor
         * @param professorOrientador
         * @return id do professor
         */
	public static ProfessorOrientador buscarProfessorOrientador(ProfessorOrientador professorOrientador) {
		GenericDAO<ProfessorOrientador> professorOrientadorDao = PersistenceManager.createGenericDAO(ProfessorOrientador.class);
		return professorOrientadorDao.buscar(professorOrientador.getIdProfessorOrientador());
	}
	
        /**
         * Método para incluir um professor
         * @param professorOrientador 
         */
	public static void incluirProfessorOrientador(ProfessorOrientador professorOrientador){
		GenericDAO<ProfessorOrientador> professorOrientadorDao = PersistenceManager.createGenericDAO(ProfessorOrientador.class);
		PersistenceManager.getTransaction().begin();
		try{
			professorOrientadorDao.incluir(professorOrientador);
			PersistenceManager.getTransaction().commit();
		}catch(Exception e){
			PersistenceManager.getTransaction().rollback();
		}
	}
	
	

}
