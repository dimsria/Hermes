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
package UserUtils;

import javax.ejb.Stateless;
import javax.inject.Named;

/**
 *Bean för administratörsinloggning
 * @author srvmng
 */
@Named(value="admin")
@Stateless
public class AdminLoginBean {
     
    private String username;
    private String password;
    
    /**
     *loginfunktionen som omredigerar till admin-meny ifall man anger rätt username och lösenord.
     * @return
     */
    public String login() {
        
        boolean loggedIn = false;
         
        if(username != null && username.equals("admin") && password != null && password.equals("admin")) {
            loggedIn = true;
            return "admin?faces-redirect=true";
        } else {
            loggedIn = false;  
        }

        return null;
    }
    
    //Getters n Setters
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
   
       
}
