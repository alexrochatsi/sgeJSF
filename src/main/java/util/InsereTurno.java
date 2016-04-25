package util;

import dominio.Turno;
import java.text.ParseException;
import javax.persistence.EntityManager;

public class InsereTurno {

    public static void main(String[] args) throws ParseException {

        Turno turno = new Turno();
        //turno.setDescricao("Matutino");
        //turno.setDescricao("Vespertino");
        //turno.setDescricao("Noturno");
        turno.setDescricao("Integral");
        
        EntityManager em = JpaUtil.getEntityManager();
        
        
        try {
            
            em.getTransaction().begin();

            em.persist(turno);

            em.getTransaction().commit();

        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}