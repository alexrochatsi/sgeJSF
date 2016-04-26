package util;

import dominio.Empresa;
import dominio.Endereco;
import dominio.Estado;
import dominio.Supervisor;
import dominio.Telefone;
import dominio.EstadoCivil;
import dominio.Sexo;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.EntityManager;

public class InsereSupervisor {

    public static void main(String[] args) throws ParseException {

        //cadastrar empresa primeiro, depois o supervisor.
        
        Supervisor supervisor = new Supervisor();
        Endereco endereco = new Endereco();
        Telefone telefone = new Telefone();
        
        EntityManager em = JpaUtil.getEntityManager();
        
        supervisor.setNome("Flaviane Ferreira");
        supervisor.setNacionalidade("Brasil");
        supervisor.setSexo(em.find(Sexo.class, 1));
        supervisor.setEstadoCivil(em.find(EstadoCivil.class, 1));
        supervisor.setEmail("flaviane@mvincorporacao.com.br");
        supervisor.setCpf("135.815.015-24");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date = sdf.parse("11/09/1989");
        supervisor.setDataNascimento(date);
        supervisor.setCargo("Gerente de Departamento Pessoal");
        supervisor.setEmpresa(em.find(Empresa.class,1));
        
        telefone.setDdd("63");
        telefone.setNumero("9954-4815");
        
        endereco.setCep("77074-014");
        endereco.setCidade("Palmas");
        endereco.setLogradouro("607 Norte, AL 48");
        endereco.setBairro("Plano Diretor Norte");
        endereco.setNumero("55");
        endereco.setEstado(em.find(Estado.class,27));
        supervisor.setEndereco(endereco);
        supervisor.setTelefone(telefone);
        
        try {
            
            em.getTransaction().begin();

            em.persist(supervisor);

            em.getTransaction().commit();

        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}