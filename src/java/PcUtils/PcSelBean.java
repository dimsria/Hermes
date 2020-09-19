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
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *Bean för att välja lånedator
 * @author srvmng
 */
@RequestScoped
@Named(value = "select")
public class PcSelBean {

    @EJB PcFacade pcFacade;
    @Inject PcBean pcBean;

    private String pcName;
    private String available;
    private List < Pc > pcs;
    private Date idate;

    /**
     *Lista med tillgängliga datorer
     */
    @PostConstruct
    public void init() {

        pcs = pcFacade.findWithNamedQuery("Pc.findByFilter");
        System.out.println(pcs);

    }

    //Getters and setters
    public String sendPcName() {

        return pcName;
    }

    public String getPcName() {
        return pcName;

    }

    public void setPcName(String pcName) {
        this.pcName = pcName;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public List < Pc > getPcs() {
        return pcs;
    }

    public void setPcs(List < Pc > pcs) {
        this.pcs = pcs;
    }

    public Date getIdate() {
        return idate;
    }

    public void setIdate(Date idate) {
        this.idate = idate;
    }

}