/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;

import dao.DaoFactory;
import dominio.PorteEmpresa;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author alexrochatsi
 */
@ManagedBean(name = "porteEmpresaBean")
@RequestScoped
public class PorteEmpresaBean {

    private PorteEmpresa porteEmpresa = new PorteEmpresa();
    private Integer id;

    public void loadModel() {

        DaoFactory daoFactory = new DaoFactory();

        if (getId() != null && getId() != -1) {
            porteEmpresa = (PorteEmpresa) daoFactory.getPorteEmpresaDao().findById(getId());
        } else {
            porteEmpresa = new PorteEmpresa();
        }
    }

    public String cancel() {
        return "porteEmpresaView?faces-redirect=true";
    }

    public String salvar() {

        DaoFactory daoFactory = new DaoFactory();
        if (porteEmpresa.getId() != null) {
            daoFactory.getPorteEmpresaDao().upd(porteEmpresa);
        } else {
            daoFactory.getPorteEmpresaDao().add(porteEmpresa);
        }
        String msg = "Porte da empresa: " + porteEmpresa.getDescricao()+ " salvo com sucesso!";
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null));
        return "porteEmpresaView?faces-redirect=true";
    }

    public PorteEmpresa getPorteEmpresa() {
        return porteEmpresa;
    }

    public void setPorteEmpresa(PorteEmpresa porteEmpresa) {
        this.porteEmpresa = porteEmpresa;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
