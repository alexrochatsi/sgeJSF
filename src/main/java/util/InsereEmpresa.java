package util;


import dominio.Empresa;
import dominio.Endereco;
import dominio.Estado;
import dominio.RepresentanteEmpresa;
import dominio.Telefone;
import dominio.PorteEmpresa;
import dominio.RamoAtividade;
import java.text.ParseException;
import java.util.Date;
import javax.persistence.EntityManager;

public class InsereEmpresa {

    public static void main(String[] args) throws ParseException {
        
        EntityManager em = JpaUtil.getEntityManager();

        Empresa empresa = new Empresa();
        Telefone telefone = new Telefone();
        Endereco endereco = new Endereco();
        
        empresa.setNomeFantasia("M&V Construção e Incorporação");
        empresa.setRazaoSocial("M&V Construção e Incorporação LTDA");
        empresa.setAtividadePrincipal("Construção de Edificios");
        empresa.setCnpj("08.774.749.0001-06");
        Date agora = new Date(System.currentTimeMillis());
        //DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        empresa.setDataCadastro(agora);
        empresa.setEmail("ti@mvincorporacao.com.br");
        empresa.setGerenteRh("Flaviane Ferreira");
        empresa.setInscricaoEstadual("126.178.454.128");
        empresa.setPorte(em.find(PorteEmpresa.class,3));
        empresa.setStatusAprovacao(Boolean.FALSE);
        empresa.setRamoAtividade(em.find(RamoAtividade.class,1));
        empresa.setRepresentanteLegalEmpresa(em.find(RepresentanteEmpresa.class,7));
                
        telefone.setDdd("63");
        telefone.setNumero("3215-4845");
        
        endereco.setCep("77000-000");
        endereco.setCidade("Palmas");
        endereco.setLogradouro("106 Norte, Al 02");
        endereco.setBairro("Plano Diretor Norte");
        endereco.setNumero("3");
        endereco.setEstado(em.find(Estado.class,27));
        
        empresa.setEndereco(endereco);
        empresa.setTelefone(telefone);
        
        try {
            em.getTransaction().begin();
            em.persist(empresa);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}