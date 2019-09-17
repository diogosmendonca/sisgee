/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetrj.sisgee.migracao;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author c18634659798
 */
@Entity
public class CursoAux {
    private static long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue
    private long id;
    private String codigo;
    private String nome;
    /* Tem que gerar também os getters e setters
    @ManyToOne(fetch=FetchType.EAGER)
    private CampusAux campus; */
		
    @OneToMany(mappedBy="cursoAux")
    private List<Estudante> alunos;

    /**
     * @return the serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     * @param aSerialVersionUID the serialVersionUID to set
     */
    public static void setSerialVersionUID(long aSerialVersionUID) {
        serialVersionUID = aSerialVersionUID;
    }

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return the cod
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * @param cod the cod to set
     */
    public void setCodigo(String cod) {
        this.codigo = cod;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

   

    /**
     * @return the alunos
     */
    public List<Estudante> getAlunos() {
        return alunos;
    }

    /**
     * @param alunos the alunos to set
     */
    public void setAlunos(List<Estudante> alunos) {
        this.alunos = alunos;
    }
}
