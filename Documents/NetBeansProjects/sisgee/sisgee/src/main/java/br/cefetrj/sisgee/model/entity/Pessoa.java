package br.cefetrj.sisgee.model.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 * Classe Pessoa para Registrar informações relacionadas a Aluno e Convênio 
 * 
 * @author Lucas Lima
 * @since 1.0
 */
@Entity
public class Pessoa implements Serializable {

    @Id
    @GeneratedValue
    private Long idPessoa;

    @Column(length = 100, nullable = false)
    private String nome;
    
    @Column(length = 11, nullable = false)
    private String cpf;

    @Column(length = 11)
    private String telefone;

    @Column(length = 50)
    private String email;
    
    @OneToOne(mappedBy = "pessoa")
    @JoinColumn()
    private Convenio convenio;

    @OneToMany(mappedBy = "pessoa")
    private List<Aluno> alunos;

    public Pessoa() {
    }

    public Pessoa(Long idPessoa, String nome) {
        this.idPessoa = idPessoa;
        this.nome = nome;
    }

    public Pessoa(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }
    
    
    public Long getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(Long idPessoa) {
        this.idPessoa = idPessoa;
    }

    public String getNome() {
        return nome;
    }

    

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }

    public String getCpf() {
        return cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    public Convenio getConvenio() {
        return convenio;
    }

    public void setConvenio(Convenio convenio) {
        this.convenio = convenio;
    }
    

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idPessoa == null) ? 0 : idPessoa.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Pessoa other = (Pessoa) obj;
        if (idPessoa == null) {
            if (other.idPessoa != null) {
                return false;
            }
        } else if (!idPessoa.equals(other.idPessoa)) {
            return false;
        }
        return true;
    }

    public String toString() {
        return nome;
    }
}
