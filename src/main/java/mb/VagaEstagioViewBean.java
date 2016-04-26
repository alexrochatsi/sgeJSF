/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;

import dao.DaoFactory;
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
@ManagedBean(name = "vagaEstagioViewBean")
@ViewScoped
public class VagaEstagioViewBean {

    private List<VagaEstagio> vagaEstagios = new ArrayList<>();
    private String valorBusca;
    private boolean exibe = false;
    private VagaEstagio vagaEstagioSelecionado;

    public String excluirVagaEstagio(VagaEstagio v) {

        DaoFactory daoFactory = new DaoFactory();
        daoFactory.getVagaEstagioDao().del(v);
        String msg = "VagaEstagio: " + v.getAtividade()+ " Excluído com sucesso!";
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null));
        return "vagaEstagioView?faces-redirect=true";
    }
    public String prepararAdicionarVagaEstagio() {
        return "vagaEstagioIncluir?faces-redirect=true&id=-1";
    }
    
    public String prepararAlterarVagaEstagio(VagaEstagio vagaEstagio) {
        return "vagaEstagioIncluir?faces-redirect=true&id=" + vagaEstagio.getId();
    }
    
    public String vagasEstagioAbertas() {
        return "vagaEstagioView?faces-redirect=true";
    }
    
    public void buscarVagaEstagio() {
        DaoFactory daoFactory = new DaoFactory();
        vagaEstagios.clear();
        vagaEstagios = daoFactory.getVagaEstagioDao().JpqlLike(getValorBusca(), "nome");
        exibe = true;
    }
    
    public List<VagaEstagio> buscarTodasAbertas() {
        DaoFactory daoFactory = new DaoFactory();
        vagaEstagios = daoFactory.getVagaEstagioEspecialistaDao().getVagasAprovadas();
        exibe = false;
        return vagaEstagios;
    }
    
    public List<VagaEstagio> getVagasEstagiosAbertas() {
        if ((vagaEstagios.isEmpty()) && (exibe == false)) {
            DaoFactory daoFactory = new DaoFactory();
            vagaEstagios = daoFactory.getVagaEstagioEspecialistaDao().getVagasAprovadas();
        }
        return vagaEstagios;
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

    public VagaEstagio getVagaEstagioSelecionado() {
        return vagaEstagioSelecionado;
    }

    public void setVagaEstagioSelecionado(VagaEstagio vagaEstagioSelecionado) {
        this.vagaEstagioSelecionado = vagaEstagioSelecionado;
    }
}
