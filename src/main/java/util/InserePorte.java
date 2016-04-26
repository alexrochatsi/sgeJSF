package util;

import dominio.PorteEmpresa;
import java.text.ParseException;
import javax.persistence.EntityManager;

public class InserePorte {

    public static void main(String[] args) throws ParseException {

        PorteEmpresa tipoPorte = new PorteEmpresa();
        
        //tipoPorte.setDescricao("Pequena");
        //tipoPorte.setDescricao("Média");
        tipoPorte.setDescricao("Grande");
        
        EntityManager em = JpaUtil.getEntityManager();
        
        
        try {
            
            em.getTransaction().begin();

            em.persist(tipoPorte);

            em.getTransaction().commit();

        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}