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
  
  private String activationString;
  
  private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";


  public String activationCode() {
      
        int count = 8;
        Users u = uFacade.find(login.sentUsername());
        if (u.getHasAccess().contains("No")){
        activationString = "Fel:"+ u.getFirstname() +" saknar behörighet";   
        
        }
        else {
            StringBuilder builder = new StringBuilder();
        while (count--!=0) {
        int character = (int)(Math.random() * ALPHA_NUMERIC_STRING.length());
        builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        activationString = builder.toString();
            
                }
        
            }

          return activationString;  
      
        }
  
    public String getActivationString() {
        return activationString;
    }

    public void setActivationString(String activationString) {
        this.activationString = activationString;
    }

  }

