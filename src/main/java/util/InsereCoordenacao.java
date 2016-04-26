package util;

import dominio.Coordenacao;
import dominio.Instituicao;
import dominio.Telefone;
import java.text.ParseException;
import javax.persistence.EntityManager;

public class InsereCoordenacao {

    public static void main(String[] args) throws ParseException {

        Coordenacao coordenacao = new Coordenacao();
        Telefone telefone = new Telefone();
        
        EntityManager em = JpaUtil.getEntityManager();
        
        coordenacao.setDescricao("Coordenação de Indústria");
        
        coordenacao.setInstituicao(em.find(Instituicao.class,1));
        
        telefone.setDdd("63");
        telefone.setNumero("3215-6753");
        
        coordenacao.setTelefone(telefone);
        
        try {      
            em.getTransaction().begin();
            em.persist(coordenacao);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}