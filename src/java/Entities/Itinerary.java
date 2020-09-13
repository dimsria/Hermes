/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author srvmng
 */
@Entity
@Table(name = "itinerary")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Itinerary.findAll", query = "SELECT i FROM Itinerary i")
    , @NamedQuery(name = "Itinerary.findByOrderid", query = "SELECT i FROM Itinerary i WHERE i.orderid = :orderid")
    , @NamedQuery(name = "Itinerary.findByQuantity", query = "SELECT i FROM Itinerary i WHERE i.quantity = :quantity")
    , @NamedQuery(name = "Itinerary.findByDatecreated", query = "SELECT i FROM Itinerary i WHERE i.datecreated = :datecreated")
    , @NamedQuery(name = "Itinerary.findByOrderstatus", query = "SELECT i FROM Itinerary i WHERE i.orderstatus = :orderstatus")})
public class Itinerary implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "orderid")
    private Integer orderid;
    @Basic(optional = false)
    @Column(name = "quantity")
    private int quantity;
    @Column(name = "datecreated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datecreated;
    @Basic(optional = false)
    @Column(name = "orderstatus")
    private String orderstatus;
    @JoinColumn(name = "prodid", referencedColumnName = "prodid")
    @ManyToOne(optional = false)
    private Products prodid;
    @JoinColumn(name = "username", referencedColumnName = "username")
    @ManyToOne(optional = false)
    private Users username;

    public Itinerary() {
    }

    public Itinerary(Integer orderid) {
        this.orderid = orderid;
    }

    public Itinerary(Integer orderid, int quantity, String orderstatus) {
        this.orderid = orderid;
        this.quantity = quantity;
        this.orderstatus = orderstatus;
    }

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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orderid != null ? orderid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Itinerary)) {
            return false;
        }
        Itinerary other = (Itinerary) object;
        if ((this.orderid == null && other.orderid != null) || (this.orderid != null && !this.orderid.equals(other.orderid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Itinerary[ orderid=" + orderid + " ]";
    }
    
}
