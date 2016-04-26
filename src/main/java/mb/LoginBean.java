package mb;

import dao.DaoFactory;
import dominio.Login;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author alexrochatsi
 */
@ManagedBean(name = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {

    private Login cadastro = new Login();
    private Login login = new Login();
    private Login loginRetorno = new Login();
    private Integer id;

    public String loadModel() {
        System.out.println("ENTREI");
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        if (session.getAttribute("NIVEL_LOGIN") == null) {
            System.out.println("ERA NULO");
            return "index?faces-redirect=true";
        }
        System.out.println("Não é nulo");
        return "home?faces-redirect=true";
    }

    public String salvarAluno() {
        FacesContext fc = FacesContext.getCurrentInstance();
        DaoFactory daoFactory = new DaoFactory();
        cadastro.setNivelAcesso(1);
        if (getCadastro().getId() != null) {
            daoFactory.getLoginEspecialistaDao().upd(getCadastro());
            FacesMessage msg = new FacesMessage("Dados de " + getCadastro().getUsuario() + " atualizados com sucesso!");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
            //salvo novaente o objeto já com o ID salvo no banco
            setCadastro(daoFactory.getLoginEspecialistaDao().add(getCadastro()));
            HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
            //pego o Id que retornou e salvo na sessao para ser usado no cadastro do participante
            session.setAttribute("ID_LOGIN", getCadastro().getId());
            session.setAttribute("NIVEL_LOGIN", getCadastro().getNivelAcesso());
            FacesMessage msg = new FacesMessage("Login " + getCadastro().getUsuario() + " salvo com sucesso!");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        return "alunoIncluir?faces-redirect=true";
    }

    public String salvarEmpresa() {
        FacesContext fc = FacesContext.getCurrentInstance();
        DaoFactory daoFactory = new DaoFactory();
        cadastro.setNivelAcesso(2);
        if (getCadastro().getId() != null) {
            daoFactory.getLoginEspecialistaDao().upd(getCadastro());
            FacesMessage msg = new FacesMessage("Dados de " + getCadastro().getUsuario() + " atualizados com sucesso!");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
            //salvo novaente o objeto já com o ID salvo no banco
            setCadastro(daoFactory.getLoginEspecialistaDao().add(getCadastro()));
            HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
            //pego o Id que retornou e salvo na sessao para ser usado no cadastro do participante
            session.setAttribute("ID_LOGIN", getCadastro().getId());
            session.setAttribute("NIVEL_LOGIN", getCadastro().getNivelAcesso());
            FacesMessage msg = new FacesMessage("Login " + getCadastro().getUsuario() + " salvo com sucesso!");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        return "empresaIncluir?faces-redirect=true";
    }

    public String salvarProfOrientador() {
        FacesContext fc = FacesContext.getCurrentInstance();
        DaoFactory daoFactory = new DaoFactory();
        cadastro.setNivelAcesso(2);
        if (getCadastro().getId() != null) {
            daoFactory.getLoginEspecialistaDao().upd(getCadastro());
            FacesMessage msg = new FacesMessage("Dados de " + getCadastro().getUsuario() + " atualizados com sucesso!");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
            //salvo novaente o objeto já com o ID salvo no banco
            setCadastro(daoFactory.getLoginEspecialistaDao().add(getCadastro()));
            HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
            //pego o Id que retornou e salvo na sessao para ser usado no cadastro do participante
            session.setAttribute("ID_LOGIN", getCadastro().getId());
            session.setAttribute("NIVEL_LOGIN", getCadastro().getNivelAcesso());
            FacesMessage msg = new FacesMessage("Login " + getCadastro().getUsuario() + " salvo com sucesso!");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        return "profOrientadorIncluir?faces-redirect=true";
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        login = new Login();
        return "index?faces-redirect=true";
    }

    public String entrar() {
        DaoFactory daoFactory = new DaoFactory();
        FacesContext fc = FacesContext.getCurrentInstance();
        setLoginRetorno(daoFactory.getLoginEspecialistaDao().fazerLogin(getLogin()));
        System.out.println("ID_LOGIN: " + getLoginRetorno().getId());
        if (getLoginRetorno().getId() != null) {
            HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
            session.setAttribute("ID_LOGIN", getLoginRetorno().getId());
            session.setAttribute("NIVEL_LOGIN", getLoginRetorno().getNivelAcesso());
            System.out.println("ID_LOGIN: " + getLoginRetorno().getId());
            System.out.println("NIVEL_LOGIN: " + getLoginRetorno().getNivelAcesso());
            FacesMessage msg = new FacesMessage("Usuario " + getLogin().getUsuario() + " entrou com sucesso!");
            fc.addMessage(null, msg);
            fc.getExternalContext().getFlash().setKeepMessages(true);
            return "home?faces-redirect=true";
        } else {
            FacesMessage msg = new FacesMessage("Erro ao efetuar login!");
            fc.addMessage(null, msg);
            fc.getExternalContext().getFlash().setKeepMessages(true);
            return "login";
        }
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Login getLoginRetorno() {
        return loginRetorno;
    }

    public void setLoginRetorno(Login loginRetorno) {
        this.loginRetorno = loginRetorno;
    }

    public Login getCadastro() {
        return cadastro;
    }

    public void setCadastro(Login cadastro) {
        this.cadastro = cadastro;
    }
}
