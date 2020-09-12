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

/**
 *
 * @author srvmng
 */
@Named(value="proBean")
@SessionScoped

public class ProductControlBean implements Serializable {
    
    @EJB ProductsFacade prodFacade;
    @Inject ProductBean prodBean;
    
    public List <Products> getAll(){
        
        return prodFacade.findAll();
    }

    
}
