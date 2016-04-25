/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;

import dao.DaoFactory;
import dominio.Curso;
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
@ManagedBean(name = "cursoViewBean")
@RequestScoped
public class CursoViewBean {

    private List<Curso> cursos = new ArrayList<>();
    private String valorBusca;
    private boolean exibe = false;

    public String excluirCurso(Curso a) {

        DaoFactory daoFactory = new DaoFactory();
        daoFactory.getCursoDao().del(a);
        String msg = "Curso: " + a.getNome() + " Excluído com sucesso!";
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null));
        return "cursoView?faces-redirect=true";
    }
    public String prepararAdicionarCurso() {
        return "cursoIncluir?faces-redirect=true&id=-1";
    }
    
    public String prepararAlterarCurso(Curso curso) {
        return "cursoIncluir?faces-redirect=true&id=" + curso.getId();
    }
    
    public void buscarCurso() {
        DaoFactory daoFactory = new DaoFactory();
        cursos.clear();
        cursos = daoFactory.getCursoDao().JpqlLike(getValorBusca(), "nome");
        exibe = true;
    }
    
    public List<Curso> buscarTodos() {
        DaoFactory daoFactory = new DaoFactory();
        cursos = daoFactory.getCursoDao().listaAll();
        exibe = false;
        return cursos;
    }
    
    public List<Curso> getCursos() {
        if ((cursos.isEmpty()) && (exibe == false)) {
            DaoFactory daoFactory = new DaoFactory();
            cursos = daoFactory.getCursoDao().listaAll();
        }
        return cursos;
    }
    
    public String getValorBusca() {
        return valorBusca;
    }
    
    public void setValorBusca(String valorBusca) {
        this.valorBusca = valorBusca;
    }
    
    public boolean isExibe() {
        return exibe;
    }
}
