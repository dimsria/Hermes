/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Abst;

import Entities.Arende;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author srvmng
 */
@Stateless
public class ArendeFacade extends AbstractFacade<Arende> {

    @PersistenceContext(unitName = "HermesPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ArendeFacade() {
        super(Arende.class);
    }
    
}
