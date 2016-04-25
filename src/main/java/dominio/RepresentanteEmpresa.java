package dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="representante_empresa")
@PrimaryKeyJoinColumn(name = "pessoa_id")
public class RepresentanteEmpresa extends Pessoa {
    @Column(name = "chapa", length = 20, nullable = false)
    private String chapa;

    public String getChapa() {
        return chapa;
    }

    public void setChapa(String chapa) {
        this.chapa = chapa;
    }
}
