package util;

import dominio.EstadoCivil;
import java.text.ParseException;
import javax.persistence.EntityManager;

public class InsereEstadoCivil {

    public static void main(String[] args) throws ParseException {

        EstadoCivil estadoCivil = new EstadoCivil();
        
        //estadoCivil.setDescricao("Casado(a)");
        //estadoCivil.setDescricao("Divorciado(a)");
        //estadoCivil.setDescricao("Separado(a)");
        //estadoCivil.setDescricao("Solteiro(a)");
        estadoCivil.setDescricao("Viúvo(a)");
        
        EntityManager em = JpaUtil.getEntityManager();
        
        
        try {
            
            em.getTransaction().begin();

            em.persist(estadoCivil);

            em.getTransaction().commit();

        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}