
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Jose
 */
public class TransformDemo {

    public static void main(String[] args) {
        
        TransformDemo ap = new TransformDemo();
        ap.run();
    }
    
    private void run() {
        try {
            // define menu.
            String [] demos = {
                "Document to file", "Node to file", "Document to string"
            };
            // display menu.
            for (int i=0; i<demos.length; i++) {
                System.out.format("%d. %s\n", i+1, demos[i]);
            }
            // read user's option.
            String option = inputString("Choose a demo: ");
            switch (option) {
                case "1": documentToFileDemo(); break;
                case "2": nodeToFileDemo(); break;
                case "3": documentToStringDemo(); break;
            }
        } catch (IOException ex) {
            Logger.getLogger(TransformDemo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private String inputString(String message) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        System.out.print(message);
        return bf.readLine();
    }
    
    /**
     * reads xml document from file and writes it to file.
     * @param xmlInputFilename the input filename
     * @param xmlOutputFilename the output filename
     */
    private void documentToFileDemo() {

         try {
            // Read file names from user.;
            String xmlInputFilename = inputString("Enter source XML file name: ");
            String xmlOutputFilename = inputString("Enter destination XML file name: ");
            //
            File xmlFile = new File(xmlInputFilename);
            if (xmlFile.exists()) {
                // Create a document builder factory..
                DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
                // Use document builder factory.
                DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
                // Parse the document and get a document.
                Document document = documentBuilder.parse(xmlFile); 
                // Create a transformer factory.
                TransformerFactory tranformerFactory = TransformerFactory.newInstance(); 
                // Get a transformer.
                Transformer transformer = tranformerFactory.newTransformer(); 
                // Configure transformer -> set transformer properties.
                transformer.setOutputProperty(OutputKeys.METHOD, "xml");
                transformer.setOutputProperty(OutputKeys.INDENT, "no");
                transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
                transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
                // Create Source and Result for transformer.
                Source source = new DOMSource(document);
                Result result = new StreamResult(new File(xmlOutputFilename));
                // Perform transformation.
                transformer.transform(source, result);
            } else {
                System.out.format("File '%s' not found.\n", xmlInputFilename);
            }
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(TransformDemo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(TransformDemo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(TransformDemo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(TransformDemo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TransformDemo.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * reads xml document from file and writes a given node to file.
     */
    private void nodeToFileDemo() {

        try {
            // Read file names from user.;
            String xmlInputFilename = inputString("Enter source XML file name: ");
            String xmlOutputFilename = inputString("Enter destination XML file name: ");
            //
            File xmlFile = new File(xmlInputFilename);
            if (xmlFile.exists()) {
                // Create a document builder factory..
                DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
                // Use document builder factory.
                DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
                // Parse the document and get a document.
                Document document = documentBuilder.parse(xmlFile); 
                // Ask node tag to get.
                String tag = inputString("Input tag to get first element: ");
                // Get nodes with given tag.
                NodeList nodes = document.getElementsByTagName(tag);
                if (nodes.getLength()>0) {
                    // Get first node with given tag.
                    Node node = nodes.item(0);
                    // Create a transformer factory.
                    TransformerFactory tranformerFactory = TransformerFactory.newInstance(); 
                    // Get a transformer.
                    Transformer transformer = tranformerFactory.newTransformer(); 
                    // Configure transformer -> set transformer properties.
                    transformer.setOutputProperty(OutputKeys.METHOD, "xml");
                    transformer.setOutputProperty(OutputKeys.INDENT, "no");
                    transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
                    transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
                    // Create Source and Result for transformer.
                    Source source = new DOMSource(node);
                    Result result = new StreamResult(new File(xmlOutputFilename));
                    // Perform transformation.
                    transformer.transform(source, result);                    
                } else {
                    System.out.format("Node with tag '%s' no found\n", tag);
                }
            } else {
                System.out.format("File '%s' not found.\n", xmlInputFilename);
            }
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(TransformDemo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(TransformDemo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(TransformDemo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(TransformDemo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TransformDemo.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    /**
     * reads xml document from file and writes it to file.
     */
    private void documentToStringDemo() {
        try {
            // Read file names from user.;
            String xmlInputFilename = inputString("Enter source XML file name: ");;
            //
            File xmlFile = new File(xmlInputFilename);
            if (xmlFile.exists()) {
                // Create a document builder factory..
                DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
                // Use document builder factory.
                DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
                // Parse the document and get a document.
                Document document = documentBuilder.parse(xmlFile); 
                // Create a transformer factory.
                TransformerFactory tranformerFactory = TransformerFactory.newInstance(); 
                // Get a transformer.
                Transformer transformer = tranformerFactory.newTransformer(); 
                // Configure transformer -> set transformer properties.
                transformer.setOutputProperty(OutputKeys.METHOD, "xml");
                transformer.setOutputProperty(OutputKeys.INDENT, "no");
                transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
                transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
                // Create Source and Result for transformer.
                Source source = new DOMSource(document);
                StreamResult result = new StreamResult(new StringWriter());
                // Perform transformation.
                transformer.transform(source, result);
                System.out.println(result.getWriter().toString());
            } else {
                System.out.format("File '%s' not found.\n", xmlInputFilename);
            }
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(TransformDemo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(TransformDemo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(TransformDemo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(TransformDemo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TransformDemo.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
}
