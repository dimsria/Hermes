/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PcUtils;

import Abst.PcFacade;
import Beans.PcBean;
import Entities.Pc;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author srvmng
 */
@Named(value = "pBean")
@SessionScoped
public class AddPcBean implements Serializable{
    
    @EJB PcFacade pcFacade;
    @Inject PcBean pcBean;
    
    public List<Pc> getAll(){
        return pcFacade.findAll();
    }
    
    public String add(){
        Pc p = new Pc();
        p.setPcName(pcBean.getPcName());
        p.setAvailable(pcBean.getAvailable());
        pcFacade.create(p);
        return "pclist";
    }
    
    public String edit(Pc p){
        
        pcBean.setPcName(p.getPcName());
        pcBean.setAvailable(p.getAvailable());
        
        return "update";
    }
    public String save(){
        Pc p = new Pc(pcBean.getPcName());
        p.setPcName(pcBean.getPcName());
        p.setAvailable(pcBean.getAvailable());
        pcFacade.edit(p);
        return "pclist";
    }
    
    public String delete (Pc p){
        
        pcFacade.remove(p);
        return "null";
    }
    
}
