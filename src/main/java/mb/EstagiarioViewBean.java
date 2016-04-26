/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;

import dao.DaoFactory;
import dominio.Estagiario;
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
@ManagedBean(name = "estagiarioViewBean")
@RequestScoped
public class EstagiarioViewBean {

    private List<Estagiario> estagiarios = new ArrayList<>();
    private String valorBusca;
    private boolean exibe = false;

    public String excluirEstagiario(Estagiario a) {

        DaoFactory daoFactory = new DaoFactory();
        daoFactory.getEstagiarioDao().del(a);
        String msg = "Estagiario: " + a.getAluno().getNome()+ " Excluído com sucesso!";
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null));
        return "estagiarioView?faces-redirect=true";
    }
    public String prepararAdicionarEstagiario() {
        return "estagiarioIncluir?faces-redirect=true&id=-1";
    }
    
    public String prepararAlterarEstagiario(Estagiario estagiario) {
        return "estagiarioIncluir?faces-redirect=true&id=" + estagiario.getId();
    }
    
    public void buscarEstagiario() {
        DaoFactory daoFactory = new DaoFactory();
        estagiarios.clear();
        estagiarios = daoFactory.getEstagiarioDao().JpqlLike(getValorBusca(), "nome");
        exibe = true;
    }
    
    public List<Estagiario> buscarTodos() {
        DaoFactory daoFactory = new DaoFactory();
        estagiarios = daoFactory.getEstagiarioDao().listaAll();
        exibe = false;
        return estagiarios;
    }
    
    public List<Estagiario> getEstagiarios() {
        if ((estagiarios.isEmpty()) && (exibe == false)) {
            DaoFactory daoFactory = new DaoFactory();
            estagiarios = daoFactory.getEstagiarioDao().listaAll();
        }
        return estagiarios;
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
