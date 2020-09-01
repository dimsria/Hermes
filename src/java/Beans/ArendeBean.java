/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import java.io.Serializable;
import java.util.Date;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author srvmng
 */
@Named(value="aBean")
@SessionScoped
public class ArendeBean implements Serializable {
    
    private Integer id;
    private String username;
    private String arType;
    private String descrip;
    private Date datecreated;
    private String arStatus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getArType() {
        return arType;
    }

    public void setArType(String arType) {
        this.arType = arType;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    public Date getDatecreated() {
        return datecreated;
    }

    public void setDatecreated(Date datecreated) {
        this.datecreated = datecreated;
    }

    public String getArStatus() {
        return arStatus;
    }

    public void setArStatus(String arStatus) {
        this.arStatus = arStatus;
    }
    
}
