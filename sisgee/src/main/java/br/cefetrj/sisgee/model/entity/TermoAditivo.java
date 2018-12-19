package br.cefetrj.sisgee.model.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/*
 * @author Vinicius Paradellas
 * @since 1.1
 *
 */
@Entity
public class TermoAditivo {

	@Id
	@GeneratedValue
	private Integer idTermoAditivo;

	private Date dataFimTermoAditivo;
        
        private String tipoAditivo;
        
        @Column(nullable = false)
        private Date dataCadastramentoTermoAditivo;

	@Column(nullable = true)
	private Integer cargaHorariaTermoAditivo;

	@Column(nullable = true)
	private Float valorBolsaTermoAditivo;

	@Column(length = 255, nullable = true)
	private String enderecoTermoAditivo;

	@Column(length = 10, nullable = true)
	private String numeroEnderecoTermoAditivo;

	@Column(length = 150, nullable = true)
	private String complementoEnderecoTermoAditivo;

	@Column(length = 150, nullable = true)
	private String bairroEnderecoTermoAditivo;

	@Column(length = 15, nullable = true)
	private String cepEnderecoTermoAditivo;

	@Column(length = 150, nullable = true)
	private String cidadeEnderecoTermoAditivo;

	@Column(length = 2, nullable = true)
	private String estadoEnderecoTermoAditivo;
        
        @Column(nullable = true)
        private String eobrigatorio;
        
        @Column(nullable = true)
        private String nomeSupervisor;
        
        @Column(nullable = true)
        private String cargoSupervisor;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(nullable = true)
	private TermoEstagio termoEstagio;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private ProfessorOrientador professorOrientador;

    @Override
    public String toString() {
        return "TermoAditivo{" + "idTermoAditivo=" + idTermoAditivo + ", tipoAditivo=" + tipoAditivo + '}';
    }
        
        
	public TermoAditivo() {}
        
	public TermoAditivo(Date dataCadastramentoTermoAditivo,float valorBolsaTermoAditivo ){
            tipoAditivo="Valor Bolsa";
            this.dataCadastramentoTermoAditivo=dataCadastramentoTermoAditivo;
            this.valorBolsaTermoAditivo=valorBolsaTermoAditivo;
        }
        
	public TermoAditivo(Date dataFimTermoAditivo, Integer cargaHorariaTermoAditivo,
			Float valorBolsaTermoAditivo, String enderecoTermoAditivo, ProfessorOrientador professorOrientador) {
		
		this.dataFimTermoAditivo = dataFimTermoAditivo;
		this.cargaHorariaTermoAditivo = cargaHorariaTermoAditivo;
		this.valorBolsaTermoAditivo = valorBolsaTermoAditivo;
		this.enderecoTermoAditivo = enderecoTermoAditivo;
		this.professorOrientador = professorOrientador;
                
	}
	
	public ProfessorOrientador getProfessorOrientador() {
		return professorOrientador;
	}

	public void setProfessorOrientador(ProfessorOrientador professorOrientador) {
		this.professorOrientador = professorOrientador;
	}
	
	public Integer getIdTermoAditivo() {
		return idTermoAditivo;
	}

	public void setIdTermoAditivo(Integer idTermoAditivo) {
		this.idTermoAditivo = idTermoAditivo;
	}
        
        public String getDataFimTermoAditivo2(){
                SimpleDateFormat format =new SimpleDateFormat("dd/MM/yyyy");
                if(dataFimTermoAditivo!=null){
                String a=format.format(dataFimTermoAditivo);
                return a;
                }
                return null;
        }
	public Date getDataFimTermoAditivo() {
            
		return dataFimTermoAditivo;
	}

	public void setDataFimTermoAditivo(Date dataFimTermoAditivo) {
		this.dataFimTermoAditivo = dataFimTermoAditivo;
	}

	public Integer getCargaHorariaTermoAditivo() {
		return cargaHorariaTermoAditivo;
	}

	public void setCargaHorariaTermoAditivo(Integer cargaHorariaTermoAditivo) {
		this.cargaHorariaTermoAditivo = cargaHorariaTermoAditivo;
	}

	public Float getValorBolsaTermoAditivo() {
		return valorBolsaTermoAditivo;
	}

	public void setValorBolsaTermoAditivo(Float valorBolsaTermoAditivo) {
		this.valorBolsaTermoAditivo = valorBolsaTermoAditivo;
	}

	public String getEnderecoTermoAditivo() {
		return enderecoTermoAditivo;
	}

	public void setEnderecoTermoAditivo(String enderecoTermoAditivo) {
		this.enderecoTermoAditivo = enderecoTermoAditivo;
	}

	public String getNumeroEnderecoTermoAditivo() {
		return numeroEnderecoTermoAditivo;
	}

	public void setNumeroEnderecoTermoAditivo(String numeroEnderecoTermoAditivo) {
		this.numeroEnderecoTermoAditivo = numeroEnderecoTermoAditivo;
	}

	public String getComplementoEnderecoTermoAditivo() {
		return complementoEnderecoTermoAditivo;
	}

	public void setComplementoEnderecoTermoAditivo(String complementoEnderecoTermoAditivo) {
		this.complementoEnderecoTermoAditivo = complementoEnderecoTermoAditivo;
	}

	public String getBairroEnderecoTermoAditivo() {
		return bairroEnderecoTermoAditivo;
	}

	public void setBairroEnderecoTermoAditivo(String bairroEnderecoTermoAditivo) {
		this.bairroEnderecoTermoAditivo = bairroEnderecoTermoAditivo;
	}

	public String getCepEnderecoTermoAditivo() {
		return cepEnderecoTermoAditivo;
	}

	public void setCepEnderecoTermoAditivo(String cepEnderecoTermoAditivo) {
		this.cepEnderecoTermoAditivo = cepEnderecoTermoAditivo;
	}

	public String getCidadeEnderecoTermoAditivo() {
		return cidadeEnderecoTermoAditivo;
	}

	public void setCidadeEnderecoTermoAditivo(String cidadeEnderecoTermoAditivo) {
		this.cidadeEnderecoTermoAditivo = cidadeEnderecoTermoAditivo;
	}

	public String getEstadoEnderecoTermoAditivo() {
		return estadoEnderecoTermoAditivo;
	}

	public void setEstadoEnderecoTermoAditivo(String estadoEnderecoTermoAditivo) {
		this.estadoEnderecoTermoAditivo = estadoEnderecoTermoAditivo;
	}

	public TermoEstagio getTermoEstagio() {
		return termoEstagio;
	}

	public void setTermoEstagio(TermoEstagio termoEstagio) {
		this.termoEstagio = termoEstagio;
	}

        public String getTipoAditivo() {
            return tipoAditivo;
        }
        
        public String getTipoAditivoMSG() {
            switch(tipoAditivo){
                case "Vigência":
                    return "br.cefetrj.sisgee.resources.form.AditivoDeVigência";
                case "Carga Horária":
                    return "br.cefetrj.sisgee.resources.form.AditivoDeCargaHoraria";
                case "Professor Orientador":
                    return "br.cefetrj.sisgee.resources.form.AditivoDeProfOrientador";
                case "Valor da Bolsa":
                    return "br.cefetrj.sisgee.resources.form.AditivoDeValorBolsa";
                case "Local Estágio":
                    return "br.cefetrj.sisgee.resources.form.AditivoDeEndereço";
                case "Supervisor":  
                    return "br.cefetrj.sisgee.resources.form.AditivoDeSupervisor";
                default:
                    return null;
            }
        }
        
        
        public void setTipoAditivo(String tipoAditivo) {
            this.tipoAditivo = tipoAditivo;
        }
        
        public String getDataCadastramentoTermoAditivo2(){
                SimpleDateFormat format =new SimpleDateFormat("dd/MM/yyyy");
                if(dataCadastramentoTermoAditivo!=null){
                String a=format.format(dataCadastramentoTermoAditivo);
                return a;
                }
                return null;
        }
        public Date getDataCadastramentoTermoAditivo() {
            return dataCadastramentoTermoAditivo;
        }

        public void setDataCadastramentoTermoAditivo(Date dataCadastramentoTermoAditivo) {
            this.dataCadastramentoTermoAditivo = dataCadastramentoTermoAditivo;
        }

        public String getEobrigatorio() {
            return eobrigatorio;
        }

        public void setEobrigatorio(String eobrigatorio) {
            this.eobrigatorio = eobrigatorio;
        }

        public String getNomeSupervisor() {
            return nomeSupervisor;
        }

        public void setNomeSupervisor(String nomeSupervisor) {
            this.nomeSupervisor = nomeSupervisor;
        }

        public String getCargoSupervisor() {
            return cargoSupervisor;
        }

        public void setCargoSupervisor(String cargoSupervisor) {
            this.cargoSupervisor = cargoSupervisor;
        }
        
        @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idTermoAditivo == null) ? 0 : idTermoAditivo.hashCode());
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
		TermoAditivo other = (TermoAditivo) obj;
		if (idTermoAditivo == null) {
			if (other.idTermoAditivo != null)
				return false;
		} else if (!idTermoAditivo.equals(other.idTermoAditivo))
			return false;
		return true;
	}

}