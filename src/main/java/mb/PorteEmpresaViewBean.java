/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;

import dao.DaoFactory;
import dominio.PorteEmpresa;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author alexrochatsi
 */
@ManagedBean(name = "porteEmpresaViewBean")
@RequestScoped
public class PorteEmpresaViewBean {

    private List<PorteEmpresa> porteEmpresas = new ArrayList<>();
    private String valorBusca;
    private boolean exibe = false;

    public String excluirPorteEmpresa(PorteEmpresa a) {

        DaoFactory daoFactory = new DaoFactory();
        daoFactory.getPorteEmpresaDao().del(a);
        String msg = "PorteEmpresa: " + a.getDescricao()+ " Excluído com sucesso!";
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null));
        return "porteEmpresaView?faces-redirect=true";
    }
    public String prepararAdicionarPorteEmpresa() {
        return "porteEmpresaIncluir?faces-redirect=true&id=-1";
    }
    
    public String prepararAlterarPorteEmpresa(PorteEmpresa porteEmpresa) {
        return "porteEmpresaIncluir?faces-redirect=true&id=" + porteEmpresa.getId();
    }
    
    public void buscarPorteEmpresa() {
        DaoFactory daoFactory = new DaoFactory();
        porteEmpresas.clear();
        porteEmpresas = daoFactory.getPorteEmpresaDao().JpqlLike(getValorBusca(), "nome");
        exibe = true;
    }
    
    public List<PorteEmpresa> buscarTodos() {
        DaoFactory daoFactory = new DaoFactory();
        porteEmpresas = daoFactory.getPorteEmpresaDao().listaAll();
        exibe = false;
        return porteEmpresas;
    }
    
    public List<PorteEmpresa> getPorteEmpresas() {
        if ((porteEmpresas.isEmpty()) && (exibe == false)) {
            DaoFactory daoFactory = new DaoFactory();
            porteEmpresas = daoFactory.getPorteEmpresaDao().listaAll();
        }
        return porteEmpresas;
    }
    
    public String getValorBusca() {
        return valorBusca;
    }
    
    public void setValorBusca(String valorBusca) {
        this.valorBusca = valorBusca;
    }
    
    public boolean isExibe() {
        return exibe;
    }
}
