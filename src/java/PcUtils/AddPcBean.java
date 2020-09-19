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
import Beans.PcBean;
import Entities.Pc;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *CrudBean för att lägga till
 * Redigera och
 * Ta bort lånedatorer
 * @author srvmng
 */
@Named(value = "pBean")
@SessionScoped
public class AddPcBean implements Serializable{

    
    @EJB PcFacade pcFacade;//entity manager
    
    @Inject PcBean pcBean; //backing bean
    
    private static final long serialVersionUID = 1L;
    
    /**
     *Returnerar en lista med lånedatorer
     * @return
     */
    public List<Pc> getAll(){
        return pcFacade.findAll();
    }
    
    /**
     *Lägger en ny lånedator
     * Skickar sen till pc-sida
     * @return
     */
    public String add(){
        Pc p = new Pc();
        p.setPcName(pcBean.getPcName());
        p.setAvailable(pcBean.getAvailable());
        pcFacade.create(p);
        return "editpc";
    }
    
    /**
     *Modifierar en lånedator
     * Skickar sen till save-funktionen
     * @param p
     * @return
     */
    public String edit(Pc p){
        
        pcBean.setPcName(p.getPcName());
        pcBean.setAvailable(p.getAvailable());
        
        return "updatepc";
    }
    
    /**
     *Sparar den modifierade datorn och
     * omredigerar till pc-sida
     * @return
     */
    public String save(){
        Pc p = new Pc(pcBean.getPcName());
        p.setPcName(pcBean.getPcName());
        p.setAvailable(pcBean.getAvailable());
        pcFacade.edit(p);
        return "editpc";
    }
    
    /**
     *Tar bort den valda datorn
     * @param p
     * @return
     */
    public String delete (Pc p){
    
        pcFacade.remove(p);
        return null;
    }
    

    
}
