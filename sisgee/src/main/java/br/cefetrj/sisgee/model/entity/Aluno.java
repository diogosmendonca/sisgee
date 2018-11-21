package br.cefetrj.sisgee.model.entity;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 * Classe Aluno usada em Buscas e Registros de Termo Estágio, Aditivo e Rescisão
 * 
 * @author Paulo Cantuaria
 * @since 1.0
 *
 */
@Entity
public class Aluno {

	@Id
	@GeneratedValue
	private Integer idAluno;

	@Column(length = 100, nullable = false, unique = true)
	private String matricula;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	private Pessoa pessoa;

	@Column(length = 50)
	private String tipoAluno;            
        
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	private Curso curso;

	@OneToMany(mappedBy = "aluno", cascade = CascadeType.MERGE)
	private List<TermoEstagio> termoEstagios;

	public Aluno() {}

	public Aluno(Integer idAluno) {
		this.idAluno = idAluno;
	}

	public Integer getIdAluno() {
		return idAluno;
	}

	public void setIdAluno(Integer idAluno) {
		this.idAluno = idAluno;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public List<TermoEstagio> getTermoEstagios() {
		return termoEstagios;
	}

	public void setTermoEstagios(List<TermoEstagio> termoEstagios) {
		this.termoEstagios = termoEstagios;
	}

        public String getTipoAluno() {
            return tipoAluno;
        }

        public void setTipoAluno(String tipoAluno) {
            this.tipoAluno = tipoAluno;
        }        

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idAluno == null) ? 0 : idAluno.hashCode());
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
		Aluno other = (Aluno) obj;
		if (idAluno == null) {
			if (other.idAluno != null)
				return false;
		} else if (!idAluno.equals(other.idAluno))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return matricula;
	}

}
