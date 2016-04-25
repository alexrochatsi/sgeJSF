/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author alexrochatsi
 */

@Entity
@Table(name = "estagiario")
public class Estagiario implements Serializable {
    @Id
    @SequenceGenerator(name = "estagiario_id_gen",
            sequenceName = "estagiario_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "estagiario_id_gen")
    @Column(name="estagiario_id")
    private Integer id;
    @Column(nullable = false)
    private Boolean possuiCNH;
    @Column(name = "categoria_cnh", length = 2, nullable = true)
    private String categoriaCNH;
    @Column(name = "cnh", length = 30, nullable = true)
    private String cnh;
    @Column(name = "data_emissao_cnh", nullable = true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataEmissaoCNH;
    @Column(name = "data_vencimento_cnh", nullable = true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataVencimentoCNH;
    
    @OneToOne(fetch=FetchType.EAGER,cascade=CascadeType.ALL, orphanRemoval = false)
    private Aluno aluno;
    
    @ManyToMany(fetch = FetchType.EAGER ,cascade=CascadeType.REMOVE)
    @JoinTable(name = "estagiario_estagio", joinColumns
            = @JoinColumn(name = "estagiario_id"), inverseJoinColumns
            = @JoinColumn(name = "estagio_id"))
    private List<Estagio> estagios = new ArrayList<>();
    
    @ManyToMany(fetch = FetchType.EAGER ,cascade=CascadeType.REMOVE)
    @JoinTable(name = "estagiario_vaga", joinColumns
            = @JoinColumn(name = "estagiario_id"), inverseJoinColumns
            = @JoinColumn(name = "vaga_estagio_id"))
    private List<VagaEstagio> vagasEstagio = new ArrayList<>();
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="supervisor_id",nullable = true)
    private Supervisor supervisor;

    /**
     * @return the possuiCNH
     */
    public Boolean getPossuiCNH() {
        return possuiCNH;
    }

    /**
     * @param possuiCNH the possuiCNH to set
     */
    public void setPossuiCNH(Boolean possuiCNH) {
        this.possuiCNH = possuiCNH;
    }

    /**
     * @return the cnh
     */
    public String getCnh() {
        return cnh;
    }

    /**
     * @param cnh the cnh to set
     */
    public void setCnh(String cnh) {
        this.cnh = cnh;
    }

    /**
     * @return the dataEmissaoCNH
     */
    public Date getDataEmissaoCNH() {
        return dataEmissaoCNH;
    }

    /**
     * @param dataEmissaoCNH the dataEmissaoCNH to set
     */
    public void setDataEmissaoCNH(Date dataEmissaoCNH) {
        this.dataEmissaoCNH = dataEmissaoCNH;
    }

    /**
     * @return the dataVencimentoCNH
     */
    public Date getDataVencimentoCNH() {
        return dataVencimentoCNH;
    }

    /**
     * @param dataVencimentoCNH the dataVencimentoCNH to set
     */
    public void setDataVencimentoCNH(Date dataVencimentoCNH) {
        this.dataVencimentoCNH = dataVencimentoCNH;
    }

    /**
     * @return the categoriaCNH
     */
    public String getCategoriaCNH() {
        return categoriaCNH;
    }

    /**
     * @param categoriaCNH the categoriaCNH to set
     */
    public void setCategoriaCNH(String categoriaCNH) {
        this.categoriaCNH = categoriaCNH;
    }

    /**
     * @return the supervisor
     */
    public Supervisor getSupervisor() {
        return supervisor;
    }

    /**
     * @param supervisor the supervisor to set
     */
    public void setSupervisor(Supervisor supervisor) {
        this.supervisor = supervisor;
    }

    /**
     * @return the aluno
     */
    public Aluno getAluno() {
        return aluno;
    }

    /**
     * @param aluno the aluno to set
     */
    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    public List<Estagio> getEstagios() {
        return estagios;
    }

    public void setEstagios(List<Estagio> estagios) {
        this.estagios = estagios;
    }

    public List<VagaEstagio> getVagasEstagio() {
        return vagasEstagio;
    }

    public void setVagasEstagio(List<VagaEstagio> vagasEstagio) {
        this.vagasEstagio = vagasEstagio;
    }
}
