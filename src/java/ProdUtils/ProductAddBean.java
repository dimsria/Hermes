/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProdUtils;

import Abst.ProductsFacade;
import Entities.Products;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;

/**
 *
 * @author srvmng
 */
@SessionScoped
@Named(value="proAddBean")
public class ProductAddBean implements Serializable{
    
  
    
    private Integer prodid;
    private String title;
    private String descrip;
    private UploadedFile img;
    private int quantity;
    
     
    @EJB ProductsFacade prFacade;
    
    public List <Products> getAll(){
        
        return prFacade.findAll();
    }
    
    public void handleFileUpload(FileUploadEvent event) {
        System.out.println("beans.AddBean.handleFileUpload()");
        img = event.getFile();
        
    }

    public Integer getProdid() {
        return prodid;
    }

    public void setProdid(Integer prodid) {
        this.prodid = prodid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    public UploadedFile getImg() {
        return img;
    }

    public void setImg(UploadedFile img) {
        this.img = img;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
  
        public String store(){
               
        if(img!=null){
            try{
               
                InputStream fin = img.getInputStream();
                Class.forName("com.mysql.cj.jdbc.Driver");
                Properties properties = new Properties();
                properties.setProperty("user", "root");
                properties.setProperty("password", "22420Kos!!");
                properties.setProperty("useSSL", "false");
                
                String url="jdbc:mysql://localhost:3306/demo?zeroDateTimeBehavior=convertToNull";
                Connection con = DriverManager.getConnection(url, properties);
                PreparedStatement ps= con.prepareStatement("insert into products(title,descrip,img,quantity)values(?,?,?,?)");
                ps.setString(1, title);
                ps.setString(2, descrip);
                ps.setBinaryStream(3, fin, img.getSize());
                ps.setInt(4, quantity);
   
                ps.executeUpdate();
              
            }       
            catch(IOException | ClassNotFoundException | SQLException e){
                System.out.println("Exception-File Upload." + e.getMessage());
            }
        }
        else
        {
            System.out.print("Error no file!");
        }
        return "products";
    }
    
}
