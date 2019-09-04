package br.cefetrj.sisgee.control;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import br.cefetrj.sisgee.model.dao.GenericDAO;
import br.cefetrj.sisgee.model.dao.PersistenceManager;
import br.cefetrj.sisgee.model.dao.TermoAditivoDAO;
import br.cefetrj.sisgee.model.entity.TermoAditivo;
import br.cefetrj.sisgee.model.entity.TermoEstagio;

/**
 * Serviços de TermoAditivo. Trata a lógica de negócios associada com a entidade
 * TermoAditivo.
 *
 * @author Paulo Cantuária
 * @since 1.0
 */
public class TermoAditivoServices {

	/**
	 * Recupera todos os termos aditivos do banco de dados.
	 * 
	 * @return lista de termos aditivos
	 * 
	 */
    public static List<TermoAditivo> listarTermoAditivo() {
        GenericDAO<TermoAditivo> termoAditivoDao = PersistenceManager.createGenericDAO(TermoAditivo.class);
        return termoAditivoDao.buscarTodos();
    }

    /**
     * Método para persistir um termo aditivo no banco.
     *
     * @param termoAditivo Termo aditivo a ser persistido
     * 
     */
    public static void incluirTermoAditivo(TermoAditivo termoAditivo) {
        GenericDAO<TermoAditivo> termoAditivoDao = PersistenceManager.createGenericDAO(TermoAditivo.class);
        PersistenceManager.getTransaction().begin();
        try {
            termoAditivoDao.incluir(termoAditivo);
            PersistenceManager.getTransaction().commit();
        } catch (Exception e) {
            //TODO remover saída do console
            System.out.println(e);
            PersistenceManager.getTransaction().rollback();
        }
    }
    
    
    /**
     * Método que altera um dado de um termo aditivo.
     * 
     * @param termoAditivo
     */
    public static void atualizarTermoAditivo(TermoAditivo termoAditivo) {
        GenericDAO<TermoAditivo> termoAditivoDao = PersistenceManager.createGenericDAO(TermoAditivo.class);
        PersistenceManager.getTransaction().begin();
        try {
            termoAditivoDao.alterar(termoAditivo);
            PersistenceManager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e);
            PersistenceManager.getTransaction().rollback();
        }
    }

    /**
     * Método para buscar um termo aditivo por id.
     *
     * @param idTermoAditivo Id do termo aditivo
     * 
     * @return termo aditivo referente ao id passado
     * 
     */
    public static TermoAditivo buscarTermoAditivo(Integer idTermoAditivo) {
        GenericDAO<TermoAditivo> termoAditivoDao = PersistenceManager.createGenericDAO(TermoAditivo.class);
        return termoAditivoDao.buscar(idTermoAditivo);
    }

    /**
     * Método para excluir um termo aditivo no banco.
     *
     * @param termoAditivo Termo aditivo a ser persistido
     * 
     */
    public static void excluirTermoAditivo(TermoAditivo termoAditivo) throws Exception {
        boolean ultimo = false;
        GenericDAO<TermoAditivo> termoAditivoDao = PersistenceManager.createGenericDAO(TermoAditivo.class);
        List<TermoEstagio> listaTermoEstagio = TermoEstagioServices.listarTermoEstagio();

        for (TermoEstagio termo : listaTermoEstagio) {
            List<TermoAditivo> aditivos = termo.getTermosAditivos();
            System.out.println(termo.getIdTermoEstagio());
            System.out.println(termo.getTermosAditivos());//TODO retirar saida do console
            Collections.sort(aditivos);
            if (aditivos != null && aditivos.size() != 0 && termoAditivo.equals(aditivos.get(aditivos.size() - 1))) {
                ultimo = true;
                break;
            }
        }
        if (ultimo == false) {
            throw new Exception();
        }
        if (!PersistenceManager.getTransaction().isActive()) {
            PersistenceManager.getTransaction().begin();
        }
        try {
            termoAditivoDao.excluir(termoAditivo);
            PersistenceManager.getTransaction().commit();
        } catch (Exception e) {
            //TODO remover saída do console
            System.out.println(e);
            PersistenceManager.getTransaction().rollback();
        }
    }

    /**
     *
     * Metodo para receber uma matriz de com conteudo do banco.
     *
     * @author Marcos Eduardo
     * @param obrigatorio boolean do form para filtrar resultado
     * @param inicio date do form para filtrar resultado
     * @param termino date do form para filtrar resultado
     * @return author matriz com conteúdo obtido do banco ou null
     * 
     */
    public static List<Object[]> listarTermoAditivoFiltrado(Boolean obrigatorio, Date inicio, Date termino) {
        TermoAditivoDAO termoAditivoDAO = new TermoAditivoDAO();

        try {
            List<Object[]> author = null;

            if (obrigatorio == null) {
                author = termoAditivoDAO.buscarFiltrado(inicio, termino);
            } else {
                author = termoAditivoDAO.buscarFiltrado(obrigatorio, inicio, termino);
            }
            return author;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Método que recupera um Termo Estágio e o modifica caso haja um termo aditivo com as informações do termo aditivo.
     * 
     * @param termoAditivo
     * 
     * @return Termo Estágio
     * 
     */
    
    public static TermoEstagio termoEstagioAtualizadoByTermoAditivo(TermoAditivo termoAditivo) {
        TermoEstagio termoEstagio = TermoEstagioServices.buscarTermoEstagio(termoAditivo.getTermoEstagio().getIdTermoEstagio());

        if (termoAditivo != null) {
            if (termoAditivo.getDataFimTermoAditivo() != null) {
                termoEstagio.setDataFimTermoEstagio(termoAditivo.getDataFimTermoAditivo());
            }

            if (termoAditivo.getCargaHorariaTermoAditivo() != null) {
                termoEstagio.setCargaHorariaTermoEstagio(termoAditivo.getCargaHorariaTermoAditivo());
            }

            if (termoAditivo.getValorBolsaTermoAditivo() != null) {
                termoEstagio.setValorBolsa(termoAditivo.getValorBolsaTermoAditivo());
            }

            if (termoAditivo.getEnderecoTermoAditivo() != null) {
                termoEstagio.setEnderecoTermoEstagio(termoAditivo.getEnderecoTermoAditivo());
            }

            if (termoAditivo.getNumeroEnderecoTermoAditivo() != null) {
                termoEstagio.setNumeroEnderecoTermoEstagio(termoAditivo.getNumeroEnderecoTermoAditivo());
            }

            if (termoAditivo.getComplementoEnderecoTermoAditivo() != null) {
                termoEstagio.setComplementoEnderecoTermoEstagio(termoAditivo.getComplementoEnderecoTermoAditivo());
            }

            if (termoAditivo.getBairroEnderecoTermoAditivo() != null) {
                termoEstagio.setBairroEnderecoTermoEstagio(termoAditivo.getBairroEnderecoTermoAditivo());
            }

            if (termoAditivo.getCepEnderecoTermoAditivo() != null) {
                termoEstagio.setCepEnderecoTermoEstagio(termoAditivo.getCepEnderecoTermoAditivo());
            }

            if (termoAditivo.getCidadeEnderecoTermoAditivo() != null) {
                termoEstagio.setCidadeEnderecoTermoEstagio(termoAditivo.getCidadeEnderecoTermoAditivo());
            }

            if (termoAditivo.getProfessorOrientador() != null) {
                termoEstagio.setProfessorOrientador(termoAditivo.getProfessorOrientador());
            }
        }

        return termoEstagio;
    }
}
