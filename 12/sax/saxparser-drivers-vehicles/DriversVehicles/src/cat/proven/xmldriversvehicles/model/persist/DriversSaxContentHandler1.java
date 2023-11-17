package cat.proven.xmldriversvehicles.model.persist;

import java.util.HashMap;
import java.util.Map;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Content handler to get list of drivers with their vehicles
 * from xml file
 * @author ProvenSoft
 */
public class DriversSaxContentHandler1 extends DefaultHandler {

    private Map<String, Integer>  data;
    private String driverName;
    private Integer numVehicles;
    private String text;

    @Override
    public void startDocument() {
        data = new HashMap<>();
    }
    
    @Override
    public void endDocument() {
        //nothing to do
    }

    @Override
    public void characters(char [] ch, int start, int length)
        throws SAXException {
        this.text = new String(ch, start, length).trim();
    }
    
    @Override
    public void startElement(String uri, String localName,
        String qName, Attributes attributes) throws SAXException {
        
        switch (qName.toLowerCase()) {
            case "drivers" -> {
                //nothing to do
            }
            case "driver" -> {
                //nothing to do
            }
            case "driver-id", "firstname", "lastname" -> {
                //nothing to do
            }
            case "vehicles" -> {
                numVehicles = 0;
            }
            case "vehicle" -> {
                numVehicles++;
            }
            case "vehicle-id", "name" -> {
                //nothing to do
            }
            default -> {
                //nothing to do
            }
        }
    }

    @Override
    public void endElement(String uri, String localName,
        String qName) throws SAXException {
        
        switch (qName.toLowerCase()) {
            case "drivers" -> {
                //nothing to do
            }
            case "driver" -> {
                data.put(driverName, numVehicles);
            }
            case "driver-id" -> {
            
            }
            case "firstname" -> {
                driverName = text;
            }
            case "lastname" -> {
                driverName = driverName.concat(" ").concat(text);
            }
            case "vehicles" -> {
            
            }
            case "vehicle" -> {
            
            }
            case "vehicle-id" -> {
            
            }
            case "name" -> {
            
            }
            default -> {
                //nothing to do
            }
        }
    }

    public Map<String, Integer> getData() {
        return data;
    }
    
}
