/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;

import dao.DaoFactory;
import dominio.Empresa;
import dominio.Estado;
import dominio.PorteEmpresa;
import dominio.RamoAtividade;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author alexrochatsi
 */
@ManagedBean(name = "empresaBean")
@RequestScoped
public class EmpresaBean {

    private Empresa empresa = new Empresa();
    private List<Estado> estados = new ArrayList<>();
    private List<PorteEmpresa> portes = new ArrayList<>();
    private List<RamoAtividade> ramos = new ArrayList<>();
    private Integer id;

    public void loadModel() {

        DaoFactory daoFactory = new DaoFactory();

        if (getId() != null && getId() != -1) {
            empresa = (Empresa) daoFactory.getEmpresaDao().findById(getId());
        } else {
            empresa = new Empresa();
        }
    }

    public String cancel() {
        return "empresaView?faces-redirect=true";
    }

    public String salvar() {

        DaoFactory daoFactory = new DaoFactory();
        if (empresa.getId() != null) {
            daoFactory.getEmpresaDao().upd(empresa);
        } else {
            empresa.setStatusAprovacao(Boolean.FALSE);
            empresa.setDataCadastro(new Date());
            daoFactory.getEmpresaDao().add(empresa);
        }
        String msg = "Empresa: " + empresa.getNomeFantasia() + " Salva com sucesso!";
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null));
        return "empresaView?faces-redirect=true";
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Estado> getEstados() {
        if(estados.isEmpty()) {
            DaoFactory daoFactory = new DaoFactory();
            setEstados(daoFactory.getEstadoDao().listaAll());
        }
        return estados;
    }

    public void setEstados(List<Estado> estados) {
        this.estados = estados;
    }

    public List<PorteEmpresa> getPortes() {
        if(portes.isEmpty()) {
            DaoFactory daoFactory = new DaoFactory();
            setPortes(daoFactory.getPorteEmpresaDao().listaAll());
        }
        return portes;
    }

    public void setPortes(List<PorteEmpresa> portes) {
        this.portes = portes;
    }

    public List<RamoAtividade> getRamos() {
        if(ramos.isEmpty()) {
            DaoFactory daoFactory = new DaoFactory();
            setRamos(daoFactory.getRamoAtividadeDao().listaAll());
        }
        return ramos;
    }

    public void setRamos(List<RamoAtividade> ramos) {
        this.ramos = ramos;
    }
}
