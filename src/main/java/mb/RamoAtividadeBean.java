/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;

import dao.DaoFactory;
import dominio.RamoAtividade;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author alexrochatsi
 */
@ManagedBean(name = "ramoAtividadeBean")
@RequestScoped
public class RamoAtividadeBean {

    private RamoAtividade ramoAtividade = new RamoAtividade();
    private Integer id;

    public void loadModel() {

        DaoFactory daoFactory = new DaoFactory();

        if (getId() != null && getId() != -1) {
            ramoAtividade = (RamoAtividade) daoFactory.getRamoAtividadeDao().findById(getId());
        } else {
            ramoAtividade = new RamoAtividade();
        }
    }

    public String cancel() {
        return "ramoAtividadeView?faces-redirect=true";
    }

    public String salvar() {

        DaoFactory daoFactory = new DaoFactory();
        if (ramoAtividade.getId() != null) {
            daoFactory.getRamoAtividadeDao().upd(ramoAtividade);
        } else {
            daoFactory.getRamoAtividadeDao().add(ramoAtividade);
        }
        String msg = "Ramo de atividade: " + ramoAtividade.getDescricao()+ " salvo com sucesso!";
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null));
        return "ramoAtividadeView?faces-redirect=true";
    }

    public RamoAtividade getRamoAtividade() {
        return ramoAtividade;
    }

    public void setRamoAtividade(RamoAtividade ramoAtividade) {
        this.ramoAtividade = ramoAtividade;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
