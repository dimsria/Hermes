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
package MiscUtils;

import Abst.UsersFacade;
import Entities.Users;
import UserUtils.LoginBean;
import java.io.IOException;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *För att demonstrera hur 
 * hämtning av aktiveringskod ska se ut
 * @author srvmng
 */
@RequestScoped
@Named(value = "activation")
public class ActivationBean {

    @Inject LoginBean login;

    @EJB UsersFacade uFacade;

    private String activationString;
    private String username;

    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    /**
     *Kollar ifall personen är behörig i db
     * och skickar antigen aktiveringskod eller felmeddelande
     * @return
     * @throws java.lang.InterruptedException
     * @throws java.io.IOException
     */
    public String activationCode() throws InterruptedException, IOException {
        
        int count = 8; //antal charaktarer
        Users u = uFacade.find(login.sentUsername()); // get from loginBean användarnamn
        if (u.getHasAccess().contains("No")) { //Kolla ifall användaren har access i db
            setActivationString("Fel:" + u.getFirstname() + " saknar behörighet"); //ActivationString blir lika med detta 

        } else { //Annars
            StringBuilder builder = new StringBuilder();
            while (count-- != 0) {
                int character = (int)(Math.random() * ALPHA_NUMERIC_STRING.length());
                builder.append(ALPHA_NUMERIC_STRING.charAt(character));
                setActivationString(builder.toString()); //Return en random blandning av sifror och bokstaver
            }
        } 
        return getActivationString();

    }
    
    public void printActivation() throws InterruptedException, IOException{
        FileExport d = new FileExport();
        Users u = uFacade.find(login.sentUsername());
        d.FileDownload("Username:" + u.getUsername() + " ," + "Aktiveringskod:" + activationCode());
    }
    //Getters n Setters
    public String getActivationString() {
        return activationString;
    }

    public void setActivationString(String activationString) {
        this.activationString = activationString;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}