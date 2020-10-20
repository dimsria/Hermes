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
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * CrudBean för ärende
 *Exakt samma som AddIncidentBean
 * förutom att typfältet är "Question"
 * @author srvmng
 */
@Named(value = "addqBean")
@SessionScoped
public class AddQuestionBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private List <Arende> questions;
    
    
    @EJB ArendeFacade aFacade;

    @Inject ArendeBean aBean;
    @Inject LoginBean login;

    /**
     *Skapar ett nytt ärende av typen "Question"
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    public String add() throws IOException, InterruptedException {
        
        FileExport d = new FileExport();
        Arende a = new Arende();
        a.setUsername(login.sentUsername());
        a.setArType("Question");
        a.setDatecreated(new java.sql.Date(new java.util.Date().getTime()));
        a.setDescrip(aBean.getDescrip());
        a.setArStatus("Öppet");

        aFacade.create(a);
        
        d.FileDownload(a.toString());
        return "menu";
    }

}