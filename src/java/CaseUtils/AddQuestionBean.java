/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CaseUtils;

import Abst.ArendeFacade;
import Beans.ArendeBean;
import Entities.Arende;
import UserUtils.LoginBean;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;

import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author srvmng
 */
@Named(value="qBean")
@RequestScoped
public class AddQuestionBean {


    
    @EJB ArendeFacade aFacade;
    @Inject ArendeBean aBean;
    @Inject LoginBean login;
 
    public List <Arende> getAll(){
        
        return aFacade.findAll();
    }
    
    public int count(){
        return aFacade.count();
    }
    
    public String add() throws IOException{
        
        Arende a = new Arende();
        a.setUsername(login.sentUsername());
        a.setArType("Question");
        a.setDatecreated(new java.sql.Date(new java.util.Date().getTime()));
        a.setDescrip(aBean.getDescrip());
        a.setArStatus("Öppet");
        
        aFacade.create(a);
       
        return "menu";
    }
    
    public String edit(Arende a){
        aBean.setArStatus(a.getArStatus());
        return "questions";
    }
    
    public String save (){
        
        Arende a = new Arende(aBean.getId());
        a.setArStatus(aBean.getArStatus());
        return "questions";
    }
    
}
