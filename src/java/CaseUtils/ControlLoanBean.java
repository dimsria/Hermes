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
import java.util.Locale;
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
    private List <Pcloan> filteredLoans;
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
    
    /**
     * Sökfunktion
     * @param value
     * @param filter
     * @param locale
     * @return
     */
    public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
        if (filterText == null || filterText.equals("")) {
            return true;
        }
        int filterInt = getInteger(filterText);
 
        Pcloan p = (Pcloan) value;
        return p.getUsername().toLowerCase().contains(filterText)
                || p.getArStatus().toLowerCase().contains(filterText)
                || p.getPcName().toLowerCase().contains(filterText)
                || p.getId() < filterInt;
    }
    
     private int getInteger(String string) {
        try {
            return Integer.valueOf(string);
        }
        catch (NumberFormatException ex) {
            return 0;
        }
     }
    // Getters n setters
    public List <Pcloan> getLoans() {
        return loans;
    }

    public void setLoans(List <Pcloan> loans) {
        this.loans = loans;
    }

    public List <Pcloan> getFilteredLoans() {
        return filteredLoans;
    }

    public void setFilteredLoans(List <Pcloan> filteredLoans) {
        this.filteredLoans = filteredLoans;
    }
}