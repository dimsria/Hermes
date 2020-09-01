/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author srvmng
 */
@Entity
@Table(name = "pc")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pc.findAll", query = "SELECT p FROM Pc p")
    , @NamedQuery(name = "Pc.findByPcName", query = "SELECT p FROM Pc p WHERE p.pcName = :pcName")
    , @NamedQuery(name = "Pc.findByPcType", query = "SELECT p FROM Pc p WHERE p.pcType = :pcType")
    , @NamedQuery(name = "Pc.findByAvailable", query = "SELECT p FROM Pc p WHERE p.available = :available")})
public class Pc implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "pc_name")
    private String pcName;
    @Basic(optional = false)
    @Column(name = "pc_type")
    private String pcType;
    @Basic(optional = false)
    @Column(name = "available")
    private String available;

    public Pc() {
    }

    public Pc(String pcName) {
        this.pcName = pcName;
    }

    public Pc(String pcName, String pcType, String available) {
        this.pcName = pcName;
        this.pcType = pcType;
        this.available = available;
    }

    public String getPcName() {
        return pcName;
    }

    public void setPcName(String pcName) {
        this.pcName = pcName;
    }

    public String getPcType() {
        return pcType;
    }

    public void setPcType(String pcType) {
        this.pcType = pcType;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pcName != null ? pcName.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pc)) {
            return false;
        }
        Pc other = (Pc) object;
        if ((this.pcName == null && other.pcName != null) || (this.pcName != null && !this.pcName.equals(other.pcName))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Pc[ pcName=" + pcName + " ]";
    }
    
}
