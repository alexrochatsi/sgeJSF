/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;

import dao.DaoFactory;
import dominio.Empresa;
import dominio.Turno;
import dominio.VagaEstagio;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author alexrochatsi
 */
@ManagedBean(name = "vagaEstagioBean")
@ViewScoped
public class VagaEstagioBean {

    private VagaEstagio vagaEstagio = new VagaEstagio();
    private Integer id;
    private List<Turno> turnos = new ArrayList<>();
    private List<Empresa> empresas = new ArrayList<>();

    public void loadModel() {

        DaoFactory daoFactory = new DaoFactory();

        if (getId() != null && getId() != -1) {
            vagaEstagio = (VagaEstagio) daoFactory.getVagaEstagioDao().findById(getId());
        } else {
            vagaEstagio = new VagaEstagio();
        }
    }

    public String cancel() {
        return "vagaEstagioView?faces-redirect=true";
    }

    public String salvar() {
        DaoFactory daoFactory = new DaoFactory();      
        if (vagaEstagio.getId() != null) {
            daoFactory.getVagaEstagioDao().upd(vagaEstagio);
        } else {
            vagaEstagio.setStatusAprovacao(Boolean.FALSE);
            daoFactory.getVagaEstagioDao().add(vagaEstagio);
        }
        String msg = "Vaga [" + vagaEstagio.getAtividade()+ "] salva com sucesso!";
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null));
        return "vagaEstagioView?faces-redirect=true";
    }

    public VagaEstagio getVagaEstagio() {
        return vagaEstagio;
    }

    public void setVagaEstagio(VagaEstagio vagaEstagio) {
        this.vagaEstagio = vagaEstagio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Turno> getTurnos() {
        if (turnos.isEmpty()) {
            DaoFactory daoFactory = new DaoFactory();
            setTurnos(daoFactory.getTurnoDao().listaAll());
        }
        return turnos;
    }

    public void setTurnos(List<Turno> turnos) {
        this.turnos = turnos;
    }
    
    public List<Empresa> getEmpresas() {
        if (empresas.isEmpty()) {
            DaoFactory daoFactory = new DaoFactory();
            setEmpresas(daoFactory.getEmpresaEspecialistaDao().getEmpresasAprovadas());
        }
        return empresas;
    }

    public void setEmpresas(List<Empresa> empresas) {
        this.empresas = empresas;
    }
}
