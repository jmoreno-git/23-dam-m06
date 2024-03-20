import java.util.ArrayList;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Jose
 */
public class CdCatalogContentHandler extends DefaultHandler {

    private String temp;
    private final List<Cd> cdList;
    private Cd cd;

    public CdCatalogContentHandler() {
        cdList = new ArrayList<>();
    }
    
    @Override
    public void characters(char[] buffer, int start, int length) {
        temp = new String(buffer, start, length);
    }

    @Override
    public void startElement(String uri, String localName,
            String qName, Attributes attributes) throws SAXException {
        temp = "";
        String elementName = qName.toLowerCase();
        switch (elementName) {
            case "cd":
                cd = new Cd();
                break;
            default:
                break;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        String elementName = qName.toLowerCase();
        switch (elementName) {
            case "cd":
                cdList.add(cd);
                break;
            case "title":
                cd.setTitle(temp);
                break;
            case "artist":
                cd.setArtist(temp);
                break;
            case "country":
                cd.setCountry(temp);
                break;
            case "company":
                break;
            case "price":
                try {
                    cd.setPrice(Double.parseDouble(temp));
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                break;
            case "year":
                try {
                    cd.setYear(Integer.parseInt(temp));
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }                
                break;
            default:
                break;
        }

    }

    public List<Cd> getCdList() {
        return cdList;
    }

}
