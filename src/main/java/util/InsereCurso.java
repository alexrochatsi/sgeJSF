package util;

import dominio.Coordenacao;
import dominio.Curso;
import dominio.Turno;
import java.text.ParseException;
import javax.persistence.EntityManager;

public class InsereCurso {

    public static void main(String[] args) throws ParseException {

        Curso curso = new Curso();

        EntityManager em = JpaUtil.getEntityManager();

        curso.setNome("Engenharia Civil");
        curso.setConteudoProgramatico("Técnicas aplicadas a construção de edificios");

        curso.setCoordenacao(em.find(Coordenacao.class, 2));
        /*
        1;"Matutino"
        2;"Vespertino"
        3;"Noturno"
        4;"Integral"
         */
        curso.setTurno(em.find(Turno.class, 4));

        try {
            em.getTransaction().begin();
            em.persist(curso);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}
