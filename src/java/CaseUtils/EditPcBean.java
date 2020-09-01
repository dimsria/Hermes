/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CaseUtils;

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
@Named(value="ePcBean")
@SessionScoped
public class EditPcBean implements Serializable {
    
    @EJB PcFacade pcFacade;
    @Inject PcBean pcBean;
    
    public List <Pc> getAll(){
        
        return pcFacade.findAll();
    }
    
    public int count(){
        return pcFacade.count();
    }
    
    public String edit(Pc p){
        pcBean.setPcName(p.getPcName());
        pcBean.setAvailable(p.getAvailable());
        return "pcs";
    }
    
    public String save(){
        Pc p = new Pc(pcBean.getPcName());
        p.setAvailable(pcBean.getAvailable());
        pcFacade.edit(p);
        return "pcs";
    }
    
}
