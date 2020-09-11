/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MiscUtils;

import Abst.UsersFacade;
import Beans.UserBean;
import Entities.Users;
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
@SessionScoped
@Named (value="test")
public class TestBean implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Inject UserBean userBean;
    @Inject LoginBean loginBean;
    
    @EJB UsersFacade uFacade;
    
    
    public String activationString (){
        
        
        Users u = uFacade.find(loginBean.sentUsername());
        System.out.println(u.getHasAccess() + "Shiet");
        return u.getFirstname();
    }
    
}
