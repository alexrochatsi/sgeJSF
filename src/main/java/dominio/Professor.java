package dominio;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="professor")
@PrimaryKeyJoinColumn(name = "pessoa_id")
public class Professor extends Pessoa {
    @Column(name = "matricula", length = 30, nullable = false)
    private String matricula;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "curso_professor", joinColumns
            = @JoinColumn(name = "professor_id"),
            inverseJoinColumns
            = @JoinColumn(name = "curso_id"))
    private List<Curso> cursos;

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public List<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }
}
