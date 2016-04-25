/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;

import dao.DaoFactory;
import dominio.Estagio;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author alexrochatsi
 */
@ManagedBean(name = "estagioBean")
@RequestScoped
public class EstagioBean {

    private Estagio estagio = new Estagio();
    private Integer id;

    public void loadModel() {

        DaoFactory daoFactory = new DaoFactory();

        if (getId() != null && getId() != -1) {
            estagio = (Estagio) daoFactory.getEstagioDao().findById(getId());
        } else {
            estagio = new Estagio();
        }
    }

    public String cancel() {
        return "estagioView?faces-redirect=true";
    }

    public String salvar() {

        DaoFactory daoFactory = new DaoFactory();
        if (estagio.getId() != null) {
            daoFactory.getEstagioDao().upd(estagio);
        } else {
            daoFactory.getEstagioDao().add(estagio);
        }
        String msg = "Estagio: " + estagio.getAtividade()+ " Salvo com sucesso!";
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null));
        return "estagioView?faces-redirect=true";
    }

    public Estagio getEstagio() {
        return estagio;
    }

    public void setEstagio(Estagio estagio) {
        this.estagio = estagio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
