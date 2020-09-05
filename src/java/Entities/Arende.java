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
@Table(name = "arende")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Arende.findAll", query = "SELECT a FROM Arende a")
    , @NamedQuery(name = "Arende.findById", query = "SELECT a FROM Arende a WHERE a.id = :id")
    , @NamedQuery(name = "Arende.findByUsername", query = "SELECT a FROM Arende a WHERE a.username = :username")
    , @NamedQuery(name = "Arende.findByArType", query = "SELECT a FROM Arende a WHERE a.arType = :arType")
    , @NamedQuery(name = "Arende.findByDescrip", query = "SELECT a FROM Arende a WHERE a.descrip = :descrip")
    ,@NamedQuery(name = "Arende.findByIncident", query = "SELECT a FROM Arende a WHERE a.arType = 'Incident' and a.arStatus = 'Öppet' ")
    ,@NamedQuery(name = "Arende.findBytest", query = "SELECT i FROM Arende i WHERE i.arType = 'Incident' and i.arStatus = 'Stängt' ")
    ,@NamedQuery(name = "Arende.findByQuestion", query = "SELECT a FROM Arende a WHERE a.arType = 'Question' and a.arStatus='Öppet' ")
    ,@NamedQuery(name = "Arende.findBytest2", query = "SELECT q FROM Arende q WHERE q.arType = 'Question' and q.arStatus='Stängt' ")
    ,@NamedQuery(name = "Arende.findByDatecreated", query = "SELECT a FROM Arende a WHERE a.datecreated = :datecreated")
    ,@NamedQuery(name = "Arende.findByArStatus", query = "SELECT a FROM Arende a WHERE a.arStatus = :arStatus")})
public class Arende implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "username")
    private String username;
    @Basic(optional = false)
    @Column(name = "ar_type")
    private String arType;
    @Basic(optional = false)
    @Column(name = "descrip")
    private String descrip;
    @Column(name = "datecreated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datecreated;
    @Basic(optional = false)
    @Column(name = "ar_status")
    private String arStatus;

    public Arende() {
    }

    public Arende(Integer id) {
        this.id = id;
    }

    public Arende(Integer id, String username, String arType, String descrip, String arStatus) {
        this.id = id;
        this.username = username;
        this.arType = arType;
        this.descrip = descrip;
        this.arStatus = arStatus;
    }

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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Arende)) {
            return false;
        }
        Arende other = (Arende) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Arende[ id=" + id + " ]";
    }
    
}
