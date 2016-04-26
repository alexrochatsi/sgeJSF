/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;

import dao.DaoFactory;
import dominio.Turno;
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
@ManagedBean(name = "turnoViewBean")
@RequestScoped
public class TurnoViewBean {

    private List<Turno> turnos = new ArrayList<>();
    private String valorBusca;
    private boolean exibe = false;

    public String excluirTurno(Turno a) {

        DaoFactory daoFactory = new DaoFactory();
        daoFactory.getTurnoDao().del(a);
        String msg = "Turno: " + a.getDescricao()+ " Excluído com sucesso!";
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null));
        return "turnoView?faces-redirect=true";
    }
    public String prepararAdicionarTurno() {
        return "turnoIncluir?faces-redirect=true&id=-1";
    }
    
    public String prepararAlterarTurno(Turno turno) {
        return "turnoIncluir?faces-redirect=true&id=" + turno.getId();
    }
    
    public void buscarTurno() {
        DaoFactory daoFactory = new DaoFactory();
        turnos.clear();
        turnos = daoFactory.getTurnoDao().JpqlLike(getValorBusca(), "nome");
        exibe = true;
    }
    
    public List<Turno> buscarTodos() {
        DaoFactory daoFactory = new DaoFactory();
        turnos = daoFactory.getTurnoDao().listaAll();
        exibe = false;
        return turnos;
    }
    
    public List<Turno> getTurnos() {
        if ((turnos.isEmpty()) && (exibe == false)) {
            DaoFactory daoFactory = new DaoFactory();
            turnos = daoFactory.getTurnoDao().listaAll();
        }
        return turnos;
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
