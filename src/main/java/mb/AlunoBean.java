/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;

import dao.DaoFactory;
import dominio.Aluno;
import dominio.Curso;
import dominio.Estado;
import dominio.EstadoCivil;
import dominio.Sexo;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author alexrochatsi
 */
@ManagedBean(name = "alunoBean")
@RequestScoped
public class AlunoBean {

    private Aluno aluno = new Aluno();
    private List<Curso> cursos = new ArrayList<>();
    private List<Estado> estados = new ArrayList<>();
    private List<Sexo> sexos = new ArrayList<>();
    private List<EstadoCivil> estadosCivis = new ArrayList<>();
    private Integer id;

    public void loadModel() {

        DaoFactory daoFactory = new DaoFactory();

        if (getId() != null && getId() != -1) {
            aluno = (Aluno) daoFactory.getAlunoDao().findById(getId());
        } else {
            aluno = new Aluno();
        }
    }

    public String cancel() {
        return "alunoView?faces-redirect=true";
    }

    public String salvar() {

        DaoFactory daoFactory = new DaoFactory();
        if (aluno.getId() != null) {
            daoFactory.getAlunoDao().upd(aluno);
        } else {
            System.out.println("Nome do Aluno: "+aluno.getNome());
            System.out.println("Endereço Quadra: "+aluno.getEndereco().getBairro());
            daoFactory.getAlunoDao().add(aluno);
        }
        String msg = "Aluno: " + aluno.getNome() + " Salvo com sucesso!";
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null));
        return "alunoView?faces-redirect=true";
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Curso> getCursos() {
        if (cursos.isEmpty()) {
            DaoFactory daoFactory = new DaoFactory();
            setCursos(daoFactory.getCursoDao().listaAll());
        }
        return cursos;
    }

    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
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
}
