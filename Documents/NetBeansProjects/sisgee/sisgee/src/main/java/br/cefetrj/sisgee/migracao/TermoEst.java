/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetrj.sisgee.migracao;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author c18634659798
 */
@Entity
public class TermoEst implements Serializable {
    private static long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue
    private Long idTermoEstagio;
    
    @ManyToOne()
    private Estudante aluno;
    @ManyToOne(cascade = {CascadeType.ALL})
    private Conveniado c;
    
    private int cargaHoraria;
    private float valorBolsa;
    private String endereco;
    private String complemento;
    private String bairro;
    private String cep;
    private String cidade;
    private String estado;
    private String Convenio;
    private String nomeEmpresa;
    private String termoAditivo;
    private String cartaRecisao;
    private String telefone;
    
    @Temporal(TemporalType.DATE)
    private Date dataInicio = new Date();
    
    @Temporal(TemporalType.DATE)
    private Date dataFim = new Date();
    
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
     * @return the idTermoEstagio
     */
    public Long getIdTermoEstagio() {
        return idTermoEstagio;
    }

    /**
     * @param idTermoEstagio the idTermoEstagio to set
     */
    public void setIdTermoEstagio(Long idTermoEstagio) {
        this.idTermoEstagio = idTermoEstagio;
    }

    /**
     * @return the cargaHoraria
     */
    public int getCargaHoraria() {
        return cargaHoraria;
    }

    /**
     * @param cargaHoraria the cargaHoraria to set
     */
    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    /**
     * @return the valorBolsa
     */
    public float getValorBolsa() {
        return valorBolsa;
    }

    /**
     * @param valorBolsa the valorBolsa to set
     */
    public void setValorBolsa(float valorBolsa) {
        this.valorBolsa = valorBolsa;
    }

    /**
     * @return the endereco
     */
    public String getEndereco() {
        return endereco;
    }

    /**
     * @param endereco the endereco to set
     */
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    /**
     * @return the complemento
     */
    public String getComplemento() {
        return complemento;
    }

    /**
     * @param complemento the complemento to set
     */
    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    /**
     * @return the bairro
     */
    public String getBairro() {
        return bairro;
    }

    /**
     * @param bairro the bairro to set
     */
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    /**
     * @return the cep
     */
    public String getCep() {
        return cep;
    }

    /**
     * @param cep the cep to set
     */
    public void setCep(String cep) {
        this.cep = cep;
    }

    /**
     * @return the cidade
     */
    public String getCidade() {
        return cidade;
    }

    /**
     * @param cidade the cidade to set
     */
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * @return the Convenio
     */
    public String getConvenio() {
        return Convenio;
    }

    /**
     * @param Convenio the numConvenio to set
     */
    public void setConvenio(String Convenio) {
        this.Convenio = Convenio;
    }

    /**
     * @return the nomeEmpresa
     */
    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    /**
     * @param nomeEmpresa the nomeEmpresa to set
     */
    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

    /**
     * @return the dataInicio
     */
    public Date getDataInicio() {
        return dataInicio;
    }

    /**
     * @param dataInicio the dataInicio to set
     */
    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    /**
     * @return the dataFim
     */
    public Date getDataFim() {
        return dataFim;
    }

    /**
     * @param dataFim the dataFim to set
     */
    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    /**
     * @return the termoAditivo
     */
    public String getTermoAditivo() {
        return termoAditivo;
    }

    /**
     * @param termoAditivo the termoAditivo to set
     */
    public void setTermoAditivo(String termoAditivo) {
        this.termoAditivo = termoAditivo;
    }

    /**
     * @return the cartaRecisao
     */
    public String getCartaRecisao() {
        return cartaRecisao;
    }

    /**
     * @param cartaRecisao the cartaRecisao to set
     */
    public void setCartaRecisao(String cartaRecisao) {
        this.cartaRecisao = cartaRecisao;
    }

    /**
     * @return the telefone
     */
    public String getTelefone() {
        return telefone;
    }

    /**
     * @param telefone the telefone to set
     */
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    /**
     * @return the aluno
     */
    public Estudante getAluno() {
        return aluno;
    }

    /**
     * @param aluno the aluno to set
     */
    public void setAluno(Estudante aluno) {
        this.aluno = aluno;
    }

    /**
     * @return the c
     */
    public Conveniado getC() {
        return c;
    }

    /**
     * @param c the c to set
     */
    public void setC(Conveniado c) {
        this.c = c;
    }

}
