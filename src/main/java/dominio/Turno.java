/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author alexrochatsi
 */
@Entity
@Table(name="turno")
public class Turno implements Serializable {
    @Id
    @SequenceGenerator(name = "turno_id_gen",
            sequenceName = "turno_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "turno_id_gen")
    private Integer id;
    private String descricao;
    
    @OneToMany(mappedBy = "turnoTrabalho", targetEntity = VagaEstagio.class, fetch = FetchType.EAGER)
    private List<VagaEstagio> vagas;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
        hash = 11 * hash + Objects.hashCode(this.id);
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
        if (!(obj instanceof Turno)) {
            return false;
        }
        Turno other = (Turno) obj;
        if(id == null) {
            if (other.id != null){
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        return true;
    }

    public List<VagaEstagio> getVagas() {
        return vagas;
    }

    public void setVagas(List<VagaEstagio> vagas) {
        this.vagas = vagas;
    }
    
}
