/*
 * Copyright (C) Dimitrios Sria 
 *
 * 2020 srvmng
 * Project Hermes-Kiosk v. 0.5
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 */
package ItineraryUtils;

import Abst.ArendeFacade;
import Abst.ItineraryFacade;
import Abst.PcFacade;
import Abst.PcloanFacade;
import Abst.ProductsFacade;
import Entities.Arende;
import Entities.Itinerary;
import Entities.Pc;
import Entities.Pcloan;
import Entities.Products;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *Backing bean för att visa olika listor
 * @author srvmng
 */
@SessionScoped
@Named(value="listBean")
public class ListBean implements Serializable {
    
    @EJB ItineraryFacade iFacade;
    @EJB ProductsFacade prodFacade;
    @EJB ArendeFacade aFacade;
    @EJB PcloanFacade pcloanFacade;
    @EJB PcFacade pcFacade;
    
    /**
     *Returnerar en lista med ej klara beställningar
     * @return
     */
    public List <Itinerary> openOrders(){
        return iFacade.findWithNamedQuery("Itinerary.findByOrderstatus2");
    }
    
    /**
     *Lista med tillgängliga produkter
     * @return
     */
    public List<Products> getFilter(){
        return prodFacade.findWithNamedQuery("Products.findByQuantityFilter");
    }
    
    /**
     *Lista med stängda IT-frågor
     * @return
     */
    public List < Arende > getAllQs() {
        return aFacade.findWithNamedQuery("Arende.findBytest2");
    }
    
    /**
     *Lista med stängda låneärende
     * @return
     */
    public List < Pcloan > getClosed() {
        return pcloanFacade.findWithNamedQuery("Pcloan.findByClosed");
    }
    
    /**
     *Lista med öppna låneärende
     * @return
     */
    public List < Pcloan > getAllPcLoans() {
        return pcloanFacade.findWithNamedQuery("Pcloan.findByArStatus");
    }

    /**
     *Lista med stängda Incidenter
     * @return
     */
    public List < Arende > getClosedIncs() {
        return aFacade.findWithNamedQuery("Arende.findBytest");
    }
    
    /**
     *Lista med lånedatorer
     * @return
     */
    public List < Pc > getAll() {
        return pcFacade.findAll();
    }
    
    
}
