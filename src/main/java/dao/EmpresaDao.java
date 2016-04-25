/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dominio.Empresa;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import util.JpaUtil;

/**
 *
 * @author Rocha
 */
public class EmpresaDao extends DaoGenerico<Object> {
    
    public EmpresaDao() {
        super(Empresa.class);
    }
    
    public List<Empresa> getEmpresasAprovadas() {

        EntityManager em = JpaUtil.getEntityManager();
        
        List<Empresa> empresas;

        try {
            Query query = em.createNamedQuery("Empresa.empresasAprovadas");
            query.setParameter("statusAprovado", Boolean.TRUE);
            empresas = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            empresas = new ArrayList();
        } finally {
            em.close();
        }
        return empresas;
    }
    
}
