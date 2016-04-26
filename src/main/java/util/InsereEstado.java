package util;

import dominio.Estado;
import dominio.PorteEmpresa;
import java.text.ParseException;
import javax.persistence.EntityManager;

public class InsereEstado {

    public static void main(String[] args) throws ParseException {

        Estado estado = new Estado();
        
        //estado.setDescricao("Acre");
        //estado.setDescricao("Alagoas");
        //estado.setDescricao("Amapá");
        //estado.setDescricao("Amazonas");
        //estado.setDescricao("Bahia");
        //estado.setDescricao("Ceará");
        //estado.setDescricao("Distrito Federal");
        //estado.setDescricao("Espírito Santo");
        //estado.setDescricao("Goiás");
        //estado.setDescricao("Maranhão");
        //estado.setDescricao("Mato Grosso");
        //estado.setDescricao("Mato Grosso do Sul");
        //estado.setDescricao("Minas Gerais");
        //estado.setDescricao("Pará");
        //estado.setDescricao("Paraíba");
        //estado.setDescricao("Paraná");
        //estado.setDescricao("Pernambuco");
        //estado.setDescricao("Piauí");
        //estado.setDescricao("Rio de Janeiro");
        //estado.setDescricao("Rio Grande do Norte");
        //estado.setDescricao("Rio Grande do Sul");
        //estado.setDescricao("Rondônia");
        //estado.setDescricao("Roraima");
        //estado.setDescricao("Santa Catarina");
        //estado.setDescricao("São Paulo");
        //estado.setDescricao("Sergipe");
        //estado.setDescricao("Tocantins");
        
        EntityManager em = JpaUtil.getEntityManager();
        
        
        try {
            
            em.getTransaction().begin();

            em.persist(estado);

            em.getTransaction().commit();

        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}