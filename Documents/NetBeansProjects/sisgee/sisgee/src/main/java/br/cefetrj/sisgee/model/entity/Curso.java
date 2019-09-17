package br.cefetrj.sisgee.model.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * Classe Curso para registrar junto do Cadastro do Aluno e exibir em tela após Buscar Aluno
 * 
 * @author Paulo Cantuária
 * @since 1.0
 */
@Entity
public class Curso {
	
	@Id
	@GeneratedValue
	private Integer idCurso;
	
	@Column(length=50, nullable=false)
	private String codigoCurso;

	@Column(length=255, nullable=false)
	private String nomeCurso;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Campus campus;
		
	@OneToMany(mappedBy="curso")
	private List<Aluno> alunos;

	public Curso() {}	

	public Integer getIdCurso() {
		return idCurso;
	}

	public void setIdCurso(Integer idCurso) {
		this.idCurso = idCurso;
	}

	public String getCodigoCurso() {
		return codigoCurso;
	}

	public void setCodigoCurso(String codigoCurso) {
		this.codigoCurso = codigoCurso;
	}

	public String getNomeCurso() {
		return nomeCurso;
	}

	public void setNomeCurso(String nomeCurso) {
		this.nomeCurso = nomeCurso;
	}

	public Campus getCampus() {
		return campus;
	}

	public void setCampus(Campus campus) {
		this.campus = campus;
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idCurso == null) ? 0 : idCurso.hashCode());
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
		Curso other = (Curso) obj;
		if (idCurso == null) {
			if (other.idCurso != null)
				return false;
		} else if (!idCurso.equals(other.idCurso))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return nomeCurso;
	}
}
