/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;

import dao.DaoFactory;
import dominio.Supervisor;
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
@ManagedBean(name = "supervisorViewBean")
@RequestScoped
public class SupervisorViewBean {

    private List<Supervisor> supervisores = new ArrayList<>();
    private String valorBusca;
    private boolean exibe = false;

    public String excluirSupervisor(Supervisor a) {

        DaoFactory daoFactory = new DaoFactory();
        daoFactory.getSupervisorDao().del(a);
        String msg = "Supervisor: " + a.getNome() + " Excluído com sucesso!";
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null));
        return "supervisorView?faces-redirect=true";
    }
    public String prepararAdicionarSupervisor() {
        return "supervisorIncluir?faces-redirect=true&id=-1";
    }
    
    public String prepararAlterarSupervisor(Supervisor supervisor) {
        return "supervisorIncluir?faces-redirect=true&id=" + supervisor.getId();
    }
    
    public void buscarSupervisor() {
        DaoFactory daoFactory = new DaoFactory();
        supervisores.clear();
        supervisores = daoFactory.getSupervisorDao().JpqlLike(getValorBusca(), "nome");
        exibe = true;
    }
    
    public List<Supervisor> buscarTodos() {
        DaoFactory daoFactory = new DaoFactory();
        supervisores = daoFactory.getSupervisorDao().listaAll();
        exibe = false;
        return supervisores;
    }
    
    public List<Supervisor> getSupervisors() {
        if ((supervisores.isEmpty()) && (exibe == false)) {
            DaoFactory daoFactory = new DaoFactory();
            supervisores = daoFactory.getSupervisorDao().listaAll();
        }
        return supervisores;
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
