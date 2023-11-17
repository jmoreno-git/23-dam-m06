import java.util.ArrayList;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Content handler for SAX parsing of bank accounts
 * Only retrieves total amount
 */
public class BankContentHandler2 extends DefaultHandler {

    private String temp;
    private double totalAmount;

    /**
     * When the parser encounters plain text (not XML elements), it calls(this
     * method, which accumulates them in a string buffer
     */
    @Override
    public void characters(char[] buffer, int start, int length) {
        temp = new String(buffer, start, length);
    }

    /**
     * Every time the parser encounters the beginning of a new element, it calls
     * this method, which resets the string buffer
     */
    @Override
    public void startElement(String uri, String localName,
            String qName, Attributes attributes) throws SAXException {
        temp = "";
        switch (qName.toLowerCase()) {
            case "bank" -> //initialize value
                totalAmount = 0.0;
        }
    }

    /**
     * When the parser encounters the end of an element, it calls this method
     */
    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {

        switch (qName.toLowerCase()) {
            case "amt" -> //set attribute to object
                totalAmount += Double.parseDouble(temp);
            //TODO: consider catching conversion exception
        }
    }

    public double getTotalAmount() {
        return totalAmount;
    }



}

