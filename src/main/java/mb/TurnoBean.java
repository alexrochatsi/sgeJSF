/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;

import dao.DaoFactory;
import dominio.Turno;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author alexrochatsi
 */
@ManagedBean(name = "turnoBean")
@RequestScoped
public class TurnoBean {

    private Turno turno = new Turno();
    private Integer id;

    public void loadModel() {

        DaoFactory daoFactory = new DaoFactory();

        if (getId() != null && getId() != -1) {
            turno = (Turno) daoFactory.getTurnoDao().findById(getId());
        } else {
            turno = new Turno();
        }
    }

    public String cancel() {
        return "turnoView?faces-redirect=true";
    }

    public String salvar() {

        DaoFactory daoFactory = new DaoFactory();
        if (turno.getId() != null) {
            daoFactory.getTurnoDao().upd(turno);
        } else {
            daoFactory.getTurnoDao().add(turno);
        }
        String msg = "Turno: " + turno.getDescricao()+ " Salvo com sucesso!";
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null));
        return "turnoView?faces-redirect=true";
    }

    public Turno getTurno() {
        return turno;
    }

    public void setTurno(Turno turno) {
        this.turno = turno;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
