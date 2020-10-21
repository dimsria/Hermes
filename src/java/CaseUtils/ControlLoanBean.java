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

import Abst.PcFacade;
import Abst.PcloanFacade;
import Entities.Pc;
import Entities.Pcloan;
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
 * Crud Bean för datorlån.
 */
@Named(value = "clBean")
@RequestScoped

public class ControlLoanBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private List <Pcloan> loans;
    //Abstrakt class som ersätter entity manager
    @EJB PcloanFacade pcloanFacade;
    @EJB PcFacade pcFacade;


    @PostConstruct
    public void init(){
        loans = pcloanFacade.findWithNamedQuery("Pcloan.findByArStatus");
    }

    /**
     *Returnerar antal låneärende.
     * @return
     */
    public int count() {
        return pcloanFacade.count();
    }

    /**
     *Instant redigering och sparande av ändringar
     * @param event
     */
    public void onRowEdit(RowEditEvent<Pcloan> event) {
        
        Pcloan b = (Pcloan)event.getObject();
        Pc p = new Pc(event.getObject().getPcName());
        System.out.print(p.getPcName());// Test purposes
        p.setAvailable("Ja");
        pcFacade.edit(p);
        pcloanFacade.edit(b);
               
        FacesMessage msg = new FacesMessage("Ärendet", event.getObject().getId().toString() + " " + "modifierades" + " " 
                + "och" + " " + event.getObject().getPcName() +" " + " är nu tillgänglig.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void onRowCancel(RowEditEvent<Pcloan> event) {
        FacesMessage msg = new FacesMessage("Ändringen avbrutten");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    // Getters n setters
    public List <Pcloan> getLoans() {
        return loans;
    }

    public void setLoans(List <Pcloan> loans) {
        this.loans = loans;
    }
}