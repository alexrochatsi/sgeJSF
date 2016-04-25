/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;

import dao.DaoFactory;
import dominio.Coordenador;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author alexrochatsi
 */
@ManagedBean(name = "coordenadorBean")
@RequestScoped
public class CoordenadorBean {

    private Coordenador coordenador = new Coordenador();
    private Integer id;

    public void loadModel() {

        DaoFactory daoFactory = new DaoFactory();

        if (getId() != null && getId() != -1) {
            coordenador = (Coordenador) daoFactory.getCoordenadorDao().findById(getId());
        } else {
            coordenador = new Coordenador();
        }
    }

    public String cancel() {
        return "coordenadorView?faces-redirect=true";
    }

    public String salvar() {

        DaoFactory daoFactory = new DaoFactory();
        if (coordenador.getId() != null) {
            daoFactory.getCoordenadorDao().upd(coordenador);
        } else {
            daoFactory.getCoordenadorDao().add(coordenador);
        }
        String msg = "Coordenador: " + coordenador.getNome() + " Salvo com sucesso!";
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null));
        return "coordenadorView?faces-redirect=true";
    }

    public Coordenador getCoordenador() {
        return coordenador;
    }

    public void setCoordenador(Coordenador coordenador) {
        this.coordenador = coordenador;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
