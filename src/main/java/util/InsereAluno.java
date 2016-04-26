package util;

import dominio.Aluno;
import dominio.Curso;
import dominio.Endereco;
import dominio.Estado;
import dominio.Telefone;
import dominio.EstadoCivil;
import dominio.Sexo;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.EntityManager;

public class InsereAluno {

    public static void main(String[] args) throws ParseException {

        //setando dados do aluno
        Aluno aluno = new Aluno();
        Endereco endereco = new Endereco();
        Telefone telefone = new Telefone();
        
        EntityManager em = JpaUtil.getEntityManager();
        
        aluno.setNome("Alesson Rocha");
        aluno.setNaturalidade("Barra da Estiva");
        aluno.setNacionalidade("Brasil");
        aluno.setSexo(em.find(Sexo.class,2));
        aluno.setEstadoCivil(em.find(EstadoCivil.class,4));
        aluno.setFiliacaoPai("Bráulio Carvalho da Rocha");
        aluno.setFiliacaoMae("Raquel da Silva Rocha");
        aluno.setPortadorDeficiencia("Não");
        aluno.setEmail("alesson.rocha@gmail.com");
        aluno.setCpf("146.123.627.46");
        aluno.setRg("1558848");
        aluno.setMatricula("756734");
        aluno.setModuloAtual(3);
        aluno.setAnoEntrada("2012/01");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date = sdf.parse("18/12/1994");
        aluno.setDataNascimento(date);
        
        aluno.setCurso(em.find(Curso.class,1));
        
        telefone.setDdd("63");
        telefone.setNumero("8412-4587");
        
        endereco.setCep("77021-072");
        endereco.setCidade("Palmas");
        endereco.setLogradouro("308 Sul, AL 05");
        endereco.setBairro("Plano Diretor Sul");
        endereco.setNumero("10");
        endereco.setEstado(em.find(Estado.class,27));
        aluno.setEndereco(endereco);
        aluno.setTelefone(telefone);
        
        try {
            
            em.getTransaction().begin();

            em.persist(aluno);

            em.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}