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
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author srvmng
 */
@SessionScoped
@Named(value = "pcBean")
public class PcBean implements Serializable{

  private static final long serialVersionUID = 1L;  
  private String pcName;
  
  private String available;

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

}