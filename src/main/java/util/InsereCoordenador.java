package util;

import dominio.Coordenacao;
import dominio.Coordenador;
import dominio.Endereco;
import dominio.Estado;
import dominio.Telefone;
import dominio.EstadoCivil;
import dominio.Sexo;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.EntityManager;

public class InsereCoordenador {

    public static void main(String[] args) throws ParseException {

        Coordenador coordenador = new Coordenador();
        Endereco endereco = new Endereco();
        Telefone telefone = new Telefone();
        
        EntityManager em = JpaUtil.getEntityManager();
        
        coordenador.setNome("Paulo Nogueira Almeida");
        coordenador.setNacionalidade("Brasil");
        coordenador.setSexo(em.find(Sexo.class,2));
        coordenador.setEstadoCivil(em.find(EstadoCivil.class,1));
        coordenador.setEmail("paulo@gmail.com");
        coordenador.setCpf("023.674.854-84");
        coordenador.setMatricula("546465");
        coordenador.setRg("8765467");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date = sdf.parse("24/11/1964");
        coordenador.setDataNascimento(date);
        
        coordenador.setCoordenacao(em.find(Coordenacao.class,2));
        
        telefone.setDdd("63");
        telefone.setNumero("8422-5673");
        
        endereco.setCep("77498-074");
        endereco.setCidade("Palmas");
        endereco.setLogradouro("AL 2, 404 Norte");
        endereco.setBairro("Plano Diretor Norte");
        endereco.setNumero("44");
        endereco.setEstado(em.find(Estado.class,27));
        coordenador.setEndereco(endereco);
        coordenador.setTelefone(telefone);
        
        try {
            
            em.getTransaction().begin();

            em.persist(coordenador);

            em.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}