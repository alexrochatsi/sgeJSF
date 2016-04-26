package util;

import dominio.Empresa;
import dominio.Estagio;
import dominio.TipoEstagio;
import dominio.RamoAtividade;
import dominio.Turno;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.EntityManager;

public class InsereEstagio {

    public static void main(String[] args) throws ParseException {

        Estagio estagio = new Estagio();
        
        EntityManager em = JpaUtil.getEntityManager();
        
        estagio.setRamoAtividade(em.find(RamoAtividade.class,4));
        estagio.setValorBolsa(880.00);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date = sdf.parse("01/07/2016");
        estagio.setDataInicio(date);
        date = sdf.parse("30/12/2016");
        estagio.setDataTermino(date);
        estagio.setAtividade("WebDesign com CSS, jQuery em páginas JSF");
        estagio.setCargaHoraria(20);
        estagio.setStatusAprovacao(Boolean.TRUE);
        estagio.setTurno(em.find(Turno.class, 2));
        estagio.setTipoEstagio(em.find(TipoEstagio.class, 1));
        estagio.setEmpresa(em.find(Empresa.class, 1));
        
        try {
            em.getTransaction().begin();
            em.persist(estagio);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}