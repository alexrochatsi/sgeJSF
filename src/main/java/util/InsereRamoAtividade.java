package util;

import dominio.RamoAtividade;
import java.text.ParseException;
import javax.persistence.EntityManager;

public class InsereRamoAtividade {

    public static void main(String[] args) throws ParseException {

        RamoAtividade ramoAtividade = new RamoAtividade();
        
        //ramoAtividade.setDescricao("Engenharia");
        //ramoAtividade.setDescricao("Educação");
        //ramoAtividade.setDescricao("Comérccio");
        ramoAtividade.setDescricao("Design");

        
        EntityManager em = JpaUtil.getEntityManager();
        
        
        try {
            
            em.getTransaction().begin();

            em.persist(ramoAtividade);

            em.getTransaction().commit();

        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}