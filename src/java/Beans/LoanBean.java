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

import java.io.Serializable;
import java.util.Date;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author srvmng
 */
@SessionScoped
@Named(value = "loanBean")
public class LoanBean implements Serializable {
    
  private static final long serialVersionUID = 1L; 
  
  private Integer id;
  private String username;
  private String arType;
  private String descrip;
  private String pcName;
  private Date datecreated;
  private Date returndate;
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

}