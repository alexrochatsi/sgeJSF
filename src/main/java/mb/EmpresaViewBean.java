/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;

import dao.DaoFactory;
import dominio.Empresa;
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
@ManagedBean(name = "empresaViewBean")
@RequestScoped
public class EmpresaViewBean {

    private List<Empresa> empresas = new ArrayList<>();
    private String valorBusca;
    private boolean exibe = false;

    public String excluirEmpresa(Empresa a) {

        DaoFactory daoFactory = new DaoFactory();
        daoFactory.getEmpresaDao().del(a);
        String msg = "Empresa: " + a.getNomeFantasia()+ " Excluído com sucesso!";
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null));
        return "empresaView?faces-redirect=true";
    }
    public String prepararAdicionarEmpresa() {
        return "empresaIncluir?faces-redirect=true&id=-1";
    }
    
    public String prepararAlterarEmpresa(Empresa empresa) {
        return "empresaIncluir?faces-redirect=true&id=" + empresa.getId();
    }
    
    public void buscarEmpresa() {
        DaoFactory daoFactory = new DaoFactory();
        empresas.clear();
        empresas = daoFactory.getEmpresaDao().JpqlLike(getValorBusca(), "nome");
        exibe = true;
    }
    
    public List<Empresa> buscarTodos() {
        DaoFactory daoFactory = new DaoFactory();
        empresas = daoFactory.getEmpresaDao().listaAll();
        exibe = false;
        return empresas;
    }
    
    public List<Empresa> getEmpresas() {
        if ((empresas.isEmpty()) && (exibe == false)) {
            DaoFactory daoFactory = new DaoFactory();
            empresas = daoFactory.getEmpresaDao().listaAll();
        }
        return empresas;
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
