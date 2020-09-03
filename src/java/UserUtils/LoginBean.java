/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserUtils;

/**
 *
 * @author srvmng
 */
import Abst.UsersFacade;
import Entities.Users;
import java.io.IOException;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;

@RequestScoped
@Named(value = "login")
public class LoginBean {
    
  @EJB UsersFacade uFacade;

  private String username;
  private String firstname;
  private String surname;

  private List < Users > users;  

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getFirstname() {
    return firstname;
  }

  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }

  public String getSurname() {
    
    return surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  public List < Users > getUsers() {
    return users;
  }

  public void setUsers(List < Users > users) {
    this.users = users;
  }

  @PostConstruct
  public void init() {
      
  
    users = uFacade.findAll();

  }
  
  public String sentUsername (){
      username = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        String[] val = username.split(" ");
    if (val.length > 1){
        username = val[0];
    }
    return username;
  }

  public void login() throws IOException {

    FacesContext context = FacesContext.getCurrentInstance();
    context.getExternalContext().getSessionMap().put("user", username);
    context.getExternalContext().getSessionMap().put("firstname", firstname);
    context.getExternalContext().getSessionMap().put("surname", surname);
    context.getExternalContext().redirect("menu.xhtml");
    
    
  }

  public void logout() {
    FacesContext context = FacesContext.getCurrentInstance();
    context.getExternalContext().invalidateSession();
    System.out.println(username);
    try {
      context.getExternalContext().redirect("index.xhtml");
    } catch(IOException e) {}
    
  }

}