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
@Named(value = "iBean")
@SessionScoped
public class AddIncidentBean implements Serializable {

  @EJB ArendeFacade aFacade;
  @Inject ArendeBean aBean;
  @Inject LoginBean login;

  public List < Arende > getAllIncs() {

    return aFacade.findWithNamedQuery("Arende.findByIncident");
  }

  public int count() {
    return aFacade.count();
  }

  public String add() throws IOException {

    Arende a = new Arende();
    a.setUsername(login.sentUsername());
    a.setArType("Incident");
    a.setDatecreated(new java.sql.Date(new java.util.Date().getTime()));
    a.setDescrip(aBean.getDescrip());
    a.setArStatus("Öppet");

    aFacade.create(a);

    return "menu";
  }

  public String edit(Arende a) {

    aBean.setId(a.getId());
    aBean.setArStatus(a.getArStatus());
    aBean.setArType(a.getArType());
    aBean.setDatecreated(a.getDatecreated());
    aBean.setDescrip(a.getDescrip());
    aBean.setUsername(a.getUsername());
    
    return "updateinc" ;
  
  }

  public String save() {

    Arende a = new Arende(aBean.getId());
    a.setArStatus(aBean.getArStatus());
    a.setArType(aBean.getArType());
    a.setDescrip(aBean.getDescrip());
    a.setUsername(aBean.getUsername());
    a.setDatecreated(aBean.getDatecreated());

    aFacade.edit(a);
    return "arenden";
  }

}