package br.cefetrj.sisgee.model.dao;

import br.cefetrj.sisgee.model.entity.Convenio;
import br.cefetrj.sisgee.model.entity.Empresa;
import br.cefetrj.sisgee.model.entity.Pessoa;
import java.util.List;

import javax.persistence.TypedQuery;

/**
 * Implementacao do padrao DAO para pesquisa especifica de Convenio
 *
 * @author Matheus
 */
public class ConvenioDAO extends GenericDAO<Convenio> {

    public ConvenioDAO() {
        super(Convenio.class, PersistenceManager.getEntityManager());
    }

    /**
     * Método que busca por numero da empresa.
     *
     * @param numero
     * 
     * @param emp
     * 
     * @return convênio
     * 
     */
    public Convenio buscarByNumeroEmpresa(String numero, Empresa emp) {
        manager.clear();
        return (Convenio) manager.createQuery(
                "SELECT c FROM Convenio c WHERE c.numeroConvenio LIKE :numero AND c.empresa = :empresa")
                .setParameter("numero", numero)
                .setParameter("empresa", emp)
                .getResultList();
    }

    /**
     * Método que busca por numero do convenio.
     *
     * @param numeroConvenio
     * 
     * @return convênio
     * 
     */
    public Convenio buscarByNumero(String numeroConvenio) {
        manager.clear();
        System.out.println("ENTROU BUSCAR NUMERO CONVENIO DAO");
        System.out.println(numeroConvenio);
        TypedQuery<Convenio> query = manager.createQuery(
                "SELECT a FROM Convenio a WHERE a.numero LIKE :numeroConvenio", Convenio.class);
                query.setParameter("numeroConvenio", numeroConvenio);
                query.setMaxResults(1);
                List<Convenio> convenio = query.getResultList();
                return convenio.isEmpty() ?  null : convenio.get(0);
    }

    /**
     * Método que busca por empresa.
     *
     * @param emp
     * 
     * @return convênio
     * 
     */
    public Convenio buscarByEmpresa(Empresa emp) {
        manager.clear();
        return (Convenio) manager.createQuery(
                "SELECT a FROM Convenio a WHERE a.empresa = :emp")
                .setParameter("emp", emp)
                .getSingleResult();
    }

    /**
     * Método que busca por pessoa.
     *
     * @param pess
     * 
     * @return convênio
     * 
     */
    public Convenio buscarByPessoa(Pessoa pess) {
        manager.clear();
        return (Convenio) manager.createQuery(
                "SELECT a FROM Convenio a WHERE a.pessoa = :pess")
                .setParameter("pess", pess)
                .getSingleResult();
    }
}
