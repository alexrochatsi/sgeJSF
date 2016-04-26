/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;

import dao.DaoFactory;
import dominio.Curso;
import dominio.Estado;
import dominio.EstadoCivil;
import dominio.Professor;
import dominio.Sexo;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
//import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author alexrochatsi
 */
@ManagedBean(name = "professorBean")
@ViewScoped
public class ProfessorBean {

    private Professor professor = new Professor();
    private Curso curso = new Curso();
    private List<Curso> cursos = new ArrayList<>();
    private List<Estado> estados = new ArrayList<>();
    private List<Sexo> sexos = new ArrayList<>();
    private List<EstadoCivil> estadosCivis = new ArrayList<>();
    private Integer id;

    public void loadModel() {

        DaoFactory daoFactory = new DaoFactory();

        if (getId() != null && getId() != -1) {
            professor = (Professor) daoFactory.getProfessorDao().findById(getId());
        } else {
            professor = new Professor();
        }
    }
    
    public void saveCurso() {
        //setar os relacionamentos
        System.out.println("Entrei com o Curso "+curso.getNome());
        professor.getCursos().add(curso);
        //reseta o objeto
        curso = new Curso();
    }
    
    public void removeCurso(Curso rCurso) {

        professor.getCursos().remove(rCurso);

    }

    public String cancel() {
        return "professorView?faces-redirect=true";
    }

    public String salvar() {

        DaoFactory daoFactory = new DaoFactory();
        if (professor.getId() != null) {
            daoFactory.getProfessorDao().upd(professor);
        } else {
            daoFactory.getProfessorDao().add(professor);
        }
        String msg = "Professor: " + professor.getNome() + " Salvo com sucesso!";
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null));
        return "professorView?faces-redirect=true";
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public List<Estado> getEstados() {
        if (estados.isEmpty()) {
            DaoFactory daoFactory = new DaoFactory();
            setEstados(daoFactory.getEstadoDao().listaAll());
        }
        return estados;
    }

    public void setEstados(List<Estado> estados) {
        this.estados = estados;
    }
    
    
    public List<Sexo> getSexos() {
        if (sexos.isEmpty()) {
            DaoFactory daoFactory = new DaoFactory();
            setSexos(daoFactory.getSexoDao().listaAll());
        }
        return sexos;
    }

    public void setSexos(List<Sexo> sexos) {
        this.sexos = sexos;
    }

    public List<EstadoCivil> getEstadosCivis() {
        if (estadosCivis.isEmpty()) {
            DaoFactory daoFactory = new DaoFactory();
            setEstadosCivis(daoFactory.getEstadoCivilDao().listaAll());
        }
        return estadosCivis;
    }

    public void setEstadosCivis(List<EstadoCivil> estadosCivis) {
        this.estadosCivis = estadosCivis;
    }

    public List<Curso> getCursos() {
        if(cursos.isEmpty()) {
            DaoFactory daoFactory = new DaoFactory();
            setCursos(daoFactory.getCursoDao().listaAll());
        }
        return cursos;
    }

    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        System.out.println("Menu salvou um item");
        this.curso = curso;
    }
}
