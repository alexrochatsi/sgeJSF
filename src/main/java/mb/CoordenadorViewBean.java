/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;

import dao.DaoFactory;
import dominio.Coordenador;
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
@ManagedBean(name = "coordenadorViewBean")
@RequestScoped
public class CoordenadorViewBean {

    private List<Coordenador> coordenadores = new ArrayList<>();
    private String valorBusca;
    private boolean exibe = false;

    public String excluirCoordenador(Coordenador a) {

        DaoFactory daoFactory = new DaoFactory();
        daoFactory.getCoordenadorDao().del(a);
        String msg = "Coordenador: " + a.getNome() + " Excluído com sucesso!";
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null));
        return "coordenadorView?faces-redirect=true";
    }
    public String prepararAdicionarCoordenador() {
        return "coordenadorIncluir?faces-redirect=true&id=-1";
    }
    
    public String prepararAlterarCoordenador(Coordenador coordenador) {
        return "coordenadorIncluir?faces-redirect=true&id=" + coordenador.getId();
    }
    
    public void buscarCoordenador() {
        DaoFactory daoFactory = new DaoFactory();
        coordenadores.clear();
        coordenadores = daoFactory.getCoordenadorDao().JpqlLike(getValorBusca(), "nome");
        exibe = true;
    }
    
    public List<Coordenador> buscarTodos() {
        DaoFactory daoFactory = new DaoFactory();
        coordenadores = daoFactory.getCoordenadorDao().listaAll();
        exibe = false;
        return coordenadores;
    }
    
    public List<Coordenador> getCoordenadors() {
        if ((coordenadores.isEmpty()) && (exibe == false)) {
            DaoFactory daoFactory = new DaoFactory();
            coordenadores = daoFactory.getCoordenadorDao().listaAll();
        }
        return coordenadores;
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
