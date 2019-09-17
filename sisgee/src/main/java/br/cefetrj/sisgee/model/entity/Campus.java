package br.cefetrj.sisgee.model.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Classe referente ao campus 
 * @author Paulo Cantuária
 * @since 1.0
 *
 */
@Entity
public class Campus {

	@Id
	@GeneratedValue
	private Integer idCampus;

	@Column(length = 100, nullable = false, unique = true)
	private String nomeCampus;

	@OneToMany(mappedBy = "campus")
	private List<Curso> cursos;

	public Campus() {}

	public Integer getIdCampus() {
		return idCampus;
	}

	public void setIdCampus(Integer idCampus) {
		this.idCampus = idCampus;
	}

	public String getNomeCampus() {
		return nomeCampus;
	}

	public void setNomeCampus(String nomeCampus) {
		this.nomeCampus = nomeCampus;
	}

	public List<Curso> getCursos() {
		return cursos;
	}

	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idCampus == null) ? 0 : idCampus.hashCode());
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
		Campus other = (Campus) obj;
		if (idCampus == null) {
			if (other.idCampus != null)
				return false;
		} else if (!idCampus.equals(other.idCampus))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return nomeCampus;
	}
}
