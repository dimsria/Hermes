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
package ProdUtils;

import Abst.ProductsFacade;
import Beans.ProductBean;
import Entities.Products;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.RowEditEvent;

/**
 *Edit och Spara produkt
 * 
 * @author srvmng
 */
@Named(value = "protBean")
@ViewScoped

public class ProductControlBean implements Serializable {
    
    
    @EJB ProductsFacade prodFacade;
    @Inject ProductBean prodBean;
    
    private static final long serialVersionUID = 1L;
    private List <Products> products;
    
    /**
     *Lista med alla produkter
     * 
     */
    @PostConstruct
    public void init() {
        
        products = prodFacade.findAll();
    }

    /**
     *Tar bort en produkt
     * @param p
     * @return
     */
    public String delete(Products p) {

        prodFacade.remove(p);
        return null;
    }

    
    /**
     *Listener för produktens ändringar
     * Sparar object när man bekräftar
     * @param event
     */
    public void onRowEdit(RowEditEvent<Products> event) {
        
        Products p = (Products)event.getObject();
        prodFacade.edit(p);
        System.out.print(p.getQuantity());// Test purposes
        
        FacesMessage msg = new FacesMessage("Produkten", event.getObject().getTitle() + " " + "ändrades.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
    /**
     *Informerar ifall man ångrar ändringen
     * @param event
     */
    public void onRowCancel(RowEditEvent<Products> event) {
        FacesMessage msg = new FacesMessage("Ändringen avbrutten");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    
    //Getters n Setters
    public List <Products> getProducts() {
        return products;
    }

    public void setProducts(List <Products> products) {
        this.products = products;
    }

}