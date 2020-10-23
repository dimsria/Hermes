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
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author srvmng
 */
@Named(value="coBean")
@RequestScoped
public class ControlOrderBean implements Serializable {
    
        
    @EJB ItineraryFacade iFacade;
    
    private List <Itinerary> orders;
    private int count;
    
    
    
    @PostConstruct
    public void init(){
        orders = iFacade.findWithNamedQuery("Itinerary.findByOrderstatus2");
    }
    
    public void increment() {  
        count++;  
    }
    
    public void onRowEdit(RowEditEvent<Itinerary> event) {
        
        Itinerary t = (Itinerary)event.getObject();
        iFacade.edit(t);
        System.out.print(t.getOrderid());// Test purposes
        
        FacesMessage msg = new FacesMessage("Beställningen", event.getObject().getOrderid().toString() + " " + "modifierades");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void onRowCancel(RowEditEvent<Itinerary> event) {
        FacesMessage msg = new FacesMessage("Ändringen avbrutten");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    //Getters n Setters

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List <Itinerary> getOrders() {
        return orders;
    }

    public void setOrders(List <Itinerary> orders) {
        this.orders = orders;
    }
    

}
