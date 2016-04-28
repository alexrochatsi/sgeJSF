package dominio;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author Rocha
 */
@Entity
@Table(name="instituicao")
public class Instituicao implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="instituicao_id")
    private Integer id;
    @Column(name = "nome", length = 300, nullable = false)
    private String nome;
    
    //outra forma de fazer é usando @Embedded, só que mescla a tabela endereço com a instituicao
    @OneToOne(fetch=FetchType.EAGER,cascade=CascadeType.ALL, orphanRemoval = true)
    private Endereco endereco;
    
    @OneToOne(fetch=FetchType.EAGER,cascade=CascadeType.ALL, orphanRemoval = true)
    private Telefone telefone;
       
    @OneToMany(mappedBy = "instituicao", targetEntity = Coordenacao.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Coordenacao> coordenacoes;
    
    public Instituicao() {
        endereco = new Endereco();
        telefone = new Telefone();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public Endereco getEndereco() {
        return endereco;
    }
    
    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.id);
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
        if (!(obj instanceof Instituicao)) {
            return false;
        }
        Instituicao other = (Instituicao) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        return true;
    } 

    public List<Coordenacao> getCoordenacoes() {
        return coordenacoes;
    }

    public void setCoordenacoes(List<Coordenacao> coordenacoes) {
        this.coordenacoes = coordenacoes;
    }

    public Telefone getTelefone() {
        return telefone;
    }

    public void setTelefone(Telefone telefone) {
        this.telefone = telefone;
    }
}
