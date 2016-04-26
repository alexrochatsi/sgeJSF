/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;

import dao.DaoFactory;
import dominio.Estagio;
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
@ManagedBean(name = "estagioViewBean")
@RequestScoped
public class EstagioViewBean {

    private List<Estagio> estagios = new ArrayList<>();
    private String valorBusca;
    private boolean exibe = false;

    public String excluirEstagio(Estagio a) {

        DaoFactory daoFactory = new DaoFactory();
        daoFactory.getEstagioDao().del(a);
        String msg = "Estagio: " + a.getAtividade() + " Excluído com sucesso!";
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null));
        return "estagioView?faces-redirect=true";
    }
    public String prepararAdicionarEstagio() {
        return "estagioIncluir?faces-redirect=true&id=-1";
    }
    
    public String prepararAlterarEstagio(Estagio estagio) {
        return "estagioIncluir?faces-redirect=true&id=" + estagio.getId();
    }
    
    public void buscarEstagio() {
        DaoFactory daoFactory = new DaoFactory();
        estagios.clear();
        estagios = daoFactory.getEstagioDao().JpqlLike(getValorBusca(), "nome");
        exibe = true;
    }
    
    public List<Estagio> buscarTodos() {
        DaoFactory daoFactory = new DaoFactory();
        estagios = daoFactory.getEstagioDao().listaAll();
        exibe = false;
        return estagios;
    }
    
    public List<Estagio> getEstagios() {
        if ((estagios.isEmpty()) && (exibe == false)) {
            DaoFactory daoFactory = new DaoFactory();
            estagios = daoFactory.getEstagioDao().listaAll();
        }
        return estagios;
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
