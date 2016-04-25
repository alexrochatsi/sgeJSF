package dominio;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "vaga_estagio")

@NamedQueries({
    @NamedQuery(name = "VagaEstagio.vagaAprovadas",
            query = "select v from VagaEstagio v where v.statusAprovacao = :statusAprovado")
})
public class VagaEstagio implements Serializable {

    @Id
    @SequenceGenerator(name = "vaga_estagio_id_gen",
            sequenceName = "vaga_estagio_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "vaga_estagio_id_gen")

    private Integer id;
    @Column(name = "valor_bolsa", nullable = false)
    private double valorBolsa;
    @Column(name = "atividade", length = 50, nullable = false)
    private String atividade;
    @Column(length = 3000, nullable = false)
    private String descricao;
    @Column(name = "status_aprovacao", nullable = false)
    private Boolean statusAprovacao;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="turnotrabalho_id",nullable = false)
    private Turno turnoTrabalho;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="empresa_id",nullable = false)
    private Empresa empresa;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "estagiario_vaga", joinColumns
            = @JoinColumn(name = "vaga_estagio_id"),
            inverseJoinColumns
            = @JoinColumn(name = "estagiario_id"))
    private List<Estagiario> estagiarios;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public double getValorBolsa() {
        return valorBolsa;
    }

    public void setValorBolsa(double valorBolsa) {
        this.valorBolsa = valorBolsa;
    }

    public String getAtividade() {
        return atividade;
    }

    public void setAtividade(String atividade) {
        this.atividade = atividade;
    }

    public Turno getTurnoTrabalho() {
        return turnoTrabalho;
    }

    public void setTurnoTrabalho(Turno turnoTrabalho) {
        this.turnoTrabalho = turnoTrabalho;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.id);
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
        if (!(obj instanceof VagaEstagio)) {
            return false;
        }
        VagaEstagio other = (VagaEstagio) obj;
        if(id == null) {
            if (other.id != null){
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        return true;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Boolean getStatusAprovacao() {
        return statusAprovacao;
    }

    public void setStatusAprovacao(Boolean statusAprovacao) {
        this.statusAprovacao = statusAprovacao;
    }

    public List<Estagiario> getEstagiarios() {
        return estagiarios;
    }

    public void setEstagiarios(List<Estagiario> estagiarios) {
        this.estagiarios = estagiarios;
    }

}
