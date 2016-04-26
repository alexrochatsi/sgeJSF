/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;

import dao.DaoFactory;
import dominio.RepresentanteEmpresa;
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
@ManagedBean(name = "representanteEmpresaViewBean")
@RequestScoped
public class RepresentanteEmpresaViewBean {

    private List<RepresentanteEmpresa> representanteEmpresas = new ArrayList<>();
    private String valorBusca;
    private boolean exibe = false;

    public String excluirRepresentanteEmpresa(RepresentanteEmpresa a) {

        DaoFactory daoFactory = new DaoFactory();
        daoFactory.getRepresentanteEmpresaDao().del(a);
        String msg = "RepresentanteEmpresa: " + a.getNome() + " Excluído com sucesso!";
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null));
        return "representanteEmpresaView?faces-redirect=true";
    }
    public String prepararAdicionarRepresentanteEmpresa() {
        return "representanteEmpresaIncluir?faces-redirect=true&id=-1";
    }
    
    public String prepararAlterarRepresentanteEmpresa(RepresentanteEmpresa representanteEmpresa) {
        return "representanteEmpresaIncluir?faces-redirect=true&id=" + representanteEmpresa.getId();
    }
    
    public void buscarRepresentanteEmpresa() {
        DaoFactory daoFactory = new DaoFactory();
        representanteEmpresas.clear();
        representanteEmpresas = daoFactory.getRepresentanteEmpresaDao().JpqlLike(getValorBusca(), "nome");
        exibe = true;
    }
    
    public List<RepresentanteEmpresa> buscarTodos() {
        DaoFactory daoFactory = new DaoFactory();
        representanteEmpresas = daoFactory.getRepresentanteEmpresaDao().listaAll();
        exibe = false;
        return representanteEmpresas;
    }
    
    public List<RepresentanteEmpresa> getRepresentanteEmpresas() {
        if ((representanteEmpresas.isEmpty()) && (exibe == false)) {
            DaoFactory daoFactory = new DaoFactory();
            representanteEmpresas = daoFactory.getRepresentanteEmpresaDao().listaAll();
        }
        return representanteEmpresas;
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
