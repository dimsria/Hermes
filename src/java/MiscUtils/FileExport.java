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
package MiscUtils;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

/**
 *Bean som hämtar info, skapar ärendet till pdf och skickar det ut direkt för nedladdning
 * Anropas när man bekräftar valet på ärendet
 * @author srvmng
 */
@Named(value="downBean")
@SessionScoped
public class FileExport implements  Serializable{
    
    public FileExport(){//Konstruktor
        
    }
    
    
    public void FileDownload(String arende) throws InterruptedException, IOException {
        
        Thread.sleep(1000);//1 sekunds paus mellan
        String fileName = "case.pdf";
        String contentType = "application/pdf";
        PDFont font = PDType1Font.HELVETICA_BOLD;
        int fontSize = 12;
        
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        ec.responseReset();
        ec.setResponseContentType(contentType);
        ec.setResponseHeader("Content-Disposition", "attachment; filename=\"" + fileName  + "\"");
        OutputStream output = ec.getResponseOutputStream();// skicka direkt till output från instance
        
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");// format tiden
        final PDDocument doc = new PDDocument();
        PDPage page = new PDPage(PDRectangle.A4);
        doc.addPage(page);
        try (PDPageContentStream stream = new PDPageContentStream(doc, page)) {
            PDImageXObject pdImage1 = PDImageXObject.createFromFile("C:\\Users\\srvmng\\Documents\\NetBeansProjects\\Hermes\\web\\tmp\\logo.png",doc);

            stream.drawImage(pdImage1, -150, 350);
            
            
            String[] myData = arende.split(",");
            stream.beginText();
            stream.setFont(font, fontSize);
            stream.setLeading(15f);
            stream.newLineAtOffset(200, 480);
            for (String s: myData) {// Iterator som läsare ärendets värde
                System.out.println(s);
                stream.showText(s);
                stream.newLineAtOffset(0, -25);// nya linjer varje 25px
                 }
            stream.newLineAtOffset(0, 0);
            String formatDateTime = now.format(formatter);
            stream.showText("Tid:" + " " + formatDateTime);// Lägga tiden ärendet skapades
            stream.endText();
            
            }
            
        doc.save(output);
        doc.close();
        
        fc.responseComplete();
               
         
        }

 
    
}
