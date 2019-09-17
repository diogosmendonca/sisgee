package br.cefetrj.sisgee.model.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * 
 * 
 * EXCLUIR
 * 
 * @author Paulo Cantuária
 * @since 1.0
 *
 */
@Entity
public class AgenteIntegracao {
	
	@Id
	@GeneratedValue
	private Integer idAgenteIntegracao;
	
	@Column(length=14, nullable=false, unique=true)
	private String cnpjAgenteIntegracao;
	
	@Column(length=100, nullable=false)
	private String nomeAgenteIntegracao;
	
	
	@OneToMany(mappedBy="agenteIntegracao")
	private List<Empresa> empresas;
		
	public AgenteIntegracao() {}
	
	public AgenteIntegracao(Integer idAgenteIntegracao) {
		this.idAgenteIntegracao = idAgenteIntegracao;
	}

	public Integer getIdAgenteIntegracao() {
		return idAgenteIntegracao;
	}

	public void setIdAgenteIntegracao(Integer idAgenteIntegracao) {
		this.idAgenteIntegracao = idAgenteIntegracao;
	}

	public String getCnpjAgenteIntegracao() {
		return cnpjAgenteIntegracao;
	}

	public void setCnpjAgenteIntegracao(String cnpjAgenteIntegracao) {
		this.cnpjAgenteIntegracao = cnpjAgenteIntegracao;
	}

	public String getNomeAgenteIntegracao() {
		return nomeAgenteIntegracao;
	}

	public void setNomeAgenteIntegracao(String nomeAgenteIntegracao) {
		this.nomeAgenteIntegracao = nomeAgenteIntegracao;
	}

	public List<Empresa> getEmpresas() {
		return empresas;
	}

	public void setEmpresas(List<Empresa> empresas) {
		this.empresas = empresas;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idAgenteIntegracao == null) ? 0 : idAgenteIntegracao.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AgenteIntegracao other = (AgenteIntegracao) obj;
		if (idAgenteIntegracao == null) {
			if (other.idAgenteIntegracao != null)
				return false;
		} else if (!idAgenteIntegracao.equals(other.idAgenteIntegracao))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return nomeAgenteIntegracao;
	}
	
	public AgenteIntegracao(String nomeAgenteIntegracao, String cnpjAgenteIntegracao){
		
		this.cnpjAgenteIntegracao = cnpjAgenteIntegracao;
		this.nomeAgenteIntegracao = nomeAgenteIntegracao;
	}

}

