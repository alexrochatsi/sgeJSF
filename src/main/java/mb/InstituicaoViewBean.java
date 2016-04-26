/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;

import dao.DaoFactory;
import dominio.Instituicao;
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
@ManagedBean(name = "instituicaoViewBean")
@RequestScoped
public class InstituicaoViewBean {

    private List<Instituicao> instituicaos = new ArrayList<>();
    private String valorBusca;
    private boolean exibe = false;

    public String excluirInstituicao(Instituicao a) {

        DaoFactory daoFactory = new DaoFactory();
        daoFactory.getInstituicaoDao().del(a);
        String msg = "Instituicao: " + a.getNome()+ " Excluída com sucesso!";
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null));
        return "instituicaoView?faces-redirect=true";
    }
    public String prepararAdicionarInstituicao() {
        return "instituicaoIncluir?faces-redirect=true&id=-1";
    }
    
    public String prepararAlterarInstituicao(Instituicao instituicao) {
        return "instituicaoIncluir?faces-redirect=true&id=" + instituicao.getId();
    }
    
    public void buscarInstituicao() {
        DaoFactory daoFactory = new DaoFactory();
        instituicaos.clear();
        instituicaos = daoFactory.getInstituicaoDao().JpqlLike(getValorBusca(), "nome");
        exibe = true;
    }
    
    public List<Instituicao> buscarTodos() {
        DaoFactory daoFactory = new DaoFactory();
        instituicaos = daoFactory.getInstituicaoDao().listaAll();
        exibe = false;
        return instituicaos;
    }
    
    public List<Instituicao> getInstituicaos() {
        if ((instituicaos.isEmpty()) && (exibe == false)) {
            DaoFactory daoFactory = new DaoFactory();
            instituicaos = daoFactory.getInstituicaoDao().listaAll();
        }
        return instituicaos;
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
