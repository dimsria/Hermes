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


import Abst.ItineraryFacade;
import Entities.Itinerary;
import MiscUtils.FileExport;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *Backing bean för att söka tidigare beställningar
 * @author srvmng
 */
@Named(value="searchOrderBean")
@RequestScoped
public class SearchOrder1 implements Serializable {

    
    
    private int searchNumber;
    private Itinerary item;
    private String orderResults;
    private String result;
    private List<Itinerary> orders;
    private List<Itinerary> filteredOrders;
    
    @EJB ItineraryFacade iFacade;
    
    @PostConstruct
    public void init(){
        orders= iFacade.findAll();
    }
    
    /**
     * Sökfunktion
     * @param value
     * @param filter
     * @param locale
     * @return
     */
    public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
        if (filterText == null || filterText.equals("")) {
            return true;
        }
        int filterInt = getInteger(filterText);
 
        Itinerary i = (Itinerary) value;
        return i.getUsername().toLowerCase().contains(filterText)
                || i.getOrderid() < filterInt;
    }
    
     private int getInteger(String string) {
        try {
            return Integer.valueOf(string);
        }
        catch (NumberFormatException ex) {
            return 0;
        }
     }
    
    /**
     *Tar in orderid och skriver ut beställningen
     * @param item
     * @throws InterruptedException
     * @throws IOException
     */
    public void printOrder(List<Itinerary> item) throws InterruptedException, IOException{
      
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

    public String getOrderResults() {
        return orderResults;
    }

    public void setOrderResults(String orderResults) {
        this.orderResults = orderResults;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public List<Itinerary> getFilteredOrders() {
        return filteredOrders;
    }

    public void setFilteredOrders(List<Itinerary> filteredOrders) {
        this.filteredOrders = filteredOrders;
    }

    public List<Itinerary> getOrders() {
        return orders;
    }

    public void setOrders(List<Itinerary> orders) {
        this.orders = orders;
    }
}
