
package cat.proven.game.model.persist;

import cat.proven.game.model.Game;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;

/**
 * SAX XML file reader for game.
 * @author ProvenSoft
 */
public class XmlSaxGame {
    
    private String filename;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
    
    /**
     * loads game data from xml file given the game id.
     * @param gameId the id of the game to retrieve
     * @return game with given id or null in case of error 
     */
     public Game load(String gameId) {
        Game data = null;
        System.out.println(filename);
        //get a SAXParserFactory
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try (InputStream xmlInput = new FileInputStream(filename)) {
            //get a SAXParser
            SAXParser saxParser = factory.newSAXParser();
            //instantiate a proper content handler
            GameContentHandler handler = new GameContentHandler();
            //parse xml file
            saxParser.parse(xmlInput, handler);
            //get read data from handler
            data = handler.getData();
        } catch (FileNotFoundException ex) {
            data = null;
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            data = null;
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            data = null;
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            data = null;
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        }
         return data;
     }
     
}
