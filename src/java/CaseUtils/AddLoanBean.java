/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CaseUtils;

import Abst.PcloanFacade;
import Beans.LoanBean;
import Entities.Pcloan;
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
 * @author srvmng
 */
@Named(value="aloanBean")
@SessionScoped
public class AddLoanBean implements Serializable{
    
    @EJB PcloanFacade pcloanFacade;
    
    @Inject LoanBean loanBean;
    @Inject LoginBean login;
    @Inject PcSelBean select;
    
    public List <Pcloan> getAll(){
        
        return pcloanFacade.findAll();
    }
    
    public int count(){
        return pcloanFacade.count();
    }
    
    public String add() throws IOException{
        
        Pcloan b = new Pcloan();
        b.setUsername(login.sentUsername());
        b.setArType("Datorlån");
        b.setDatecreated(new java.sql.Date(new java.util.Date().getTime()));
        b.setDescrip(loanBean.getDescrip());
        b.setPcName(select.sendPcName());
        b.setReturndate(new java.sql.Date(select.getIdate().getTime()));
        b.setArStatus("Öppet");
        
        pcloanFacade.create(b);
       
        return "welcomePrimefaces";
    }
}
