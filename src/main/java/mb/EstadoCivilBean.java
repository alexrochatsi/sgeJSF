/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;

import dao.DaoFactory;
import dominio.EstadoCivil;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author alexrochatsi
 */
@ManagedBean(name = "estadoCivilBean")
@RequestScoped
public class EstadoCivilBean {

    private EstadoCivil estadoCivil = new EstadoCivil();
    private Integer id;

    public void loadModel() {

        DaoFactory daoFactory = new DaoFactory();

        if (getId() != null && getId() != -1) {
            estadoCivil = (EstadoCivil) daoFactory.getEstadoCivilDao().findById(getId());
        } else {
            estadoCivil = new EstadoCivil();
        }
    }

    public String cancel() {
        return "estadoCivilView?faces-redirect=true";
    }

    public String salvar() {

        DaoFactory daoFactory = new DaoFactory();
        if (estadoCivil.getId() != null) {
            daoFactory.getEstadoCivilDao().upd(estadoCivil);
        } else {
            daoFactory.getEstadoCivilDao().add(estadoCivil);
        }
        String msg = "EstadoCivil: " + estadoCivil.getDescricao()+ " Salva com sucesso!";
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null));
        return "estadoCivilView?faces-redirect=true";
    }

    public EstadoCivil getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(EstadoCivil estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
