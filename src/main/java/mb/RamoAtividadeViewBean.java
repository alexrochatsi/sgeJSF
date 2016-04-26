/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;

import dao.DaoFactory;
import dominio.RamoAtividade;
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
@ManagedBean(name = "ramoAtividadeViewBean")
@RequestScoped
public class RamoAtividadeViewBean {

    private List<RamoAtividade> ramoAtividades = new ArrayList<>();
    private String valorBusca;
    private boolean exibe = false;

    public String excluirRamoAtividade(RamoAtividade a) {

        DaoFactory daoFactory = new DaoFactory();
        daoFactory.getRamoAtividadeDao().del(a);
        String msg = "RamoAtividade: " + a.getDescricao()+ " Excluído com sucesso!";
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null));
        return "ramoAtividadeView?faces-redirect=true";
    }
    public String prepararAdicionarRamoAtividade() {
        return "ramoAtividadeIncluir?faces-redirect=true&id=-1";
    }
    
    public String prepararAlterarRamoAtividade(RamoAtividade ramoAtividade) {
        return "ramoAtividadeIncluir?faces-redirect=true&id=" + ramoAtividade.getId();
    }
    
    public void buscarRamoAtividade() {
        DaoFactory daoFactory = new DaoFactory();
        ramoAtividades.clear();
        ramoAtividades = daoFactory.getRamoAtividadeDao().JpqlLike(getValorBusca(), "nome");
        exibe = true;
    }
    
    public List<RamoAtividade> buscarTodos() {
        DaoFactory daoFactory = new DaoFactory();
        ramoAtividades = daoFactory.getRamoAtividadeDao().listaAll();
        exibe = false;
        return ramoAtividades;
    }
    
    public List<RamoAtividade> getRamoAtividades() {
        if ((ramoAtividades.isEmpty()) && (exibe == false)) {
            DaoFactory daoFactory = new DaoFactory();
            ramoAtividades = daoFactory.getRamoAtividadeDao().listaAll();
        }
        return ramoAtividades;
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
