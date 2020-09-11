/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MiscUtils;

import Abst.UsersFacade;
import Entities.Users;
import UserUtils.LoginBean;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author srvmng
 */
@RequestScoped
@Named(value = "activation")
public class ActivationBean {

  @Inject LoginBean login;
    
  @EJB UsersFacade uFacade;
  
  
  private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

  public String checkUser(){
      
    Users u = uFacade.find(login.sentUsername());
    System.out.println(u.getHasAccess());
    if (("Yes").equals(u.getHasAccess())){
     return "activation";   
    }
    
    else {
        
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Contact admin."));
        return null;
    }
  }
  
  public String activationCode(int count) {
      
    
        StringBuilder builder = new StringBuilder();
        while (count--!=0) {
        int character = (int)(Math.random() * ALPHA_NUMERIC_STRING.length());
        builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }

            System.out.print(builder.toString());
            return builder.toString();
    }
 
  }

