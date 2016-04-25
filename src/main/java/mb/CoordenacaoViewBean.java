/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;

import dao.DaoFactory;
import dominio.Coordenacao;
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
@ManagedBean(name = "coordenacaoViewBean")
@RequestScoped
public class CoordenacaoViewBean {

    private List<Coordenacao> coordenacoes = new ArrayList<>();
    private String valorBusca;
    private boolean exibe = false;

    public String excluirCoordenacao(Coordenacao a) {

        DaoFactory daoFactory = new DaoFactory();
        daoFactory.getCoordenacaoDao().del(a);
        String msg = "Coordenacao " + a.getDescricao()+ " excluída com sucesso!";
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null));
        return "coordenacaoView?faces-redirect=true";
    }
    public String prepararAdicionarCoordenacao() {
        return "coordenacaoIncluir?faces-redirect=true&id=-1";
    }
    
    public String prepararAlterarCoordenacao(Coordenacao coordenacao) {
        return "coordenacaoIncluir?faces-redirect=true&id=" + coordenacao.getId();
    }
    
    public void buscarCoordenacao() {
        DaoFactory daoFactory = new DaoFactory();
        coordenacoes.clear();
        coordenacoes = daoFactory.getCoordenacaoDao().JpqlLike(getValorBusca(), "nome");
        exibe = true;
    }
    
    public List<Coordenacao> buscarTodos() {
        DaoFactory daoFactory = new DaoFactory();
        coordenacoes = daoFactory.getCoordenacaoDao().listaAll();
        exibe = false;
        return coordenacoes;
    }
    
    public List<Coordenacao> getCoordenacaos() {
        if ((coordenacoes.isEmpty()) && (exibe == false)) {
            DaoFactory daoFactory = new DaoFactory();
            coordenacoes = daoFactory.getCoordenacaoDao().listaAll();
        }
        return coordenacoes;
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
