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
package Beans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * Backing bean för Users Entity
 */
@Named(value = "userBean")
@RequestScoped
public class UserBean {

    private String username;
    private String firstname;
    private String surname;
    private String email;
    private String telephone;
    private String hasAccess;

    // Getters and setters

    public String getHasAccess() {
        return hasAccess;
    }

    public void setHasAccess(String hasAccess) {
        this.hasAccess = hasAccess;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

}