/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dominio.VagaEstagio;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import util.JpaUtil;

/**
 *
 * @author Rocha
 */
public class VagaEstagioDao extends DaoGenerico<Object> {
    
    public VagaEstagioDao() {
        super(VagaEstagio.class);
    }
    
    public List<VagaEstagio> getVagasAprovadas() {

        EntityManager em = JpaUtil.getEntityManager();
        
        List<VagaEstagio> vagaEstagios;

        try {
            Query query = em.createNamedQuery("VagaEstagio.vagaAprovadas");
            query.setParameter("statusAprovado", Boolean.TRUE);
            vagaEstagios = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            vagaEstagios = new ArrayList();
        } finally {
            em.close();
        }
        return vagaEstagios;
    }
    
}
