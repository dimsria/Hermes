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

import Abst.ItineraryFacade;
import Entities.Itinerary;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author srvmng
 */
@SessionScoped
@Named(value="listBean")
public class ListBean implements Serializable {
    
    @EJB ItineraryFacade iFacade;
    
        public List <Itinerary> openOrders(){
        return iFacade.findWithNamedQuery("Itinerary.findByOrderstatus2");
    }
    
}
