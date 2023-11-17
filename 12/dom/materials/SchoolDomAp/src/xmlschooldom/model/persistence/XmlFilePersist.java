package xmlschooldom.model.persistence;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

public interface XmlFilePersist<T> {
    
    /**
     * reads object data from xml file 
     * @param filename the name of the file to read
     * @return T with read data or null in case of error
     */
    public T load(String filename);
    /**
     * saves object  data to xmml file
     * @param filename the name of the file to write
     * @param data object to save to file
     * @return true if successful, false otherwise
     */
    public boolean save(String filename, T data);
    
    /**
     * creates a DOM simple type element
     * @param document the document to work with
     * @param elementName the name of the element to create
     * @param elementValue the value of the text node
     * @return 
     */
    public static Element createSimpleTypeElement(Document document, String elementName, String elementValue) {
        // create element.
        Element element = document.createElement(elementName);
        // create text node.
        Text textNode = document.createTextNode(elementValue);
        // append text node to element.
        element.appendChild(textNode);
        return element;
    }
    
}
