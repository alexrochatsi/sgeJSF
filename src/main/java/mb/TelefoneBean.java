/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;

import dao.DaoFactory;
import dominio.Telefone;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author alexrochatsi
 */
@ManagedBean(name = "telefoneBean")
@RequestScoped
public class TelefoneBean {

    private Telefone telefone = new Telefone();
    private Integer id;

    public void loadModel() {

        DaoFactory daoFactory = new DaoFactory();

        if (getId() != null && getId() != -1) {
            telefone = (Telefone) daoFactory.getTelefoneDao().findById(getId());
        } else {
            telefone = new Telefone();
        }
    }

    public String cancel() {
        return "telefoneView?faces-redirect=true";
    }

    public String salvar() {

        DaoFactory daoFactory = new DaoFactory();
        if (telefone.getId() != null) {
            daoFactory.getTelefoneDao().upd(telefone);
        } else {
            daoFactory.getTelefoneDao().add(telefone);
        }
        String msg = "Telefone: " + telefone.getNumero()+ " Salvo com sucesso!";
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null));
        return "telefoneView?faces-redirect=true";
    }

    public Telefone getTelefone() {
        return telefone;
    }

    public void setTelefone(Telefone telefone) {
        this.telefone = telefone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
