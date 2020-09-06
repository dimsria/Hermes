/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserUtils;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author srvmng
 */
@Named(value="admin")
@RequestScoped
public class AdminLoginBean {
     
    private String username;
     
    private String password;
 
    public String getUsername() {
        return username;
    }
 
    public void setUsername(String username) {
        this.username = username;
    }
 
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
   
    public String login() {
        
        boolean loggedIn = false;
         
        if(username != null && username.equals("admin") && password != null && password.equals("admin")) {
            loggedIn = true;
            return "admin";
        } else {
            loggedIn = false;
            
        }
         
        
        
        return null;
    }   
}
