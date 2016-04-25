package util;

import dominio.TipoEstagio;
import java.text.ParseException;
import javax.persistence.EntityManager;

public class InsereTipoEstagio {

    public static void main(String[] args) throws ParseException {

        TipoEstagio tipoEstagio = new TipoEstagio();
        
        //tipoEstagio.setDescricao("Estágio Curricular");
        //tipoEstagio.setDescricao("Estágio Extra-Curricular");
        //tipoEstagio.setDescricao("Estágio Profissional");
        tipoEstagio.setDescricao("Estágio de Férias");
        
        EntityManager em = JpaUtil.getEntityManager();
        
        
        try {
            
            em.getTransaction().begin();

            em.persist(tipoEstagio);

            em.getTransaction().commit();

        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}