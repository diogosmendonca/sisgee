package br.cefetrj.sisgee.model.entity;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * Classe Termo Estágio para registro de informações sobre o Estágio firmado
 * 
 * @author padu
 * @since 1.0
 * 
 * Inclusão de métodos e variáveis para modificar o uso do Termo Estágio e oque é incluído nele
 * @author Claudio
 * @version 2.0
 */
@Entity
public class TermoEstagio {

	@Id
	@GeneratedValue
	private Integer idTermoEstagio;
        
        private String estado;

	@Column(nullable = false)
	private Date dataInicioTermoEstagio;

	private Date dataFimTermoEstagio;

	private Date dataRescisaoTermoEstagio;
		
	@Column(nullable = false)
	private Integer cargaHorariaTermoEstagio;

	@Column(nullable = false)
	private Float valorBolsa;

	@Column(length = 255, nullable = false)
	private String enderecoTermoEstagio;

	@Column(length = 10, nullable = false)
	private String numeroEnderecoTermoEstagio;

	@Column(length = 150, nullable = false)
	private String complementoEnderecoTermoEstagio;

	@Column(length = 150, nullable = false)
	private String bairroEnderecoTermoEstagio;

	@Column(length = 15, nullable = false)
	private String cepEnderecoTermoEstagio;

	@Column(length = 150, nullable = false)
	private String cidadeEnderecoTermoEstagio;

	@Column(length = 2, nullable = false)
	private String estadoEnderecoTermoEstagio;

	@Column(nullable = false)
	private Boolean eEstagioObrigatorio;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(nullable = false)
	private Aluno aluno;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(nullable = false)
	private Convenio convenio;
        
	@Column(length = 80)
	private String nomeSupervisor;    
        
	@Column(length = 80)
	private String cargoSupervisor;   
        
	@Column()
	private String nomeAgenciada;         

	@ManyToOne(fetch = FetchType.EAGER)
	private ProfessorOrientador professorOrientador;

	@OneToMany(mappedBy = "termoEstagio")
	private List<TermoAditivo> termosAditivos;

	public TermoEstagio() {}
	
	
	
	public TermoEstagio(Date dataInicioTermoEstagio, Date dataFimTermoEstagio, Integer cargaHorariaTermoEstagio,
			Float valorBolsa, String enderecoTermoEstagio, String numeroEnderecoTermoEstagio,
			String complementoEnderecoTermoEstagio, String bairroEnderecoTermoEstagio, String cepEnderecoTermoEstagio,
			String cidadeEnderecoTermoEstagio, String estadoEnderecoTermoEstagio, Boolean eEstagioObrigatorio,
			Aluno aluno, Convenio convenio, ProfessorOrientador professorOrientador,
                        String nomeSupervisor, String cargoSupervisor, String nomeAgenciada) {
		this.estado="ativo";
		this.dataInicioTermoEstagio = dataInicioTermoEstagio;
		this.dataFimTermoEstagio = dataFimTermoEstagio;
		this.cargaHorariaTermoEstagio = cargaHorariaTermoEstagio;
		this.valorBolsa = valorBolsa;
		this.enderecoTermoEstagio = enderecoTermoEstagio;
		this.numeroEnderecoTermoEstagio = numeroEnderecoTermoEstagio;
		this.complementoEnderecoTermoEstagio = complementoEnderecoTermoEstagio;
		this.bairroEnderecoTermoEstagio = bairroEnderecoTermoEstagio;
		this.cepEnderecoTermoEstagio = cepEnderecoTermoEstagio;
		this.cidadeEnderecoTermoEstagio = cidadeEnderecoTermoEstagio;
		this.estadoEnderecoTermoEstagio = estadoEnderecoTermoEstagio;
		this.eEstagioObrigatorio = eEstagioObrigatorio;
		this.aluno = aluno;
		this.convenio = convenio;
		this.professorOrientador = professorOrientador;
                this.nomeSupervisor = nomeSupervisor;
                this.cargoSupervisor = cargoSupervisor;
                this.nomeAgenciada = nomeAgenciada;
	}


        public String getEstado() throws Exception{
            //final DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
            Calendar cal = Calendar.getInstance();
            Date a= getDataFimTermoEstagio(), b=getDataRescisaoTermoEstagio();
            
            if(a!=null && a.compareTo(cal.getTime()) >0){
                if(b==null){
                return "Ativo";
                }  
                if((b.compareTo(cal.getTime()) >0)){
                    return "Ativo";
                }
            }
            return"Encerrado" ;
        }
	public Integer getIdTermoEstagio() {
		return idTermoEstagio;
	}

	public void setIdTermoEstagio(Integer idTermoEstagio) {
		this.idTermoEstagio = idTermoEstagio;
	}

        public String getDataInicioTermoEstagio2(){
                SimpleDateFormat format =new SimpleDateFormat("dd/MM/yyyy");
                if(dataInicioTermoEstagio!=null){
                String a=format.format(dataInicioTermoEstagio);
                return a;
                }
                return null;}
        
	public Date getDataInicioTermoEstagio() {
		return dataInicioTermoEstagio;
	}

	public void setDataInicioTermoEstagio(Date dataInicioTermoEstagio) {
		this.dataInicioTermoEstagio = dataInicioTermoEstagio;
	}
        
        public String getDataFimTermoEstagio3()throws ParseException  {
            SimpleDateFormat format =new SimpleDateFormat("dd/MM/yyyy");
            String a=format.format(dataFimTermoEstagio);
            return a;
	}
        public String getDataFimTermoEstagio2(){
            boolean pegou=false;
            String a="";
            for (TermoAditivo termosAditivo : termosAditivos) {
                if(termosAditivo.getDataFimTermoAditivo()!=null){
                    SimpleDateFormat format =new SimpleDateFormat("dd/MM/yyyy");
                    a=format.format(termosAditivo.getDataFimTermoAditivo());
                }
            }
            if(pegou){
                return a;
            }
            SimpleDateFormat format =new SimpleDateFormat("dd/MM/yyyy");
            a=format.format(dataFimTermoEstagio);
            return a;
        }
        public String getDataFimTermoEstagioVisu(TermoAditivo ad){
            boolean pegou=false;
            String a="";
            for (TermoAditivo termosAditivo : termosAditivos) {
                if(termosAditivo.getDataFimTermoAditivo()!=null){
                    SimpleDateFormat format =new SimpleDateFormat("dd/MM/yyyy");
                    a=format.format(termosAditivo.getDataFimTermoAditivo());
                }
                if(ad!=null && ad.equals(termosAditivo)){
                    pegou = true;
                    break;
                }
            }
            if(pegou){
                return a;
            }
            SimpleDateFormat format =new SimpleDateFormat("dd/MM/yyyy");
            a=format.format(dataFimTermoEstagio);
            return a;
        }
        
	public Date getDataFimTermoEstagio(){
            Date a=null;
            boolean pegou=false;
            if(!termosAditivos.isEmpty())
                for (TermoAditivo termosAditivo : termosAditivos) {
                    if(termosAditivo.getDataFimTermoAditivo()!=null){
                        a=termosAditivo.getDataFimTermoAditivo();
                        pegou=true;
                    }
                }
            if(pegou)
                return a;
            else
                return dataFimTermoEstagio;
	}

	public void setDataFimTermoEstagio(Date dataFimTermoEstagio) {
		this.dataFimTermoEstagio = dataFimTermoEstagio;
	}

	public Date getDataRescisaoTermoEstagio() {
		return dataRescisaoTermoEstagio;
	}

	public void setDataRescisaoTermoEstagio(Date dataRescisaoTermoEstagio) {
		this.dataRescisaoTermoEstagio = dataRescisaoTermoEstagio;
	}

	public Integer getCargaHorariaTermoEstagio() {
            Integer a=-1;
            for (TermoAditivo termosAditivo : termosAditivos) {
                if(cargaHorariaTermoEstagio!=null){
                    a=termosAditivo.getCargaHorariaTermoAditivo();
                }
            }
            if(a!=null && a!=-1)
                return a;
            else
		return cargaHorariaTermoEstagio;
	}
        
        public Integer getCargaHorariaTermoEstagioVisu(TermoAditivo ad) {
            Integer a=-1;
            for (TermoAditivo termosAditivo : termosAditivos) {
                if(cargaHorariaTermoEstagio!=null){
                    a=termosAditivo.getCargaHorariaTermoAditivo();
                }
                if(ad==null){
                    return cargaHorariaTermoEstagio;
                }
                if(ad!=null && ad.equals(termosAditivo)){
                    break;
                }
            }
            if(a!=null && a!=-1)
                return a;
            else
		return cargaHorariaTermoEstagio;
	}

	public void setCargaHorariaTermoEstagio(Integer cargaHorariaTermoEstagio) {
		this.cargaHorariaTermoEstagio = cargaHorariaTermoEstagio;
	}

	public Float getValorBolsaVisu(TermoAditivo ad) {
            float a=-1;
            boolean valid=false;
            for (TermoAditivo termosAditivo : termosAditivos) {
                
                
                if(termosAditivo.getValorBolsaTermoAditivo()!=null){
                    a=termosAditivo.getValorBolsaTermoAditivo();
                    valid=true;
                }
                if(ad==null){
                    return valorBolsa;
                }
                if(ad!=null && ad.equals(termosAditivo)){
                    break;
                }
            }
            if(valid)
                return a;
            else	
                return valorBolsa;
	}
        
        public Float getValorBolsa() {
            float a=-1;
            boolean valid=false;
            for (TermoAditivo termosAditivo : termosAditivos) {
                if(termosAditivo.getValorBolsaTermoAditivo()!=null){
                    a=termosAditivo.getValorBolsaTermoAditivo();
                    valid=true;
                }
            }
            if(valid)
                return a;
            else	
                return valorBolsa;
	}

	public void setValorBolsa(Float valorBolsa) {
		this.valorBolsa = valorBolsa;
	}

	public String getEnderecoTermoEstagio() {
            String a=null;
            boolean valid=false;
            for (TermoAditivo termosAditivo : termosAditivos) {
                if(termosAditivo.getEnderecoTermoAditivo()!=null){
                    a=termosAditivo.getEnderecoTermoAditivo();
                    valid=true;
                }
            }
            if(valid)
                return a;
            else
            return enderecoTermoEstagio;
	}
        public String getEnderecoTermoEstagioVisu(TermoAditivo ad) {
            String a=null;
            boolean valid=false;
            for (TermoAditivo termosAditivo : termosAditivos) {
                if(termosAditivo.getEnderecoTermoAditivo()!=null){
                    a=termosAditivo.getEnderecoTermoAditivo();
                    valid=true;
                }
                if(ad==null){
                    return enderecoTermoEstagio;
                }
                if(ad!=null && ad.equals(termosAditivo)){
                    break;
                }
            }
            if(valid)
                return a;
            else
            return enderecoTermoEstagio;
	}

	public void setEnderecoTermoEstagio(String enderecoTermoEstagio) {
		this.enderecoTermoEstagio = enderecoTermoEstagio;
	}

	public String getNumeroEnderecoTermoEstagio() {
		
            String a=null;
            boolean valid=false;
            for (TermoAditivo termosAditivo : termosAditivos) {
                if(termosAditivo.getNumeroEnderecoTermoAditivo()!=null){
                    a=termosAditivo.getNumeroEnderecoTermoAditivo();
                    valid=true;
                }
                
            }
            if(valid)
                return a;
            else
            return numeroEnderecoTermoEstagio;
	}
        
        public String getNumeroEnderecoTermoEstagioVisu(TermoAditivo ad) {
		
            String a=null;
            boolean valid=false;
            for (TermoAditivo termosAditivo : termosAditivos) {
                if(termosAditivo.getNumeroEnderecoTermoAditivo()!=null){
                    a=termosAditivo.getNumeroEnderecoTermoAditivo();
                    valid=true;
                }
                if(ad==null){
                    return numeroEnderecoTermoEstagio;
                }
                if(ad!=null && ad.equals(termosAditivo)){
                    break;
                }
            }
            if(valid)
                return a;
            else
            return numeroEnderecoTermoEstagio;
	}

	public void setNumeroEnderecoTermoEstagio(String numeroEnderecoTermoEstagio) {
		this.numeroEnderecoTermoEstagio = numeroEnderecoTermoEstagio;
	}

	public String getComplementoEnderecoTermoEstagio() {
		
            String a=null;
            boolean valid=false;
            for (TermoAditivo termosAditivo : termosAditivos) {
                if(termosAditivo.getComplementoEnderecoTermoAditivo()!=null){
                    a=termosAditivo.getComplementoEnderecoTermoAditivo();
                    valid=true;
                }
            }
            if(valid)
                return a;
            else
            return complementoEnderecoTermoEstagio;
	}
        
        public String getComplementoEnderecoTermoEstagioVisu(TermoAditivo ad) {
		
            String a=null;
            boolean valid=false;
            for (TermoAditivo termosAditivo : termosAditivos) {
                if(termosAditivo.getComplementoEnderecoTermoAditivo()!=null){
                    a=termosAditivo.getComplementoEnderecoTermoAditivo();
                    valid=true;
                }
                if(ad==null){
                    return complementoEnderecoTermoEstagio;
                }
                if(ad!=null && ad.equals(termosAditivo)){
                    break;
                }
            }
            if(valid)
                return a;
            else
            return complementoEnderecoTermoEstagio;
	}

	public void setComplementoEnderecoTermoEstagio(String complementoEnderecoTermoEstagio) {
		this.complementoEnderecoTermoEstagio = complementoEnderecoTermoEstagio;
	}

	public String getBairroEnderecoTermoEstagio() {
		
            String a=null;
            boolean valid=false;
            for (TermoAditivo termosAditivo : termosAditivos) {
                if(termosAditivo.getBairroEnderecoTermoAditivo()!=null){
                    a=termosAditivo.getBairroEnderecoTermoAditivo();
                    valid=true;
                }
            }
            if(valid)
                return a;
            else
            return bairroEnderecoTermoEstagio;
	}
        
        public String getBairroEnderecoTermoEstagioVisu(TermoAditivo ad) {
		
            String a=null;
            boolean valid=false;
            for (TermoAditivo termosAditivo : termosAditivos) {
                if(termosAditivo.getBairroEnderecoTermoAditivo()!=null){
                    a=termosAditivo.getBairroEnderecoTermoAditivo();
                    valid=true;
                }
                if(ad==null){
                    return bairroEnderecoTermoEstagio;
                }
                if(ad!=null && ad.equals(termosAditivo)){
                    break;
                }
            }
            if(valid)
                return a;
            else
            return bairroEnderecoTermoEstagio;
	}

	public void setBairroEnderecoTermoEstagio(String bairroEnderecoTermoEstagio) {
		this.bairroEnderecoTermoEstagio = bairroEnderecoTermoEstagio;
	}

	public String getCepEnderecoTermoEstagio() {
	
            String a=null;
            boolean valid=false;
            for (TermoAditivo termosAditivo : termosAditivos) {
                if(termosAditivo.getCepEnderecoTermoAditivo()!=null){
                    a=termosAditivo.getCepEnderecoTermoAditivo();
                    valid=true;
                }
            }
            if(valid)
                return a;
            else
            return cepEnderecoTermoEstagio;
	}
        
        public String getCepEnderecoTermoEstagioVisu(TermoAditivo ad) {
	
            String a=null;
            boolean valid=false;
            for (TermoAditivo termosAditivo : termosAditivos) {
                if(termosAditivo.getCepEnderecoTermoAditivo()!=null){
                    a=termosAditivo.getCepEnderecoTermoAditivo();
                    valid=true;
                }
                if(ad==null){
                    return cepEnderecoTermoEstagio;
                }
                if(ad!=null && ad.equals(termosAditivo)){
                    break;
                }
            }
            if(valid)
                return a;
            else
            return cepEnderecoTermoEstagio;
	}

	public void setCepEnderecoTermoEstagio(String cepEnderecoTermoEstagio) {
		this.cepEnderecoTermoEstagio = cepEnderecoTermoEstagio;
	}

	public String getCidadeEnderecoTermoEstagio() {
		
            String a=null;
            boolean valid=false;
            for (TermoAditivo termosAditivo : termosAditivos) {
                if(termosAditivo.getCidadeEnderecoTermoAditivo()!=null){
                    a=termosAditivo.getCidadeEnderecoTermoAditivo();
                    valid=true;
                }
            }
            if(valid)
                return a;
            else
            return cidadeEnderecoTermoEstagio;
	}
        public String getCidadeEnderecoTermoEstagioVisu(TermoAditivo ad) {
		
            String a=null;
            boolean valid=false;
            for (TermoAditivo termosAditivo : termosAditivos) {
                if(termosAditivo.getCidadeEnderecoTermoAditivo()!=null){
                    a=termosAditivo.getCidadeEnderecoTermoAditivo();
                    valid=true;
                }
                if(ad==null){
                    return cidadeEnderecoTermoEstagio;
                }
                if(ad!=null && ad.equals(termosAditivo)){
                    break;
                }
            }
            if(valid)
                return a;
            else
            return cidadeEnderecoTermoEstagio;
	}

	public void setCidadeEnderecoTermoEstagio(String cidadeEnderecoTermoEstagio) {
		this.cidadeEnderecoTermoEstagio = cidadeEnderecoTermoEstagio;
	}

	public String getEstadoEnderecoTermoEstagio() {
		
            String a=null;
            boolean valid=false;
            for (TermoAditivo termosAditivo : termosAditivos) {
                if(termosAditivo.getEstadoEnderecoTermoAditivo()!=null){
                    a=termosAditivo.getEstadoEnderecoTermoAditivo();
                    valid=true;
                }
            }
            if(valid)
                return a;
            else
            return estadoEnderecoTermoEstagio;
	}
        
        public String getEstadoEnderecoTermoEstagioVisu(TermoAditivo ad) {
		
            String a=null;
            boolean valid=false;
            for (TermoAditivo termosAditivo : termosAditivos) {
                if(termosAditivo.getEstadoEnderecoTermoAditivo()!=null){
                    a=termosAditivo.getEstadoEnderecoTermoAditivo();
                    valid=true;
                }
                if(ad==null){
                    return estadoEnderecoTermoEstagio;
                }
                if(ad!=null && ad.equals(termosAditivo)){
                    break;
                }
            }
            if(valid)
                return a;
            else
            return estadoEnderecoTermoEstagio;
	}


	public void setEstadoEnderecoTermoEstagio(String estadoEnderecoTermoEstagio) {
		this.estadoEnderecoTermoEstagio = estadoEnderecoTermoEstagio;
	}	

	public Boolean getEEstagioObrigatorio() {
		
            String a="";
            boolean valid=false;
            for (TermoAditivo termosAditivo : termosAditivos) {
                if(termosAditivo.getEobrigatorio()!=null){
                    a=termosAditivo.getEobrigatorio();
                    valid=true;
                }
            }
            if(valid)
                if(a.equals("sim")){
                return true;
                }else{
                return false;}
            return eEstagioObrigatorio;
	}

	public void setEEstagioObrigatorio(Boolean eEstagioObrigatorio) {
		this.eEstagioObrigatorio = eEstagioObrigatorio;
	}

	public Aluno getAluno() {
		return aluno;
	}

        public String getNomeSupervisor() {
            
            String a=null;
            boolean valid=false;
            for (TermoAditivo termosAditivo : termosAditivos) {
                if(termosAditivo.getNomeSupervisor()!=null){
                    a=termosAditivo.getNomeSupervisor();
                    valid=true;
                }
                
            }
            if(valid)
                return a;
            else
            return nomeSupervisor;
        }
        
        public String getNomeSupervisorVisu(TermoAditivo ad) {
            
            String a=null;
            boolean valid=false;
            for (TermoAditivo termosAditivo : termosAditivos) {
                if(termosAditivo.getNomeSupervisor()!=null){
                    a=termosAditivo.getNomeSupervisor();
                    valid=true;
                }
                if(ad==null){
                    return nomeSupervisor;
                }
                if(ad!=null && ad.equals(termosAditivo)){
                    break;
                }
            }
            if(valid)
                return a;
            else
            return nomeSupervisor;
        }

        public void setNomeSupervisor(String nomeSupervisor) {
            this.nomeSupervisor = nomeSupervisor;
        }

        public String getCargoSupervisor() {
            
            String a=null;
            boolean valid=false;
            for (TermoAditivo termosAditivo : termosAditivos) {
                if(termosAditivo.getCargoSupervisor()!=null){
                    a=termosAditivo.getCargoSupervisor();
                    valid=true;
                }
            }
            if(valid)
                return a;
            else
            return cargoSupervisor;
        }
        
        public String getCargoSupervisorVisu(TermoAditivo ad) {
            
            String a=null;
            boolean valid=false;
            for (TermoAditivo termosAditivo : termosAditivos) {
                if(termosAditivo.getCargoSupervisor()!=null){
                    a=termosAditivo.getCargoSupervisor();
                    valid=true;
                }
                if(ad==null){
                    return cargoSupervisor;
                }
                if(ad!=null && ad.equals(termosAditivo)){
                    break;
                }
            }
            if(valid)
                return a;
            else
            return cargoSupervisor;
        }

        public void setCargoSupervisor(String cargoSupervisor) {
            this.cargoSupervisor = cargoSupervisor;
        }        
        
	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Convenio getConvenio() {
		return convenio;
	}

	public void setConvenio(Convenio convenio) {
		this.convenio = convenio;
	}

	public ProfessorOrientador getProfessorOrientador() {
		
            ProfessorOrientador a=null;
            boolean valid=false;
            for (TermoAditivo termosAditivo : termosAditivos) {
                if(termosAditivo.getProfessorOrientador()!=null){
                    a=termosAditivo.getProfessorOrientador();
                    valid=true;
                }
            }
            if(valid)
                return a;
            else
            return professorOrientador;
	}
        public ProfessorOrientador getProfessorOrientadorVisu(TermoAditivo ad) {
		
            ProfessorOrientador a=null;
            boolean valid=false;
            for (TermoAditivo termosAditivo : termosAditivos) {
                if(termosAditivo.getProfessorOrientador()!=null){
                    a=termosAditivo.getProfessorOrientador();
                    valid=true;
                }
                if(ad==null){
                    return professorOrientador;
                }
                if(ad!=null && ad.equals(termosAditivo)){
                    break;
                }
            }
            if(valid)
                return a;
            else
            return professorOrientador;
	}

	public void setProfessorOrientador(ProfessorOrientador professorOrientador) {
		this.professorOrientador = professorOrientador;
	}

	public List<TermoAditivo> getTermosAditivos() {
		return termosAditivos;
	}

	public void setTermosAditivos(List<TermoAditivo> termosAditivos) {
		this.termosAditivos = termosAditivos;
	}

        public String getNomeAgenciada() {
            return nomeAgenciada;
        }

        public void setNomeAgenciada(String nomeAgenciada) {
            this.nomeAgenciada = nomeAgenciada;
        }        
        
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idTermoEstagio == null) ? 0 : idTermoEstagio.hashCode());
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
		TermoEstagio other = (TermoEstagio) obj;
		if (idTermoEstagio == null) {
			if (other.idTermoEstagio != null)
				return false;
		} else if (!idTermoEstagio.equals(other.idTermoEstagio))
			return false;
		return true;
	}
    @Override
    public String toString() {
        return "TermoEstagio{" + "idTermoEstagio=" + idTermoEstagio + ", valorBolsa=" + valorBolsa + ", aluno=" + aluno + '}';
    }
}