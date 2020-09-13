/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Entities.Products;
import Entities.Users;
import java.io.Serializable;
import java.util.Date;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author srvmng
 */
@SessionScoped
@Named(value="itBean")
public class ItineraryBean implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private Integer orderid;
    private int quantity;
    private Date datecreated;
    private String orderstatus;
    private Products prodid;
    private Users username;

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getDatecreated() {
        return datecreated;
    }

    public void setDatecreated(Date datecreated) {
        this.datecreated = datecreated;
    }

    public String getOrderstatus() {
        return orderstatus;
    }

    public void setOrderstatus(String orderstatus) {
        this.orderstatus = orderstatus;
    }

    public Products getProdid() {
        return prodid;
    }

    public void setProdid(Products prodid) {
        this.prodid = prodid;
    }

    public Users getUsername() {
        return username;
    }

    public void setUsername(Users username) {
        this.username = username;
    }
    
    
    
}
