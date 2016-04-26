package util;


import dominio.Endereco;
import dominio.Estado;
import dominio.Instituicao;
import dominio.Telefone;
import java.text.ParseException;
import javax.persistence.EntityManager;

public class InsereInstituicao {

    public static void main(String[] args) throws ParseException {

        Instituicao instituicao = new Instituicao();
        Telefone telefone = new Telefone();
        Endereco endereco = new Endereco();
        
        instituicao.setNome("Instituto Federal de Educação, Ciência e Tecnologia");
        
        telefone.setDdd("63");
        telefone.setNumero("3215-4845");
        
        EntityManager em = JpaUtil.getEntityManager();
        
        endereco.setCep("77000-000");
        endereco.setCidade("Palmas");
        endereco.setLogradouro("310 Sul, LO 05");
        endereco.setBairro("Plano Diretor Sul");
        endereco.setNumero("Nº 1");
        endereco.setEstado(em.find(Estado.class,27));
        
        instituicao.setEndereco(endereco);
        instituicao.setTelefone(telefone);
        
        
        try {
            
            em.getTransaction().begin();

            em.persist(instituicao);

            em.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}