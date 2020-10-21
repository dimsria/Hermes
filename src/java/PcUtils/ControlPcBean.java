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
package PcUtils;

import Abst.PcFacade;
import Entities.Pc;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.event.RowEditEvent;

/**
 *CrudBean för att lägga till
 * Redigera och
 * Ta bort lånedatorer
 * @author srvmng
 */
@Named(value = "cpcBean")
@ViewScoped
public class ControlPcBean implements Serializable {


    @EJB PcFacade pcFacade; //entity manager
    

    private static final long serialVersionUID = 1L;
    private List <Pc> pcs;
    
    @PostConstruct
    public void init(){
        pcs = pcFacade.findAll();
    }
    
    public void onRowEdit(RowEditEvent<Pc> event) {
        
        Pc p = (Pc)event.getObject();
        pcFacade.edit(p);
        System.out.print(p.getPcName());// Test purposes
        FacesMessage msg = new FacesMessage("Datorn  ", event.getObject().getPcName() + " " + "modifierad");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void onRowCancel(RowEditEvent<Pc> event) {
        FacesMessage msg = new FacesMessage("Ändringen avbrutten");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    /**
     *Tar bort den valda datorn
     * @param p
     * @return
     */
    public String delete(Pc p) {

        pcFacade.remove(p);
        return null;
    }
    //Getters n Setters
    public List <Pc> getPcs() {
        return pcs;
    }

    public void setPcs(List <Pc> pcs) {
        this.pcs = pcs;
    }
    
    



}