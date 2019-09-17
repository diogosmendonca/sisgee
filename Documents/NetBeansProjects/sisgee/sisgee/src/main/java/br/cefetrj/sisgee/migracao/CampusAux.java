/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetrj.sisgee.migracao;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author c18634659798
 */
public class CampusAux {
    @Id
    @GeneratedValue
    private Integer idCampus;

    @Column(length = 100, nullable = false, unique = true)
    private String nomeCampus;

    /*Tem que gerar também os getters e setters
    @OneToMany(mappedBy = "campus")
    private List<CursoAux> cursos;*/

    /**
     * @return the idCampus
     */
    public Integer getIdCampus() {
        return idCampus;
    }

    /**
     * @param idCampus the idCampus to set
     */
    public void setIdCampus(Integer idCampus) {
        this.idCampus = idCampus;
    }

    /**
     * @return the nomeCampus
     */
    public String getNomeCampus() {
        return nomeCampus;
    }

    /**
     * @param nomeCampus the nomeCampus to set
     */
    public void setNomeCampus(String nomeCampus) {
        this.nomeCampus = nomeCampus;
    }

    
}
