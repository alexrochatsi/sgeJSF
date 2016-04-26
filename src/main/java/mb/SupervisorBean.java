/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;

import dao.DaoFactory;
import dominio.Supervisor;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author alexrochatsi
 */
@ManagedBean(name = "supervisorBean")
@RequestScoped
public class SupervisorBean {

    private Supervisor supervisor = new Supervisor();
    private Integer id;

    public void loadModel() {

        DaoFactory daoFactory = new DaoFactory();

        if (getId() != null && getId() != -1) {
            supervisor = (Supervisor) daoFactory.getSupervisorDao().findById(getId());
        } else {
            supervisor = new Supervisor();
        }
    }

    public String cancel() {
        return "supervisorView?faces-redirect=true";
    }

    public String salvar() {

        DaoFactory daoFactory = new DaoFactory();
        if (supervisor.getId() != null) {
            daoFactory.getSupervisorDao().upd(supervisor);
        } else {
            daoFactory.getSupervisorDao().add(supervisor);
        }
        String msg = "Supervisor: " + supervisor.getNome() + " Salvo com sucesso!";
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null));
        return "supervisorView?faces-redirect=true";
    }

    public Supervisor getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Supervisor supervisor) {
        this.supervisor = supervisor;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
