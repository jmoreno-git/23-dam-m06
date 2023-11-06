package schooldomappt.model.persistence;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import schooldomappt.model.School;

/**
 *
 * @author Alumne
 */
public class SchoolXmlDomPersist implements SchoolPersist{
    private School mSchool;
    private String filename;
   
    
    public SchoolXmlDomPersist(){}
    
    public SchoolXmlDomPersist(String filename)
    {
        this.filename = filename;
    }
    
    public void setFileName(String filename)
    {
        this.filename = filename;
    }

    @Override
    public School load() {
         
        try{
            File file = new File(filename);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(file);
            document.getDocumentElement().normalize();
            System.out.println("Root element " + document.getDocumentElement().getNodeName());
        }catch(FileNotFoundException ex)
        {
            ex.printStackTrace(System.out);
            System.exit(1);
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            ex.printStackTrace(System.out);
            System.exit(1);
        }
        
        //TODO
        return null;
    }
    
 
    @Override
    public void save(School list) {
        //TODO
    }


   
}
