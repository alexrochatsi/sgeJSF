package dao;

import dominio.Aluno;
import dominio.Coordenacao;
import dominio.Coordenador;
import dominio.Curso;
import dominio.Empresa;
import dominio.Estado;
import dominio.EstadoCivil;
import dominio.Estagiario;
import dominio.Estagio;
import dominio.Instituicao;
import dominio.Login;
import dominio.PorteEmpresa;
import dominio.Professor;
import dominio.RamoAtividade;
import dominio.RepresentanteEmpresa;
import dominio.Sexo;
import dominio.Supervisor;
import dominio.Telefone;
import dominio.TipoEstagio;
import dominio.Turno;
import dominio.VagaEstagio;

public class DaoFactory {
    
    //fabrica de dao's genericos
    public DaoGenerico<Aluno> getAlunoDao() {
        return new DaoGenerico<>(Aluno.class);
    }
    
    public DaoGenerico<Coordenacao> getCoordenacaoDao() {
        return new DaoGenerico<>(Coordenacao.class);
    }
    
    public DaoGenerico<Coordenador> getCoordenadorDao() {
        return new DaoGenerico<>(Coordenador.class);
    }
    
    public DaoGenerico<Curso> getCursoDao() {
        return new DaoGenerico<>(Curso.class);
    }
    
    public DaoGenerico<Empresa> getEmpresaDao() {
        return new DaoGenerico<>(Empresa.class);
    }
    
    public DaoGenerico<EstadoCivil> getEstadoCivilDao() {
        return new DaoGenerico<>(EstadoCivil.class);
    }
    
    public DaoGenerico<Estado> getEstadoDao() {
        return new DaoGenerico<>(Estado.class);
    }
    
    public DaoGenerico<Estagiario> getEstagiarioDao() {
        return new DaoGenerico<>(Estagiario.class);
    }
    
    public DaoGenerico<Estagio> getEstagioDao() {
        return new DaoGenerico<>(Estagio.class);
    }
    
    public DaoGenerico<Instituicao> getInstituicaoDao() {
        return new DaoGenerico<>(Instituicao.class);
    }
    
    public DaoGenerico<Login> getLoginDao() {
        return new DaoGenerico<>(Login.class);
    }
    
    public DaoGenerico<PorteEmpresa> getPorteEmpresaDao() {
        return new DaoGenerico<>(PorteEmpresa.class);
    }
    
    public DaoGenerico<Professor> getProfessorDao() {
        return new DaoGenerico<>(Professor.class);
    }
    
    public DaoGenerico<RamoAtividade> getRamoAtividadeDao() {
        return new DaoGenerico<>(RamoAtividade.class);
    }
    
    public DaoGenerico<RepresentanteEmpresa> getRepresentanteEmpresaDao() {
        return new DaoGenerico<>(RepresentanteEmpresa.class);
    }
    
    public DaoGenerico<Sexo> getSexoDao() {
        return new DaoGenerico<>(Sexo.class);
    }
    
    public DaoGenerico<Supervisor> getSupervisorDao() {
        return new DaoGenerico<>(Supervisor.class);
    }
    
    public DaoGenerico<Telefone> getTelefoneDao() {
        return new DaoGenerico<>(Telefone.class);
    }
    
    public DaoGenerico<TipoEstagio> getTipoEstagioDao() {
        return new DaoGenerico<>(TipoEstagio.class);
    }
    
    public DaoGenerico<Turno> getTurnoDao() {
        return new DaoGenerico<>(Turno.class);
    }
    
    public DaoGenerico<VagaEstagio> getVagaEstagioDao() {
        return new DaoGenerico<>(VagaEstagio.class);
    }

    //Dao especializados 
    
    public VagaEstagioDao getVagaEstagioEspecialistaDao() {
        return new VagaEstagioDao();
    }
    
    public EmpresaDao getEmpresaEspecialistaDao() {
        return new EmpresaDao();
    }
    
    public LoginDao getLoginEspecialistaDao() {
        return new LoginDao();
    }

   /*public ProjetoDao getProjetoEspecialistaDao() {
        return new ProjetoDao();
    }
   
   public BancadaDao getBancadaEspecialistaDao() {
        return new BancadaDao();
    }
   
   public CursoDao getCursoEspecialistaDao() {
        return new CursoDao(); 
    }*/
}
