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
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *Bean för att lägga beställningar
 * Ändra status på beställngar
 * Sök tidigare beställningar
 * @author srvmng
 */
@Stateless
@Named(value = "orderBean")
public class AddOrder implements Serializable {

    //Abstrakt entity manager
    @EJB ProductsFacade pFacade;
    @EJB ItineraryFacade iFacade;

    //Injekt backing bean för produkter och inloggad användare
    @Inject ProductBean prodBean;
    @Inject LoginBean login;

    private static final long serialVersionUID = 1L;


    private List < Products > initList;
    private List < Products > items;
    private Itinerary item;
    private int searchInt;

    /**
     *Lista med produkter
     */
    @PostConstruct
    public void init() {


        initList = pFacade.findAll();

    }

    /**
     *Constructor för klassen som initierar en ny items.list
     */
    public AddOrder() {
        this.items = new ArrayList < > ();
    }

    /**
     *För test purposes
     * @param quantity
     */
    public void addQuantityToCart(int quantity) {
        System.out.println(quantity);
    }

    /**
     *När man klickar på "lägg till"
     * så läggs en produkt en gång till arraylistan
     * och samtidigt minskar antal produkter i "lagret"
     * @param product
     */
    public void addToCart(Products product) {

        int a = 0;
        items.add(product);
        a++;
        product.setQuantity(product.getQuantity() - a); // minska antal på produkten
        pFacade.edit(product);

        System.out.println(a); //För test purposes

        System.out.println(product.getQuantity());
        addMessage("La till + " + product);

    }

    /**
     *Skapar en lista som mappar hur många gånger en produkt har lagts i varukorgen
     * returnerar sen listan som string
     * @return
     */
    public String orderTable() {

        Map < Products, Integer > countMap = new HashMap < > ();
        for (Products item: items) {
            if (countMap.containsKey(item))
                countMap.put(item, countMap.get(item) + 1);
            else
                countMap.put(item, 1);
        }
        return countMap.toString();
    }

    /**
     *Lägger en beställning
     * och returnerar till menyn
     * @return
     */
    public String add() {

        Itinerary i = new Itinerary();
        i.setUsername(login.sentUsername());
        i.setDatecreated(new java.sql.Date(new java.util.Date().getTime()));
        i.setOrderstatus("Öppet");
        i.setCart(orderTable());
        iFacade.create(i);
        System.out.print(i);
        return "menu";
    }

    /**
     *Uppdaterar en textarea med innehålet som sökfunktionen gav
     * @param event
     */
    public void updateItinerary(AjaxBehaviorEvent event) {
        item = iFacade.find(searchInt);
    }

    /**
     *Skapar och visar ett meddelande när man lägger en produkt i korgen
     * @param summary
     */
    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    //Getters and setters
    public List < Products > getInitList() {
        return initList;
    }

    public void setInitList(List < Products > initList) {
        this.initList = initList;
    }

    public List < Products > getItems() {
        return items;
    }

    public void setItems(List < Products > items) {
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

}