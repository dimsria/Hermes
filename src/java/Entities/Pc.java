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
    ,@NamedQuery(name = "Pc.findByPcName", query = "SELECT p FROM Pc p WHERE p.pcName = :pcName")
    ,@NamedQuery(name = "Pc.findByFilter", query = "SELECT p FROM Pc p WHERE p.available = 'Ja'")
    ,@NamedQuery(name = "Pc.findByAvailable", query = "SELECT p FROM Pc p WHERE p.available = :available")})
public class Pc implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "pc_name")
    private String pcName;

    @Basic(optional = false)
    @Column(name = "available")
    private String available;

    public Pc() {
    }

    public Pc(String pcName) {
        this.pcName = pcName;
    }

    public Pc(String pcName, String available) {
        this.pcName = pcName;
        this.available = available;
    }

    public String getPcName() {
        return pcName;
    }

    public void setPcName(String pcName) {
        this.pcName = pcName;
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
        return pcName;
    }
    
}
