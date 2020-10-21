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
package CaseUtils;

import Abst.ArendeFacade;
import Entities.Arende;
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
 * Crud Bean för incidenter.
 */
@Named(value = "ciBean")
@RequestScoped
public class ControlIncidentBean implements Serializable {

    //Abstrakt class som ersätter entity manager  
    @EJB ArendeFacade aFacade;

    private List <Arende> incidents;
    private int count;
    /**
     *Returnerar en lista med öppna ärenden
     *
     */
    @PostConstruct
    public void init() {

        incidents = aFacade.findWithNamedQuery("Arende.findByIncident");
    }

    /**
     *Returnerar antal ärenden
     * @return
     */
    public int count() {
        return aFacade.count();
    }
    
    public void increment() {  
        count++;  
    }

    public void onRowEdit(RowEditEvent<Arende> event) {
        
        Arende i = (Arende)event.getObject();
        aFacade.edit(i);
        System.out.print(i.getId());// Test purposes
        
        FacesMessage msg = new FacesMessage("Ärendet", event.getObject().getId().toString() + " " + "modifierades");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void onRowCancel(RowEditEvent<Arende> event) {
        FacesMessage msg = new FacesMessage("Ändringen avbrutten");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    //Getters n Setters
    public List <Arende> getIncidents() {
        return incidents;
    }

    public void setIncidents(List <Arende> incidents) {
        this.incidents = incidents;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

}