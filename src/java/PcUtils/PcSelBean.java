/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PcUtils;

import Abst.PcFacade;
import Entities.Pc;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author srvmng
 */
@RequestScoped
@Named(value="select")
public class PcSelBean {


    
    
    
    @EJB PcFacade pcFacade;
    
    private String pcName;
    private String pcType;
    private String available;
    private List<Pc>pcs;
    private Date idate;
    private java.sql.Date sqlDate;
    
    @PostConstruct
    public void init() {
        
     pcs = pcFacade.findWithNamedQuery("Pc.findByFilter");
     System.out.println(pcs);
    }
    
    public java.sql.Date sqlDate(java.util.Date idate){
        return new java.sql.Date(idate.getTime());
    }
    
    public String sendPcName(){
        System.out.print(pcName);
        System.out.print(getIdate());
        System.out.print(sqlDate(idate));
        return pcName;
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

    public List<Pc> getPcs() {
        return pcs;
    }

    public void setPcs(List<Pc> pcs) {
        this.pcs = pcs;
    }

    public Date getIdate() {
        return idate;
    }

    public void setIdate(Date idate) {
        this.idate = idate;
    }

    public java.sql.Date getSqlDate() {
        return sqlDate;
    }

    public void setSqlDate(java.sql.Date sqlDate) {
        this.sqlDate = sqlDate;
    }
     
    
}
