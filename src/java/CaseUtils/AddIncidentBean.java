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
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * Add Bean för incidenter.
 */
@Named(value = "addincBean")
@SessionScoped
public class AddIncidentBean implements Serializable {

    //Abstrakt class som ersätter entity manager  
    @EJB ArendeFacade aFacade;

    //Inject backing bean för ärenden och loginbean för inloggade användare
    @Inject ArendeBean aBean;
    @Inject LoginBean login;

    private static final long serialVersionUID = 1L;
   
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
        addMessage("Ärende:" + " " + a.getId() + " " + "skapat!");
        d.FileDownload(a.toString());// Skapa pdf från a-entitet och skicka till nedladdning
        
        return "menu";
    }
    
    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

}