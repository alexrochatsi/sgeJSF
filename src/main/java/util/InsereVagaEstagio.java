package util;

import dominio.Empresa;
import dominio.Turno;
import dominio.VagaEstagio;
import java.text.ParseException;
import javax.persistence.EntityManager;

public class InsereVagaEstagio {

    public static void main(String[] args) throws ParseException {

        VagaEstagio vagaEstagio = new VagaEstagio();
        
        vagaEstagio.setAtividade("Teste");
        vagaEstagio.setDescricao("Teste");
        vagaEstagio.setValorBolsa(600);
        vagaEstagio.setStatusAprovacao(Boolean.TRUE);
        
        EntityManager em = JpaUtil.getEntityManager();
        
        vagaEstagio.setTurnoTrabalho(em.find(Turno.class,4));
        
        vagaEstagio.setEmpresa(em.find(Empresa.class, 1));
        
        try {
            
            em.getTransaction().begin();

            em.persist(vagaEstagio);

            em.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}