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
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.file.UploadedFile;

/**
 *Edit och Spara produkt
 * 
 * @author srvmng
 */
@Named(value = "protBean")
@SessionScoped

public class ProductTestBean implements Serializable {
    
    private Products valdProd;
    
    @EJB ProductsFacade prodFacade;
    @Inject ProductBean prodBean;
    
    private static final long serialVersionUID = 1L;
    
    private UploadedFile file;

    /**
     *Lista med alla produkter
     * @return
     */
    public List < Products > getAll() {
        
        return prodFacade.findAll();
    }
    public List<Products> getFilter(){
        return prodFacade.findWithNamedQuery("Products.findByQuantityFilter");
    }

    /**
     *Modifierar en produkt och sen skickas
     * vidare för att spara ändringar
     * @param p
     * @return
     */
    public String edit(Products p) {

        prodBean.setProdid(p.getProdid());
        prodBean.setTitle(p.getTitle());
        prodBean.setDescrip(p.getDescrip());
        prodBean.setQuantity(p.getQuantity());
        prodBean.setImg(p.getImg());

        prodFacade.edit(p);

        return "updateproduct";
    }

    /**
     *Hämtar och sparar ändringar från editFunktionen
     * Omredigerar sen till produktsidan
     * @return
     */
    public String save() {

        Products p = new Products(prodBean.getProdid());
        p.setTitle(prodBean.getTitle());
        p.setDescrip(prodBean.getDescrip());
        p.setQuantity(prodBean.getQuantity());
        p.setImg(prodBean.getImg());

        prodFacade.edit(p);
        return "products";
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

    //Getters n Setters
    public UploadedFile getFile() {
        return file;
    }
    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public Products getValdProd() {
        return valdProd;
    }

    public void setValdProd(Products valdProd) {
        this.valdProd = valdProd;
    }
    
    public void onRowEdit(RowEditEvent<Products> event) {
        Products p = new Products(event.getObject().getProdid());
        prodFacade.edit(p);
        FacesMessage msg = new FacesMessage("Produkten ändrad", event.getObject().getProdid().toString());    
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
    public void onRowCancel(RowEditEvent<Products> event) {
        FacesMessage msg = new FacesMessage("Ändringen avbrutten", event.getObject().getProdid().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
    public void onCellEdit(CellEditEvent event) { 
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
        
         
        if(newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell ändrad", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

}