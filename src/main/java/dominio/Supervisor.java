
package dominio;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="supervisor")
@PrimaryKeyJoinColumn(name = "pessoa_id")
public class Supervisor extends Pessoa {
    @Column(name = "cargo", length = 150, nullable = false)
    private String cargo;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="empresa_id",nullable = false)
    private Empresa empresa;
    
    @OneToMany(mappedBy = "supervisor", targetEntity = Estagiario.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Estagiario> estagiarios;
  
    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    /**
     * @return the estagiarios
     */
    public List<Estagiario> getEstagiarios() {
        return estagiarios;
    }

    /**
     * @param estagiarios the estagiarios to set
     */
    public void setEstagiarios(List<Estagiario> estagiarios) {
        this.estagiarios = estagiarios;
    }
}