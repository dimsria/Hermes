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
package ItineraryUtils;

import Abst.ItineraryFacade;
import Entities.Itinerary;
import MiscUtils.FileExport;
import java.io.IOException;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *Backing bean för att söka tidigare beställningar
 * @author srvmng
 */
@Named(value="searchBean")
@RequestScoped
public class SearchOrder implements Serializable {

    
    
    private int searchNumber;
    private Itinerary item;
    private String result;
    
    @EJB ItineraryFacade iFacade;
    
    /**
     *Sökningsfunktion för beställningar
     * @param searchNumber
     * @return
     * @throws InterruptedException
     * @throws IOException
     */
    public String searchOrder (int searchNumber) throws InterruptedException, IOException{
        
        this.item= iFacade.find(searchNumber);
        if (item==null){
            result= "Hittade ingen beställning med denna nummer";
        }
        else
        {   
            
            item.setCart(item.getCart().toString().replaceAll("[{}]", ""));
            result = "Hittade den följande: "; 
            
        } 
          return result;  
    }
    
    /**
     *Tar in orderid och skriver ut beställningen
     * @param searchNumber
     * @throws InterruptedException
     * @throws IOException
     */
    public void printOrder(int searchNumber) throws InterruptedException, IOException{
        this.item= iFacade.find(searchNumber);
        item.setCart(item.getCart().toString().replaceAll("[{}]", ""));
        FileExport o = new FileExport();
        o.FileDownload(item.toString());
    }
    
    //Getters and Setters
    public int getSearchNumber() {
        return searchNumber;
    }

    public void setSearchNumber(int searchNumber) {
        this.searchNumber = searchNumber;
    }


    public Itinerary getItem() {
        return item;
    }

    public void setItem(Itinerary item) {
        this.item = item;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
