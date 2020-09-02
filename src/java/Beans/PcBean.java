/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author srvmng
 */
@RequestScoped
@Named(value="pcBean")
public class PcBean {
    
    private String pcName;
    private String pcType;
    private String available;

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
    
    
    
}
