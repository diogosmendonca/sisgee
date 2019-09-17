package br.cefetrj.sisgee.model.dao;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
/**
 * Implementacao do padrao DAO para pesquisa especifica do Termo Estagio
 * 
 * @author Marcos Eduardo
 * @since 1.0
 *
 */

public class TermoEstagioDAO  {
	

	public List<Object[]> buscarFiltrado(Boolean obrigatorio , Date inicio, Date termino){
		EntityManagerFactory factory =
				Persistence.createEntityManagerFactory("sisgeePU");
		EntityManager manager = factory.createEntityManager();
			
			Query query = manager
				.createNativeQuery("select te.idtermoestagio, cur.nomecurso, te.datarescisaotermoestagio " + 
						"from termoestagio te " + 
						"inner join aluno a " + 
						"on te.aluno_idaluno = a.idaluno " + 
						"inner join curso cur " + 
						"on a.curso_idcurso = cur.idcurso " + 
						"inner join campus c " + 
						"on cur.campus_idcampus = c.idcampus " + 
						"where te.datainiciotermoestagio > :inicio " + 
						"and te.datainiciotermoestagio < :termino " + 
						"and te.eestagioobrigatorio = :obrigatorio");
		
		query.setParameter("obrigatorio", obrigatorio);
		query.setParameter("inicio", inicio);
		query.setParameter("termino", termino);
				
		@SuppressWarnings("unchecked")
		List<Object[]> authors = query.getResultList();
		 
		manager.close();
		factory.close();
		 return  authors;
	}
	
        /**
         * 
         * @param inicio
         * @param termino
         * @return 
         */
	public List<Object[]> buscarFiltrado( Date inicio, Date termino){
		EntityManagerFactory factory =
				Persistence.createEntityManagerFactory("sisgeePU");
		EntityManager manager = factory.createEntityManager();
			
			Query query = manager
				.createNativeQuery("select te.idtermoestagio, cur.nomecurso, te.datarescisaotermoestagio " + 
						"from termoestagio te " + 
						"inner join aluno a " + 
						"on te.aluno_idaluno = a.idaluno " + 
						"inner join curso cur " + 
						"on a.curso_idcurso = cur.idcurso " + 
						"inner join campus c " + 
						"on cur.campus_idcampus = c.idcampus " + 
						"where te.datainiciotermoestagio >= :inicio " + 
						"and :termino >= te.datainiciotermoestagio ");
		
		query.setParameter("inicio", inicio);
		query.setParameter("termino", termino);
				
		@SuppressWarnings("unchecked")
		List<Object[]> authors = query.getResultList();
		 
		manager.close();
		factory.close();
		 return  authors;
	}

}