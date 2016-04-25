/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;

import dao.DaoFactory;
import dominio.Coordenacao;
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
@ManagedBean(name = "coordenacaoBean")
@RequestScoped
public class CoordenacaoBean {

    private Coordenacao coordenacao = new Coordenacao();
    private List<Instituicao> instituicoes = new ArrayList<>();
    private Integer id;

    public void loadModel() {

        DaoFactory daoFactory = new DaoFactory();

        if (getId() != null && getId() != -1) {
            coordenacao = (Coordenacao) daoFactory.getCoordenacaoDao().findById(getId());
        } else {
            coordenacao = new Coordenacao();
        }
    }

    public String cancel() {
        return "coordenacaoView?faces-redirect=true";
    }

    public String salvar() {

        DaoFactory daoFactory = new DaoFactory();
        if (coordenacao.getId() != null) {
            daoFactory.getCoordenacaoDao().upd(coordenacao);
        } else {
            daoFactory.getCoordenacaoDao().add(coordenacao);
        }
        String msg = "Coordenacao " + coordenacao.getDescricao()+ " salva com sucesso!";
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null));
        return "coordenacaoView?faces-redirect=true";
    }

    public Coordenacao getCoordenacao() {
        return coordenacao;
    }

    public void setCoordenacao(Coordenacao coordenacao) {
        this.coordenacao = coordenacao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the instituicoes
     */
    public List<Instituicao> getInstituicoes() {
        if (instituicoes.isEmpty()) {
            DaoFactory daoFactory = new DaoFactory();
            setInstituicoes(daoFactory.getInstituicaoDao().listaAll());
        }
        return instituicoes;
    }

    /**
     * @param instituicoes the instituicoes to set
     */
    public void setInstituicoes(List<Instituicao> instituicoes) {
        this.instituicoes = instituicoes;
    }
}
