package dominio;

import java.io.Serializable;
import java.util.ArrayList;
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "curso")
public class Curso implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="curso_id")
    private Integer id;

    @Column(length = 100, nullable = false)
    private String nome;
    @Column(name = "conteudo_programatico", length = 3000, nullable = false)
    private String conteudoProgramatico;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="coordenacao_id",nullable = false)
    private Coordenacao coordenacao;
    
    @OneToOne(fetch=FetchType.EAGER,cascade=CascadeType.ALL, orphanRemoval = false)
    private Turno turno;
    
    @ManyToMany(fetch = FetchType.EAGER ,cascade=CascadeType.REMOVE)
    @JoinTable(name = "curso_professor", joinColumns
            = @JoinColumn(name = "curso_id"), inverseJoinColumns
            = @JoinColumn(name = "professor_id"))
    private List<Professor> professores = new ArrayList<>();
    
    @OneToMany(mappedBy = "curso", targetEntity = Aluno.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Aluno> alunos;

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

    public String getConteudoProgramatico() {
        return conteudoProgramatico;
    }

    public void setConteudoProgramatico(String conteudoProgramatico) {
        this.conteudoProgramatico = conteudoProgramatico;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
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
        if (!(obj instanceof Curso)) {
            return false;
        }
        Curso other = (Curso) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        
        return true;
    }

    public List<Professor> getProfessores() {
        return professores;
    }

    public void setProfessores(List<Professor> professores) {
        this.professores = professores;
    }

    public Coordenacao getCoordenacao() {
        return coordenacao;
    }

    public void setCoordenacao(Coordenacao coordenacao) {
        this.coordenacao = coordenacao;
    }

    public Turno getTurno() {
        return turno;
    }

    public void setTurno(Turno turno) {
        this.turno = turno;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }
}
