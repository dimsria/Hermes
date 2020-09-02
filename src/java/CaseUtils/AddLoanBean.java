/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CaseUtils;

import Abst.PcFacade;
import Abst.PcloanFacade;
import Beans.LoanBean;
import Beans.PcBean;
import Entities.Arende;
import Entities.Pc;
import Entities.Pcloan;
import PcUtils.PcSelBean;
import UserUtils.LoginBean;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author srvmng
 */
@Named(value="aloanBean")
@RequestScoped

public class AddLoanBean{
    
    @EJB PcloanFacade pcloanFacade;
    @EJB PcFacade pcFacade;
    @Inject LoanBean loanBean;
    @Inject LoginBean login;
    @Inject PcSelBean select;
    @Inject PcBean pcBean;
    
    public List <Pcloan> getAll(){
        
        return pcloanFacade.findAll();
    }
    
    public int count(){
        return pcloanFacade.count();
    }
    
    public String add() throws IOException{
        
        Pcloan b = new Pcloan();
        Pc p = new Pc(select.sendPcName());
        
        
        b.setUsername(login.sentUsername());
        b.setArType("Datorlån");
        b.setDatecreated(new java.sql.Date(new java.util.Date().getTime()));
        b.setDescrip(loanBean.getDescrip());
        b.setPcName(select.sendPcName());
        b.setReturndate(new java.sql.Date(select.getIdate().getTime()));
        b.setArStatus("Öppet");
        p.setAvailable("No");
        pcFacade.edit(p);
        pcloanFacade.create(b);
        
        return "welcomePrimefaces";
    }
    public String edit(Arende a){
        loanBean.setArStatus(a.getArStatus());
        return "pcloans";
    }
    
    public String save (){
        
        Pcloan b = new Pcloan(loanBean.getId());
        b.setArStatus(loanBean.getArStatus());
        pcloanFacade.edit(b);
        return "pcloans";
    }
}
