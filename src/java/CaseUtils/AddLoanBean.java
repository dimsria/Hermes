/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CaseUtils;

import Abst.PcloanFacade;
import Beans.LoanBean;
import PcUtils.PcSelBean;
import UserUtils.LoginBean;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author srvmng
 */
@Named(value="loanBean")
@SessionScoped
public class AddLoanBean implements Serializable{
    
    @EJB PcloanFacade pcloanFacade;
    @Inject LoanBean loanBean;
    @Inject LoginBean login;
    @Inject PcSelBean select;
    
}
