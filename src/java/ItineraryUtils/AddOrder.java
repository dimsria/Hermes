/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ItineraryUtils;

import Abst.ItineraryFacade;
import Abst.ProductsFacade;
import Beans.ItineraryBean;
import Beans.ProductBean;
import Entities.Products;
import UserUtils.LoginBean;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

/**
 *
 * @author srvmng
 */
@SessionScoped
@Named(value="orderBean")
public class AddOrder implements Serializable {
    
    @EJB ItineraryFacade itFacade;
    @EJB ProductsFacade pFacade;
    @Inject ProductBean prodBean;
    @Inject LoginBean login;
    @Inject ItineraryBean itBean;
    
    private List <Products> products;
    private List <Products> selectedProducts;
    private Products selectedProduct;
    
    @PostConstruct
    public void init (){
        
        setProducts(pFacade.findAll());
        
    }
    

    
  

    public List <Products> getProducts() {
        return products;
    }

    public void setProducts(List <Products> products) {
        this.products = products;
    }

    public List <Products> getSelectedProducts() {
        return selectedProducts;
    }

    public Products getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(Products selectedProduct) {
        this.selectedProduct = selectedProduct;
    }

    public void setSelectedProducts(List <Products> selectedProducts) {
        this.selectedProducts = selectedProducts;
    }
    
    public void onRowSelect(SelectEvent<Products> event) {
        FacesMessage msg = new FacesMessage("Produkten vald", event.getObject().getProdid().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
 
    public void onRowUnselect(UnselectEvent<Products> event) {
        FacesMessage msg = new FacesMessage("Produkten togs bort", event.getObject().getProdid().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
}
