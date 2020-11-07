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
package CaseUtils;

import Abst.ArendeFacade;
import Entities.Arende;
import java.io.Serializable;
import java.util.List;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.event.RowEditEvent;

/**
 * CrudBean för ärende
 *Exakt samma som AddIncidentBean
 * förutom att typfältet är "Question"
 * @author srvmng
 */
@Named(value = "cqBean")
@RequestScoped
public class ControlQuestionBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private List <Arende> questions;
    private List <Arende> filteredCases;
    
    @EJB ArendeFacade aFacade;

    /**
     *Lista med ärenden som är Questions
     *
     */
    @PostConstruct
    public void init(){

        questions = aFacade.findWithNamedQuery("Arende.findByQuestion");
    }

    /**
     *Sökfunktion som returnerar ärende med angivna id-numret
     * @param id
     * @return
     */
    public String searchById(Integer id) {
        return aFacade.find(id).toString();

    }
    
    /**
     *Antal ärende
     * @return
     */
    public int count() {
        return aFacade.count();
    }

    /**
     *Instant edit och spara på ärendet
     * @param event
     */
    public void onRowEdit(RowEditEvent<Arende> event) {
        
        Arende i = (Arende)event.getObject();
        aFacade.edit(i);
        System.out.print(i.getId());// Test purposes
        
        FacesMessage msg = new FacesMessage("Ärendet", event.getObject().getId().toString() + " " + "modifierades");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void onRowCancel(RowEditEvent<Arende> event) {
        FacesMessage msg = new FacesMessage("Ändringen avbrutten");
        FacesContext.getCurrentInstance().addMessage(null, msg);
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
 
        Arende a = (Arende) value;
        return a.getUsername().toLowerCase().contains(filterText)
                || a.getArStatus().toLowerCase().contains(filterText)
                || a.getDescrip().toLowerCase().contains(filterText)
                || a.getId() < filterInt;
    }
    
     private int getInteger(String string) {
        try {
            return Integer.valueOf(string);
        }
        catch (NumberFormatException ex) {
            return 0;
        }
     }
    
    //Getters n Setters
    public void setQuestions(List <Arende> questions) {
        this.questions = questions;
    }

    public List <Arende> getQuestions() {
        return questions;
    }

    public List <Arende> getFilteredCases() {
        return filteredCases;
    }

    public void setFilteredCases(List <Arende> filteredCases) {
        this.filteredCases = filteredCases;
    }



}