package dominio;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table(name = "estagio")
public class Estagio implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    @Column(name = "valor_bolsa")
    private double valorBolsa;
    @Column(name = "data_inicio")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataInicio;
    @Column(name = "data_termino")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataTermino;
    @Column(name = "atividade", length = 2000, nullable = false)
    private String atividade;
    @Column(name = "carga_horaria", length = 2, nullable = false)
    private Integer cargaHoraria;
    @Column(name = "status_aprovacao")
    private Boolean statusAprovacao;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="empresa_id",nullable = false)
    private Empresa empresa;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "estagiario_estagio", joinColumns
            = @JoinColumn(name = "estagio_id"),
            inverseJoinColumns
            = @JoinColumn(name = "estagiario_id"))
    private List<Estagiario> estagiarios;
    
    @OneToOne(fetch=FetchType.EAGER,cascade=CascadeType.ALL, orphanRemoval = false)
    private Turno turno;
    
    @OneToOne(fetch=FetchType.EAGER,cascade=CascadeType.ALL, orphanRemoval = false)
    private TipoEstagio tipoEstagio;
    
    @OneToOne(fetch=FetchType.EAGER,cascade=CascadeType.ALL, orphanRemoval = false)
    private RamoAtividade ramoAtividade;

    public void setStatusAprovacao(Boolean statusAprovacao) {
        this.statusAprovacao = statusAprovacao;
    }

    public Boolean getStatusAprovacao() {
        return statusAprovacao;
    }

    public double getValorBolsa() {
        return valorBolsa;
    }

    public void setValorBolsa(double valorBolsa) {
        this.valorBolsa = valorBolsa;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataTermino() {
        return dataTermino;
    }

    public void setDataTermino(Date dataTermino) {
        this.dataTermino = dataTermino;
    }

    public String getAtividade() {
        return atividade;
    }

    public void setAtividade(String atividade) {
        this.atividade = atividade;
    }

    public Integer getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(Integer cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Estagio)) {
            return false;
        }
        Estagio other = (Estagio) obj;
        if(id == null) {
            if (other.id != null){
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        return true;
    }

    public Turno getTurno() {
        return turno;
    }

    public void setTurno(Turno turno) {
        this.turno = turno;
    }

    public TipoEstagio getTipoEstagio() {
        return tipoEstagio;
    }

    public void setTipoEstagio(TipoEstagio tipoEstagio) {
        this.tipoEstagio = tipoEstagio;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    /**
     * @return the ramoAtividade
     */
    public RamoAtividade getRamoAtividade() {
        return ramoAtividade;
    }

    /**
     * @param ramoAtividade the ramoAtividade to set
     */
    public void setRamoAtividade(RamoAtividade ramoAtividade) {
        this.ramoAtividade = ramoAtividade;
    }

    public List<Estagiario> getEstagiarios() {
        return estagiarios;
    }

    public void setEstagiarios(List<Estagiario> estagiarios) {
        this.estagiarios = estagiarios;
    }

    
}
