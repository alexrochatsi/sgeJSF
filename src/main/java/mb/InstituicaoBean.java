/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;

import dao.DaoFactory;
import dominio.Estado;
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
@ManagedBean(name = "instituicaoBean")
@RequestScoped
public class InstituicaoBean {

    private Instituicao instituicao = new Instituicao();
    private List<Estado> estados = new ArrayList<>();
    private Integer id;

    public void loadModel() {

        DaoFactory daoFactory = new DaoFactory();

        if (getId() != null && getId() != -1) {
            instituicao = (Instituicao) daoFactory.getInstituicaoDao().findById(getId());
        } else {
            instituicao = new Instituicao();
        }
    }

    public String cancel() {
        return "instituicaoView?faces-redirect=true";
    }

    public String salvar() {

        DaoFactory daoFactory = new DaoFactory();
        if (instituicao.getId() != null) {
            daoFactory.getInstituicaoDao().upd(instituicao);
        } else {
            daoFactory.getInstituicaoDao().add(instituicao);
        }
        String msg = "Instituicao: " + instituicao.getNome()+ " Salva com sucesso!";
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null));
        return "instituicaoView?faces-redirect=true";
    }

    public Instituicao getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(Instituicao instituicao) {
        this.instituicao = instituicao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the estados
     */
    public List<Estado> getEstados() {
        if (estados.isEmpty()) {
            DaoFactory daoFactory = new DaoFactory();
            setEstados(daoFactory.getEstadoDao().listaAll());
        }
        return estados;
    }

    /**
     * @param estados the estados to set
     */
    public void setEstados(List<Estado> estados) {
        this.estados = estados;
    }
}
