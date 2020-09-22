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
@Table(name = "pcloan")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pcloan.findAll", query = "SELECT p FROM Pcloan p")
    , @NamedQuery(name = "Pcloan.findById", query = "SELECT p FROM Pcloan p WHERE p.id = :id")
    , @NamedQuery(name = "Pcloan.findByUsername", query = "SELECT p FROM Pcloan p WHERE p.username = :username")
    , @NamedQuery(name = "Pcloan.findByArType", query = "SELECT p FROM Pcloan p WHERE p.arType = :arType")
    , @NamedQuery(name = "Pcloan.findByDescrip", query = "SELECT p FROM Pcloan p WHERE p.descrip = :descrip")
    , @NamedQuery(name = "Pcloan.findByPcName", query = "SELECT p FROM Pcloan p WHERE p.pcName = :pcName")
    , @NamedQuery(name = "Pcloan.findByDatecreated", query = "SELECT p FROM Pcloan p WHERE p.datecreated = :datecreated")
    , @NamedQuery(name = "Pcloan.findByArStatus", query = "SELECT p FROM Pcloan p WHERE p.arStatus = 'Öppet'")
    , @NamedQuery(name = "Pcloan.findByClosed", query = "SELECT p FROM Pcloan p WHERE p.arStatus = 'Stängt'")
    , @NamedQuery(name = "Pcloan.findByReturndate", query = "SELECT p FROM Pcloan p WHERE p.returndate = :returndate")})
public class Pcloan implements Serializable {

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
    @Basic(optional = false)
    @Column(name = "pc_name")
    private String pcName;
    @Column(name = "datecreated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datecreated;
    @Basic(optional = false)
    @Column(name = "returndate")
    @Temporal(TemporalType.DATE)
    private Date returndate;
    @Basic(optional = false)
    @Column(name = "ar_status")
    private String arStatus;

    public Pcloan() {
    }

    public Pcloan(Integer id) {
        this.id = id;
    }

    public Pcloan(Integer id, String username, String arType, String descrip, String pcName, Date returndate, String arStatus) {
        this.id = id;
        this.username = username;
        this.arType = arType;
        this.descrip = descrip;
        this.pcName = pcName;
        this.returndate = returndate;
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

    public String getPcName() {
        return pcName;
    }

    public void setPcName(String pcName) {
        this.pcName = pcName;
    }

    public Date getDatecreated() {
        return datecreated;
    }

    public void setDatecreated(Date datecreated) {
        this.datecreated = datecreated;
    }

    public Date getReturndate() {
        return returndate;
    }

    public void setReturndate(Date returndate) {
        this.returndate = returndate;
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
        if (!(object instanceof Pcloan)) {
            return false;
        }
        Pcloan other = (Pcloan) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Arendeid:" + id + ","
                + "Användarnamn:" + username + ","
                + "Beskrivning:" + descrip + ","
                + "Dator att låna:" + pcName + ","
                + "Datum skapat:" + datecreated + ","
                + "Inlämningsdatum:" + returndate;
        
    }
    
}
