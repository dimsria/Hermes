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
import Beans.LoanBean;
import Beans.PcBean;
import Entities.Pc;
import Entities.Pcloan;
import MiscUtils.FileExport;
import PcUtils.PcSelBean;
import UserUtils.LoginBean;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * Crud Bean för datorlån.
 */
@Named(value = "addloanBean")
@SessionScoped

public class AddLoanBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private List <Pcloan> loans;
    //Abstrakt class som ersätter entity manager
    @EJB PcloanFacade pcloanFacade;
    @EJB PcFacade pcFacade;

    /*
     *Behövs backing bean för entity,
     *loginbean för user,
     *pcsel för välja dator
     *och pcbean för ändring av datorns tillgänglighet
     */

    @Inject LoanBean loanBean;
    @Inject LoginBean login;
    @Inject PcSelBean select;
    @Inject PcBean pcBean;

    /**
     *Skapar ett nytt låneärende
     * med hjälp av backing injected beans.
     * och ändrar tillgänglighet på den valda datorn
     * omredigerar sen till menyn-sida
     * @return
     * @throws IOException
     * @throws java.lang.InterruptedException
     */
    public String add() throws IOException, InterruptedException {

        Pcloan b = new Pcloan();
        Pc p = new Pc(select.sendPcName());
        FileExport d = new FileExport();
        
        b.setUsername(login.sentUsername());
        b.setArType("Datorlån");
        b.setDatecreated(new java.sql.Date(new java.util.Date().getTime()));
        b.setDescrip("Låna en dator.");
        b.setPcName(select.sendPcName());
        b.setReturndate(new java.sql.Date(select.getIdate().getTime()));
        b.setArStatus("Öppet");
        p.setAvailable("Nej");
        pcFacade.edit(p);
        pcloanFacade.create(b);
        d.FileDownload(b.toString());
        return "menu";
    }

}