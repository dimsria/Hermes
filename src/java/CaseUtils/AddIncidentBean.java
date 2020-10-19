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
import Beans.ArendeBean;
import Entities.Arende;
import MiscUtils.FileExport;
import UserUtils.LoginBean;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.RowEditEvent;

/**
 *
 * Crud Bean för incidenter.
 */
@Named(value = "iBean")
@ViewScoped
public class AddIncidentBean implements Serializable {

    //Abstrakt class som ersätter entity manager  
    @EJB ArendeFacade aFacade;

    //Inject backing bean för ärenden och loginbean för inloggade användare
    @Inject ArendeBean aBean;
    @Inject LoginBean login;

    private static final long serialVersionUID = 1L;
    private List <Arende> incidents;
    /**
     *Returnerar a lista med stängda ärenden
     * @return
     */
    public List < Arende > getClosed() {
        return aFacade.findWithNamedQuery("Arende.findBytest");
    }

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

    /**
     *Skapar en ny incident
     * Values from backing bean förutom username som vi får via loginBean 
     * och datum som vi anger det nuvarande.
     * return menu innebär omredigering till menyn-sidan
     * @return
     * @throws IOException
     * @throws java.lang.InterruptedException
     */
    public String add() throws IOException, InterruptedException {
        
        FileExport d = new FileExport();
        
        Arende a = new Arende();
        a.setUsername(login.sentUsername());
        a.setArType("Incident");
        a.setDatecreated(new java.sql.Date(new java.util.Date().getTime()));
        a.setDescrip(aBean.getDescrip());
        a.setArStatus("Öppet");

        aFacade.create(a);
        d.FileDownload(a.toString());// Skapa pdf från a-entitet och skicka till nedladdning
        
        return "menu";
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

}