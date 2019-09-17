package br.cefetrj.sisgee.model.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author Lucas Lima
 * @since 2.0
 *
 */
@Entity
public class Empresa implements Serializable {

    @Id
    @GeneratedValue
    private Integer idEmpresa;

    @Column(length = 14, nullable = false, unique = true)
    private String cnpjEmpresa;

   

    @Column(length = 100)
    private String razaoSocial;

    @Column()
    private boolean agenteIntegracao = false;
    
    @Column(length = 50)
    private String emailEmpresa;
    
    @Column(length = 11)
    private String telefoneEmpresa;

    @Column(length = 50)
    private String contatoEmpresa;

    @OneToMany(mappedBy = "empresa")
    private List<Convenio> convenio;

    public Empresa() {
    }

    public Empresa(String cnpjEmpresa,  String razaoSocial, boolean agenteIntegracao) {

        this.cnpjEmpresa = cnpjEmpresa;
        this.razaoSocial = razaoSocial;
        this.agenteIntegracao = agenteIntegracao;

    }

    public Empresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public String getEmailEmpresa() {
        return emailEmpresa;
    }

    public void setEmailEmpresa(String emailEmpresa) {
        this.emailEmpresa = emailEmpresa;
    }

    public String getTelefoneEmpresa() {
        return telefoneEmpresa;
    }

    public void setTelefoneEmpresa(String telefoneEmpresa) {
        this.telefoneEmpresa = telefoneEmpresa;
    }

    public void setIdEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getCnpjEmpresa() {
        return cnpjEmpresa;
    }

    public void setCnpjEmpresa(String cnpjEmpresa) {
        this.cnpjEmpresa = cnpjEmpresa;
    }

    

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public boolean isAgenteIntegracao() {
        return agenteIntegracao;
    }

    public void setAgenteIntegracao(boolean agenteIntegracao) {
        this.agenteIntegracao = agenteIntegracao;
    }

    public String getContatoEmpresa() {
        return contatoEmpresa;
    }

    public void setContatoEmpresa(String contatoEmpresa) {
        this.contatoEmpresa = contatoEmpresa;
    }

    public List<Convenio> getConvenio() {
        return convenio;
    }

    public void setConvenio(List<Convenio> convenio) {
        this.convenio = convenio;
    }

   
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idEmpresa == null) ? 0 : idEmpresa.hashCode());
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
        Empresa other = (Empresa) obj;
        if (idEmpresa == null) {
            if (other.idEmpresa != null) {
                return false;
            }
        } else if (!idEmpresa.equals(other.idEmpresa)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return razaoSocial;
    }

}
