package util;

import dominio.Sexo;
import java.text.ParseException;
import javax.persistence.EntityManager;

public class InsereSexo {

    public static void main(String[] args) throws ParseException {

        Sexo tipoSexo = new Sexo();
        
        //tipoSexo.setDescricao("Feminino");
        tipoSexo.setDescricao("Masculino");
        
        EntityManager em = JpaUtil.getEntityManager();
        
        
        try {
            
            em.getTransaction().begin();

            em.persist(tipoSexo);

            em.getTransaction().commit();

        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}