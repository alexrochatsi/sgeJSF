/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;

import dao.DaoFactory;
import dominio.Estagiario;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author alexrochatsi
 */
@ManagedBean(name = "estagiarioBean")
@RequestScoped
public class EstagiarioBean {

    private Estagiario estagiario = new Estagiario();
    private Integer id;

    public void loadModel() {

        DaoFactory daoFactory = new DaoFactory();

        if (getId() != null && getId() != -1) {
            estagiario = (Estagiario) daoFactory.getEstagiarioDao().findById(getId());
        } else {
            estagiario = new Estagiario();
        }
    }

    public String cancel() {
        return "estagiarioView?faces-redirect=true";
    }

    public String salvar() {

        DaoFactory daoFactory = new DaoFactory();
        if (estagiario.getId() != null) {
            daoFactory.getEstagiarioDao().upd(estagiario);
        } else {
            daoFactory.getEstagiarioDao().add(estagiario);
        }
        String msg = "Estagiario: " + estagiario.getAluno().getNome()+ " Salvo com sucesso!";
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null));
        return "estagiarioView?faces-redirect=true";
    }

    public Estagiario getEstagiario() {
        return estagiario;
    }

    public void setEstagiario(Estagiario estagiario) {
        this.estagiario = estagiario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
