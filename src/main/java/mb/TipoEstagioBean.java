/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;

import dao.DaoFactory;
import dominio.TipoEstagio;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author alexrochatsi
 */
@ManagedBean(name = "tipoEstagioBean")
@RequestScoped
public class TipoEstagioBean {

    private TipoEstagio tipoEstagio = new TipoEstagio();
    private Integer id;

    public void loadModel() {

        DaoFactory daoFactory = new DaoFactory();

        if (getId() != null && getId() != -1) {
            tipoEstagio = (TipoEstagio) daoFactory.getTipoEstagioDao().findById(getId());
        } else {
            tipoEstagio = new TipoEstagio();
        }
    }

    public String cancel() {
        return "tipoEstagioView?faces-redirect=true";
    }

    public String salvar() {

        DaoFactory daoFactory = new DaoFactory();
        if (tipoEstagio.getId() != null) {
            daoFactory.getTipoEstagioDao().upd(tipoEstagio);
        } else {
            daoFactory.getTipoEstagioDao().add(tipoEstagio);
        }
        String msg = "TipoEstagio: " + tipoEstagio.getDescricao()+ " Salvo com sucesso!";
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null));
        return "tipoEstagioView?faces-redirect=true";
    }

    public TipoEstagio getTipoEstagio() {
        return tipoEstagio;
    }

    public void setTipoEstagio(TipoEstagio tipoEstagio) {
        this.tipoEstagio = tipoEstagio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
