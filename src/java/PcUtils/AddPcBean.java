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
    
    private static final long serialVersionUID = 1L;
    
    public List<Pc> getAll(){
        return pcFacade.findAll();
    }
    
    public String add(){
        Pc p = new Pc();
        p.setPcName(pcBean.getPcName());
        p.setAvailable(pcBean.getAvailable());
        pcFacade.create(p);
        return "editpc";
    }
    
    public String edit(Pc p){
        
        pcBean.setPcName(p.getPcName());
        pcBean.setAvailable(p.getAvailable());
        
        return "updatepc";
    }
    public String save(){
        Pc p = new Pc(pcBean.getPcName());
        p.setPcName(pcBean.getPcName());
        p.setAvailable(pcBean.getAvailable());
        pcFacade.edit(p);
        return "editpc";
    }
    
    public String delete (Pc p){
    
        pcFacade.remove(p);
        return null;
    }
    

    
}
