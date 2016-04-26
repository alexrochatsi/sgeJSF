package util;

import dominio.Aluno;
import dominio.Estagiario;
import dominio.Estagio;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.persistence.EntityManager;

public class InsereEstagiario {

    public static void main(String[] args) throws ParseException {

        Estagiario estagiario = new Estagiario();
        
        EntityManager em = JpaUtil.getEntityManager();
        
        estagiario.setPossuiCNH(Boolean.TRUE);
        estagiario.setCnh("531331553");
        estagiario.setCategoriaCNH("AB");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date = sdf.parse("30/05/2014");
        estagiario.setDataEmissaoCNH(date);
        date = sdf.parse("30/05/2018");
        estagiario.setDataVencimentoCNH(date);
        estagiario.setAluno(em.find(Aluno.class,3));
        
        ArrayList estagios = new ArrayList();
        estagios.add(em.find(Estagio.class,3));
        estagiario.setEstagios(estagios);
        
        try {
            em.getTransaction().begin();
            em.persist(estagiario);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}