/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;

import dao.DaoFactory;
import dominio.Telefone;
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
@ManagedBean(name = "telefoneViewBean")
@RequestScoped
public class TelefoneViewBean {

    private List<Telefone> telefones = new ArrayList<>();
    private String valorBusca;
    private boolean exibe = false;

    public String excluirTelefone(Telefone a) {

        DaoFactory daoFactory = new DaoFactory();
        daoFactory.getTelefoneDao().del(a);
        String msg = "Telefone: " + a.getNumero()+ " Excluído com sucesso!";
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null));
        return "telefoneView?faces-redirect=true";
    }
    public String prepararAdicionarTelefone() {
        return "telefoneIncluir?faces-redirect=true&id=-1";
    }
    
    public String prepararAlterarTelefone(Telefone telefone) {
        return "telefoneIncluir?faces-redirect=true&id=" + telefone.getId();
    }
    
    public void buscarTelefone() {
        DaoFactory daoFactory = new DaoFactory();
        telefones.clear();
        telefones = daoFactory.getTelefoneDao().JpqlLike(getValorBusca(), "nome");
        exibe = true;
    }
    
    public List<Telefone> buscarTodos() {
        DaoFactory daoFactory = new DaoFactory();
        telefones = daoFactory.getTelefoneDao().listaAll();
        exibe = false;
        return telefones;
    }
    
    public List<Telefone> getTelefones() {
        if ((telefones.isEmpty()) && (exibe == false)) {
            DaoFactory daoFactory = new DaoFactory();
            telefones = daoFactory.getTelefoneDao().listaAll();
        }
        return telefones;
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
