/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetrj.sisgee.migracao;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author c18634659798
 */
@Entity
public class TermoAditivoAux {
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
	private TermoEst termoEstagio;

    /**
     * @return the idTermoAditivo
     */
    public Integer getIdTermoAditivo() {
        return idTermoAditivo;
    }

    /**
     * @param idTermoAditivo the idTermoAditivo to set
     */
    public void setIdTermoAditivo(Integer idTermoAditivo) {
        this.idTermoAditivo = idTermoAditivo;
    }

    /**
     * @return the dataFimTermoAditivo
     */
    public Date getDataFimTermoAditivo() {
        return dataFimTermoAditivo;
    }

    /**
     * @param dataFimTermoAditivo the dataFimTermoAditivo to set
     */
    public void setDataFimTermoAditivo(Date dataFimTermoAditivo) {
        this.dataFimTermoAditivo = dataFimTermoAditivo;
    }

    /**
     * @return the tipoAditivo
     */
    public String getTipoAditivo() {
        return tipoAditivo;
    }

    /**
     * @param tipoAditivo the tipoAditivo to set
     */
    public void setTipoAditivo(String tipoAditivo) {
        this.tipoAditivo = tipoAditivo;
    }

    /**
     * @return the dataCadastramentoTermoAditivo
     */
    public Date getDataCadastramentoTermoAditivo() {
        return dataCadastramentoTermoAditivo;
    }

    /**
     * @param dataCadastramentoTermoAditivo the dataCadastramentoTermoAditivo to set
     */
    public void setDataCadastramentoTermoAditivo(Date dataCadastramentoTermoAditivo) {
        this.dataCadastramentoTermoAditivo = dataCadastramentoTermoAditivo;
    }

    /**
     * @return the cargaHorariaTermoAditivo
     */
    public Integer getCargaHorariaTermoAditivo() {
        return cargaHorariaTermoAditivo;
    }

    /**
     * @param cargaHorariaTermoAditivo the cargaHorariaTermoAditivo to set
     */
    public void setCargaHorariaTermoAditivo(Integer cargaHorariaTermoAditivo) {
        this.cargaHorariaTermoAditivo = cargaHorariaTermoAditivo;
    }

    /**
     * @return the valorBolsaTermoAditivo
     */
    public Float getValorBolsaTermoAditivo() {
        return valorBolsaTermoAditivo;
    }

    /**
     * @param valorBolsaTermoAditivo the valorBolsaTermoAditivo to set
     */
    public void setValorBolsaTermoAditivo(Float valorBolsaTermoAditivo) {
        this.valorBolsaTermoAditivo = valorBolsaTermoAditivo;
    }

    /**
     * @return the enderecoTermoAditivo
     */
    public String getEnderecoTermoAditivo() {
        return enderecoTermoAditivo;
    }

    /**
     * @param enderecoTermoAditivo the enderecoTermoAditivo to set
     */
    public void setEnderecoTermoAditivo(String enderecoTermoAditivo) {
        this.enderecoTermoAditivo = enderecoTermoAditivo;
    }

    /**
     * @return the numeroEnderecoTermoAditivo
     */
    public String getNumeroEnderecoTermoAditivo() {
        return numeroEnderecoTermoAditivo;
    }

    /**
     * @param numeroEnderecoTermoAditivo the numeroEnderecoTermoAditivo to set
     */
    public void setNumeroEnderecoTermoAditivo(String numeroEnderecoTermoAditivo) {
        this.numeroEnderecoTermoAditivo = numeroEnderecoTermoAditivo;
    }

    /**
     * @return the complementoEnderecoTermoAditivo
     */
    public String getComplementoEnderecoTermoAditivo() {
        return complementoEnderecoTermoAditivo;
    }

    /**
     * @param complementoEnderecoTermoAditivo the complementoEnderecoTermoAditivo to set
     */
    public void setComplementoEnderecoTermoAditivo(String complementoEnderecoTermoAditivo) {
        this.complementoEnderecoTermoAditivo = complementoEnderecoTermoAditivo;
    }

    /**
     * @return the bairroEnderecoTermoAditivo
     */
    public String getBairroEnderecoTermoAditivo() {
        return bairroEnderecoTermoAditivo;
    }

    /**
     * @param bairroEnderecoTermoAditivo the bairroEnderecoTermoAditivo to set
     */
    public void setBairroEnderecoTermoAditivo(String bairroEnderecoTermoAditivo) {
        this.bairroEnderecoTermoAditivo = bairroEnderecoTermoAditivo;
    }

    /**
     * @return the cepEnderecoTermoAditivo
     */
    public String getCepEnderecoTermoAditivo() {
        return cepEnderecoTermoAditivo;
    }

    /**
     * @param cepEnderecoTermoAditivo the cepEnderecoTermoAditivo to set
     */
    public void setCepEnderecoTermoAditivo(String cepEnderecoTermoAditivo) {
        this.cepEnderecoTermoAditivo = cepEnderecoTermoAditivo;
    }

    /**
     * @return the cidadeEnderecoTermoAditivo
     */
    public String getCidadeEnderecoTermoAditivo() {
        return cidadeEnderecoTermoAditivo;
    }

    /**
     * @param cidadeEnderecoTermoAditivo the cidadeEnderecoTermoAditivo to set
     */
    public void setCidadeEnderecoTermoAditivo(String cidadeEnderecoTermoAditivo) {
        this.cidadeEnderecoTermoAditivo = cidadeEnderecoTermoAditivo;
    }

    /**
     * @return the estadoEnderecoTermoAditivo
     */
    public String getEstadoEnderecoTermoAditivo() {
        return estadoEnderecoTermoAditivo;
    }

    /**
     * @param estadoEnderecoTermoAditivo the estadoEnderecoTermoAditivo to set
     */
    public void setEstadoEnderecoTermoAditivo(String estadoEnderecoTermoAditivo) {
        this.estadoEnderecoTermoAditivo = estadoEnderecoTermoAditivo;
    }

    /**
     * @return the eobrigatorio
     */
    public String getEobrigatorio() {
        return eobrigatorio;
    }

    /**
     * @param eobrigatorio the eobrigatorio to set
     */
    public void setEobrigatorio(String eobrigatorio) {
        this.eobrigatorio = eobrigatorio;
    }

    /**
     * @return the nomeSupervisor
     */
    public String getNomeSupervisor() {
        return nomeSupervisor;
    }

    /**
     * @param nomeSupervisor the nomeSupervisor to set
     */
    public void setNomeSupervisor(String nomeSupervisor) {
        this.nomeSupervisor = nomeSupervisor;
    }

    /**
     * @return the cargoSupervisor
     */
    public String getCargoSupervisor() {
        return cargoSupervisor;
    }

    /**
     * @param cargoSupervisor the cargoSupervisor to set
     */
    public void setCargoSupervisor(String cargoSupervisor) {
        this.cargoSupervisor = cargoSupervisor;
    }

    /**
     * @return the termoEstagio
     */
    public TermoEst getTermoEstagio() {
        return termoEstagio;
    }

    /**
     * @param termoEstagio the termoEstagio to set
     */
    public void setTermoEstagio(TermoEst termoEstagio) {
        this.termoEstagio = termoEstagio;
    }
        
}
