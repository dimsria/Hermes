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
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *CrudBean för att lägga till
 * Redigera och
 * Ta bort lånedatorer
 * @author srvmng
 */
@Named(value = "pBean")
@RequestScoped
public class AddPcBean implements Serializable {


    @EJB PcFacade pcFacade; //entity manager
    @Inject PcBean pcBean; //backing bean

    private static final long serialVersionUID = 1L;

    /**
     *Lägger en ny lånedator
     * Skickar sen till pc-sida
     * @return
     */
    public String add() {
        Pc p = new Pc();
        p.setPcName(pcBean.getPcName());
        p.setAvailable(pcBean.getAvailable());
        pcFacade.create(p);
        return "editpc";
    }

}