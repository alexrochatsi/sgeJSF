package util;

import dominio.Endereco;
import dominio.Estado;
import dominio.RepresentanteEmpresa;
import dominio.Telefone;
import dominio.EstadoCivil;
import dominio.Sexo;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.EntityManager;

public class InsereRepEmpresa {

    public static void main(String[] args) throws ParseException {

        RepresentanteEmpresa representanteEmpresa = new RepresentanteEmpresa();
        Endereco endereco = new Endereco();
        Telefone telefone = new Telefone();
        
        EntityManager em = JpaUtil.getEntityManager();
        
        representanteEmpresa.setNome("Fabiano Roberto Matos do Vale Filho");
        representanteEmpresa.setNacionalidade("Brasil");
        representanteEmpresa.setSexo(em.find(Sexo.class,2));
        representanteEmpresa.setEstadoCivil(em.find(EstadoCivil.class,1));
        representanteEmpresa.setEmail("fabiano@mvincorporacao.com.br");
        representanteEmpresa.setCpf("800.615.574-25");
        representanteEmpresa.setChapa("04512");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date = sdf.parse("13/07/1972");
        representanteEmpresa.setDataNascimento(date);

        telefone.setDdd("63");
        telefone.setNumero("8143-7788");

        endereco.setCep("77021-615");
        endereco.setCidade("Palmas");
        endereco.setLogradouro("106 Sul, AL 13");
        endereco.setBairro("Plano Diretor Sul");
        endereco.setNumero("01");
        endereco.setEstado(em.find(Estado.class,27));

        representanteEmpresa.setEndereco(endereco);
        representanteEmpresa.setTelefone(telefone);
        
        try {    
            em.getTransaction().begin();
            em.persist(representanteEmpresa);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}