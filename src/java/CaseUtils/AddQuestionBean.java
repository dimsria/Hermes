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
@Named(value = "qBean")@SessionScoped
public class AddQuestionBean implements Serializable {

private static final long serialVersionUID = 1L;

  @EJB ArendeFacade aFacade;
  @Inject ArendeBean aBean;
  @Inject LoginBean login;


  
  public List < Arende > getAllQs(){

    return aFacade.findWithNamedQuery("Arende.findBytest2");
    
  }
  
 public String searchById(Integer id)
{
    return aFacade.find(id).toString();
    
}
  
  public List <Arende> getQuestions(){
      
      return aFacade.findWithNamedQuery("Arende.findByQuestion");
  }

  public int count() {
    return aFacade.count();
  }

  public String add() throws IOException {

    Arende a = new Arende();
    a.setUsername(login.sentUsername());
    a.setArType("Question");
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

    return "updateq";
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