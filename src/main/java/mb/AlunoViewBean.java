/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;

import dao.DaoFactory;
import dominio.Aluno;
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
@ManagedBean(name = "alunoViewBean")
@RequestScoped
public class AlunoViewBean {

    private List<Aluno> alunos = new ArrayList<>();
    private String valorBusca;
    private boolean exibe = false;

    public String excluirAluno(Aluno a) {

        DaoFactory daoFactory = new DaoFactory();
        daoFactory.getAlunoDao().del(a);
        String msg = "Aluno: " + a.getNome() + " Excluído com sucesso!";
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null));
        return "alunoView?faces-redirect=true";
    }
    public String prepararAdicionarAluno() {
        return "alunoIncluir?faces-redirect=true&id=-1";
    }
    
    public String prepararAlterarAluno(Aluno aluno) {
        return "alunoIncluir?faces-redirect=true&id=" + aluno.getId();
    }
    
    public void buscarAluno() {
        DaoFactory daoFactory = new DaoFactory();
        alunos.clear();
        alunos = daoFactory.getAlunoDao().JpqlLike(getValorBusca(), "nome");
        exibe = true;
    }
    
    public List<Aluno> buscarTodos() {
        DaoFactory daoFactory = new DaoFactory();
        alunos = daoFactory.getAlunoDao().listaAll();
        exibe = false;
        return alunos;
    }
    
    public List<Aluno> getAlunos() {
        if ((alunos.isEmpty()) && (exibe == false)) {
            DaoFactory daoFactory = new DaoFactory();
            alunos = daoFactory.getAlunoDao().listaAll();
        }
        return alunos;
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
