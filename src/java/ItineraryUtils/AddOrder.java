/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ItineraryUtils;


import Abst.ItineraryFacade;
import Abst.ProductsFacade;
import Beans.ProductBean;
import Entities.Itinerary;
import Entities.Products;
import UserUtils.LoginBean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
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
    @EJB ItineraryFacade iFacade;
    
    @Inject ProductBean prodBean;
    @Inject LoginBean login;
    
    
    
    private List <Products> initList;
    private List<Products> items;
    private Itinerary item;
    private int searchInt;
   
    
    
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
    public void addToCart(Products product){
        
        int a =0;
        items.add(product);
        a++;
        product.setQuantity(product.getQuantity()-a);
        pFacade.edit(product);
        System.out.println(a);
        
        
        System.out.println(product.getQuantity());
        addMessage("La till + " + product);

    }

    public String orderTable(){
        
        Map<Products, Integer> countMap = new HashMap<>();
        for (Products item: items) {
      if (countMap.containsKey(item))
          countMap.put(item, countMap.get(item) + 1);
      else
          countMap.put(item, 1);
          }
        return countMap.toString();
    }

    
    public String add(){
        
        Itinerary i = new Itinerary();
        i.setUsername(login.sentUsername());
        i.setDatecreated(new java.sql.Date(new java.util.Date().getTime()));
        i.setOrderstatus("Öppet");
        i.setCart(orderTable());
        iFacade.create(i);
        System.out.print(i);
        return "menu";
    }
    
    public void updateItinerary (AjaxBehaviorEvent event){
        item = iFacade.find(searchInt);
    }
    

    public List <Products> getInitList() {
        return initList;
    }

    public void setInitList(List <Products> initList) {
        this.initList = initList;
    }

    public List<Products> getItems() {
        return items;
    }

    public void setItems(List<Products> items) {
        this.items = items;
    }

    public Itinerary getItem() {
        return item;
    }

    public void setItem(Itinerary item) {
        this.item = item;
    }

    public int getSearchInt() {
        return searchInt;
    }

    public void setSearchInt(int searchInt) {
        this.searchInt = searchInt;
    }
    
    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

   
}
