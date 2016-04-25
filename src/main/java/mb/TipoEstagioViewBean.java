/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;

import dao.DaoFactory;
import dominio.TipoEstagio;
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
@ManagedBean(name = "tipoEstagioViewBean")
@RequestScoped
public class TipoEstagioViewBean {

    private List<TipoEstagio> tipoEstagios = new ArrayList<>();
    private String valorBusca;
    private boolean exibe = false;

    public String excluirTipoEstagio(TipoEstagio a) {

        DaoFactory daoFactory = new DaoFactory();
        daoFactory.getTipoEstagioDao().del(a);
        String msg = "TipoEstagio: " + a.getDescricao()+ " Excluído com sucesso!";
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null));
        return "tipoEstagioView?faces-redirect=true";
    }
    public String prepararAdicionarTipoEstagio() {
        return "tipoEstagioIncluir?faces-redirect=true&id=-1";
    }
    
    public String prepararAlterarTipoEstagio(TipoEstagio tipoEstagio) {
        return "tipoEstagioIncluir?faces-redirect=true&id=" + tipoEstagio.getId();
    }
    
    public void buscarTipoEstagio() {
        DaoFactory daoFactory = new DaoFactory();
        tipoEstagios.clear();
        tipoEstagios = daoFactory.getTipoEstagioDao().JpqlLike(getValorBusca(), "nome");
        exibe = true;
    }
    
    public List<TipoEstagio> buscarTodos() {
        DaoFactory daoFactory = new DaoFactory();
        tipoEstagios = daoFactory.getTipoEstagioDao().listaAll();
        exibe = false;
        return tipoEstagios;
    }
    
    public List<TipoEstagio> getTipoEstagios() {
        if ((tipoEstagios.isEmpty()) && (exibe == false)) {
            DaoFactory daoFactory = new DaoFactory();
            tipoEstagios = daoFactory.getTipoEstagioDao().listaAll();
        }
        return tipoEstagios;
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
