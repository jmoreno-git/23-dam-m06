package cat.proven.game.model.persist;

import cat.proven.game.model.Game;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Content handler for games xml file.
 *
 * @author ProvenSoft
 */
public class GameContentHandler extends DefaultHandler {

    /**
     * the id of the game to retrieve
     */
    private String gameId;
    private Game data;

    private Game game;
    private String text;

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public Game getData() {
        return data;
    }

    @Override
    public void startDocument() throws SAXException {
        //TODO
    }

    @Override
    public void endDocument() throws SAXException {
        //TODO
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
        switch (qName.toLowerCase()) {
            case "game" -> {
                game = new Game();
                game.setId(atts.getValue("id"));
            }
            case "players" -> {
            }
            case "player" -> {
            }
            default -> {
                //nothing to do
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (qName.toLowerCase()) {
            case "game" -> {
                
            }
            case "players" -> {
            }
            case "player" -> {
            }
            default -> {
                //nothing to do
            }
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        text = new String(ch, start, length);
    }
}
