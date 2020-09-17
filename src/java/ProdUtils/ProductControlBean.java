/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProdUtils;

import Abst.ProductsFacade;
import Beans.ProductBean;
import Entities.Products;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.model.file.UploadedFile;

/**
 *
 * @author srvmng
 */
@Named(value="proBean")
@SessionScoped

public class ProductControlBean implements Serializable {
    
    @EJB ProductsFacade prodFacade;
    @Inject ProductBean prodBean;
    private UploadedFile file;
    
    
    public UploadedFile getFile() {
        return file;
    }
    public void setFile(UploadedFile file) {
        this.file = file;
    }
    
    public List <Products> getAll(){
        
        return prodFacade.findAll();
    }
    
    public String edit (Products p){
        
        prodBean.setProdid(p.getProdid());
        prodBean.setTitle(p.getTitle());
        prodBean.setDescrip(p.getDescrip());
        prodBean.setQuantity(p.getQuantity());
        prodBean.setImg(p.getImg());
        
        prodFacade.edit(p);
        
        return "updateproduct" ;
    }
    
    public String save() {

    Products p = new Products(prodBean.getProdid());
    p.setTitle(prodBean.getTitle());
    p.setDescrip(prodBean.getDescrip());
    p.setQuantity(prodBean.getQuantity());
    p.setImg(prodBean.getImg());

    prodFacade.edit(p);
    return "products";
  }
    
    public String delete(Products p){
        
        prodFacade.remove(p);
        return null;
    }
    
}
