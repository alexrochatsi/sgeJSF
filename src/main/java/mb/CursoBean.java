/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;

import dao.DaoFactory;
import dominio.Coordenacao;
import dominio.Curso;
import dominio.Turno;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author alexrochatsi
 */
@ManagedBean(name = "cursoBean")
@RequestScoped
public class CursoBean {

    private Curso curso = new Curso();
    private List<Coordenacao> coordenacoes = new ArrayList<>();
    private List<Turno> turnos = new ArrayList<>();
    private Integer id;

    public void loadModel() {

        DaoFactory daoFactory = new DaoFactory();

        if (getId() != null && getId() != -1) {
            curso = (Curso) daoFactory.getCursoDao().findById(getId());
        } else {
            curso = new Curso();
        }
    }

    public String cancel() {
        return "cursoView?faces-redirect=true";
    }

    public String salvar() {

        DaoFactory daoFactory = new DaoFactory();
        if (curso.getId() != null) {
            daoFactory.getCursoDao().upd(curso);
        } else {
            daoFactory.getCursoDao().add(curso);
        }
        String msg = "Curso: " + curso.getNome() + " Salvo com sucesso!";
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null));
        return "cursoView?faces-redirect=true";
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the coordenacoes
     */
    public List<Coordenacao> getCoordenacoes() {
        if (coordenacoes.isEmpty()) {
            DaoFactory daoFactory = new DaoFactory();
            setCoordenacoes(daoFactory.getCoordenacaoDao().listaAll());
        }
        return coordenacoes;
    }

    /**
     * @param coordenacoes the coordenacoes to set
     */
    public void setCoordenacoes(List<Coordenacao> coordenacoes) {
        this.coordenacoes = coordenacoes;
    }

    /**
     * @return the turnos
     */
    public List<Turno> getTurnos() {
        return turnos;
    }

    /**
     * @param turnos the turnos to set
     */
    public void setTurnos(List<Turno> turnos) {
        this.turnos = turnos;
    }
}
