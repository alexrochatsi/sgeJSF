package util;

import dominio.Curso;
import dominio.Endereco;
import dominio.Estado;
import dominio.Professor;
import dominio.Telefone;
import dominio.EstadoCivil;
import dominio.Sexo;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.persistence.EntityManager;

public class InsereProfessor {

    public static void main(String[] args) throws ParseException {

        Professor professor = new Professor();
        Endereco endereco = new Endereco();
        Telefone telefone = new Telefone();
        
        EntityManager em = JpaUtil.getEntityManager();
        
        professor.setNome("Cláudio Monteiro");
        professor.setNacionalidade("Brasil");
        professor.setSexo(em.find(Sexo.class, 2));
        professor.setEstadoCivil(em.find(EstadoCivil.class, 1));
        professor.setEmail("ccm@gmail.com");
        professor.setCpf("875.235.097-36");
        professor.setMatricula("8754468");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date = sdf.parse("14/10/1970");
        professor.setDataNascimento(date);
        
        ArrayList cursos = new ArrayList();
        cursos.add(em.find(Curso.class,1));
        cursos.add(em.find(Curso.class,2));
        professor.setCursos(cursos);
        
        telefone.setDdd("63");
        telefone.setNumero("87643-1365");
        
        endereco.setCep("77020-162");
        endereco.setCidade("Palmas");
        endereco.setLogradouro("TO-050");
        endereco.setBairro("Taquaralto");
        endereco.setNumero("LT 64");
        endereco.setEstado(em.find(Estado.class,27));
        
        professor.setEndereco(endereco);
        professor.setTelefone(telefone);
        
        try {    
            em.getTransaction().begin();
            em.persist(professor);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}