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

import java.io.File;
import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

/**
 *Skapar ett pdf-dokument med ärendeinformation
 * Sparas lokalt på c:
 * @author srvmng
 */
public class SaveToFile {
    
    public SaveToFile(){
        
    }

    public void saveToPDF(String arende) throws IOException{

        PDFont font = PDType1Font.HELVETICA_BOLD;
           
            int fontSize = 14;

            final PDDocument doc = new PDDocument();
            PDPage page = new PDPage(PDRectangle.A4);
           
            doc.addPage(page);
            
        try (PDPageContentStream stream = new PDPageContentStream(doc, page)) {
            PDImageXObject pdImage1 = PDImageXObject.createFromFile("C:/Hermes/logo.png",doc);

            stream.drawImage(pdImage1, -150, 350);
            
            
            String[] myData = arende.split(",");
            stream.beginText();
            stream.setFont(font, fontSize);
            stream.setLeading(15f);
            stream.newLineAtOffset(200, 480);
            for (String s: myData) {
                System.out.println(s);
                stream.showText(s);
                stream.newLineAtOffset(0, -25);
            }
            stream.endText();
            
        }
            doc.save(new File("C:/Hermes/case.pdf"));
            doc.close();
            
        }
    }
    
    

