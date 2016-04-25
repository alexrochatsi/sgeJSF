/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;

import dao.DaoFactory;
import dominio.Estado;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author alexrochatsi
 */
@ManagedBean(name = "estadoBean")
@RequestScoped
public class EstadoBean {

    private Estado estado = new Estado();
    private Integer id;

    public void loadModel() {

        DaoFactory daoFactory = new DaoFactory();

        if (getId() != null && getId() != -1) {
            estado = (Estado) daoFactory.getEstadoDao().findById(getId());
        } else {
            estado = new Estado();
        }
    }

    public String cancel() {
        return "estadoView?faces-redirect=true";
    }

    public String salvar() {

        DaoFactory daoFactory = new DaoFactory();
        if (estado.getId() != null) {
            daoFactory.getEstadoDao().upd(estado);
        } else {
            daoFactory.getEstadoDao().add(estado);
        }
        String msg = "Estado: " + estado.getDescricao()+ " salvo com sucesso!";
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null));
        return "estadoView?faces-redirect=true";
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
