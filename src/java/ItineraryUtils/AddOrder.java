/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ItineraryUtils;


import Abst.ProductsFacade;
import Beans.ProductBean;
import Entities.Itinerary;
import Entities.Products;
import UserUtils.LoginBean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author srvmng
 */
@SessionScoped 
@Named(value="orderBean")
public class AddOrder implements Serializable {
    
    
    @EJB ProductsFacade pFacade;
    
    @Inject ProductBean prodBean;
    @Inject LoginBean login;
    
    
    
    private List <Products> initList;
    private List<String> items;
    private int quantity;
    
    @PostConstruct
    public void init (){
        
        initList= pFacade.findAll();
   
    }
        
    public AddOrder(){
        this.items = new ArrayList<>();
    }
    
    public void addQuantityToCart(int quantity){
        System.out.println(quantity);
    }
    public void addToCart(String product){
        

        items.add(product);
        System.out.println(items);
        
        
    }

    
    public String add(){
        
        Itinerary i = new Itinerary();
        i.setUsername(login.sentUsername());
        i.setDatecreated(new java.sql.Date(new java.util.Date().getTime()));
        
        
        
        return "cart";
    }


    public List <Products> getInitList() {
        return initList;
    }

    public void setInitList(List <Products> initList) {
        this.initList = initList;
    }

    public List<String> getItems() {
        return items;
    }

    public void setItems(List<String> items) {
        this.items = items;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }



   
}
