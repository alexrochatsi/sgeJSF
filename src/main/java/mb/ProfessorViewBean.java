/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;

import dao.DaoFactory;
import dominio.Professor;
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
@ManagedBean(name = "professorViewBean")
@RequestScoped
public class ProfessorViewBean {

    private List<Professor> professors = new ArrayList<>();
    private String valorBusca;
    private boolean exibe = false;

    public String excluirProfessor(Professor a) {

        DaoFactory daoFactory = new DaoFactory();
        daoFactory.getProfessorDao().del(a);
        String msg = "Professor: " + a.getNome() + " Excluído com sucesso!";
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null));
        return "professorView?faces-redirect=true";
    }
    public String prepararAdicionarProfessor() {
        return "professorIncluir?faces-redirect=true&id=-1";
    }
    
    public String prepararAlterarProfessor(Professor professor) {
        return "professorIncluir?faces-redirect=true&id=" + professor.getId();
    }
    
    public void buscarProfessor() {
        DaoFactory daoFactory = new DaoFactory();
        professors.clear();
        professors = daoFactory.getProfessorDao().JpqlLike(getValorBusca(), "nome");
        exibe = true;
    }
    
    public List<Professor> buscarTodos() {
        DaoFactory daoFactory = new DaoFactory();
        professors = daoFactory.getProfessorDao().listaAll();
        exibe = false;
        return professors;
    }
    
    public List<Professor> getProfessors() {
        if ((professors.isEmpty()) && (exibe == false)) {
            DaoFactory daoFactory = new DaoFactory();
            professors = daoFactory.getProfessorDao().listaAll();
        }
        return professors;
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
