/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;

import dao.DaoFactory;
import dominio.RepresentanteEmpresa;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author alexrochatsi
 */
@ManagedBean(name = "representanteEmpresaBean")
@RequestScoped
public class RepresentanteEmpresaBean {

    private RepresentanteEmpresa representanteEmpresa = new RepresentanteEmpresa();
    private Integer id;

    public void loadModel() {

        DaoFactory daoFactory = new DaoFactory();

        if (getId() != null && getId() != -1) {
            representanteEmpresa = (RepresentanteEmpresa) daoFactory.getRepresentanteEmpresaDao().findById(getId());
        } else {
            representanteEmpresa = new RepresentanteEmpresa();
        }
    }

    public String cancel() {
        return "representanteEmpresaView?faces-redirect=true";
    }

    public String salvar() {

        DaoFactory daoFactory = new DaoFactory();
        if (representanteEmpresa.getId() != null) {
            daoFactory.getRepresentanteEmpresaDao().upd(representanteEmpresa);
        } else {
            daoFactory.getRepresentanteEmpresaDao().add(representanteEmpresa);
        }
        String msg = "RepresentanteEmpresa: " + representanteEmpresa.getNome() + " Salvo com sucesso!";
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null));
        return "representanteEmpresaView?faces-redirect=true";
    }

    public RepresentanteEmpresa getRepresentanteEmpresa() {
        return representanteEmpresa;
    }

    public void setRepresentanteEmpresa(RepresentanteEmpresa representanteEmpresa) {
        this.representanteEmpresa = representanteEmpresa;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
