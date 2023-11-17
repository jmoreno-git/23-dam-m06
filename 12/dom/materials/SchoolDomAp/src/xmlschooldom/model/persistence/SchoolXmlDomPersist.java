package xmlschooldom.model.persistence;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
import javax.xml.transform.stream.StreamSource;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import xmlschooldom.model.Group;

import xmlschooldom.model.School;
import xmlschooldom.model.Student;

/**
 *
 * @author ProvenSoft
 */
public class SchoolXmlDomPersist implements XmlFilePersist<School> {

    public SchoolXmlDomPersist() {
    }

    /**
     * Read methods *
     */
    @Override
    public School load(String filename) {
        School school = null;
        try {
            File file = new File(filename);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(file);
            Element schoolElement = document.getDocumentElement();
            schoolElement.normalize();
            //read school data from document
            Element nameElement = (Element) schoolElement.getElementsByTagName("name").item(0);
            String name = nameElement.getTextContent();
            Element groupsElement = (Element) schoolElement.getElementsByTagName("groups").item(0);
            List<Group> groups = readGroups(groupsElement);
            school = new School(name, groups);
        } catch (FileNotFoundException ex) {
            //ex.printStackTrace(System.out);
            //System.exit(1);
            school = null;
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            //ex.printStackTrace(System.out);
            //System.exit(1);
            school = null;
        }

        return school;
    }

    /**
     * reads a groups element
     * @param groupsElement the groups element to read
     * @return list of groups with read data
     */
    private List<Group> readGroups(Element groupsElement) {
        List<Group> groups = new ArrayList<>();
        NodeList groupNodes = groupsElement.getChildNodes();
        for (int i = 0; i < groupNodes.getLength(); i++) {
            Node groupNode = groupNodes.item(i);
            if (groupNode instanceof Element groupElement) {
                Group group = readGroup(groupElement);
                groups.add(group);
            }
        }
        return groups;
    }

    /**
     * reads group element
     * @param groupElement the group element to read
     * @return group object
     */
    private Group readGroup(Element groupElement) {
        Group group = new Group();
        String name = groupElement.getElementsByTagName("name").item(0).getTextContent();
        String tutor = groupElement.getElementsByTagName("tutor").item(0).getTextContent();
        String curriculum = groupElement.getElementsByTagName("curriculum").item(0).getTextContent();
        Element studentsElement = (Element) groupElement.getElementsByTagName("students").item(0);
        List<Student> students = readStudents(studentsElement);
        group = new Group(name, tutor, curriculum, students);
        return group;
    }

    /**
     * reads students element
     * @param studentsElement the students element to read
     * @return a list of students
     */
    private List<Student> readStudents(Element studentsElement) {
        List<Student> students = new ArrayList<>();
        NodeList studentNodes = studentsElement.getChildNodes();
        for (int i = 0; i < studentNodes.getLength(); i++) {
            Node studentNode = studentNodes.item(i);
            if (studentNode instanceof Element studentElement) {
                Student student = readStudent(studentElement);
                students.add(student);
            }
        }
        return students;
    }

    /**
     * read a student element
     * @param studentElement the student element to read
     * @return a student object
     */
    private Student readStudent(Element studentElement) {
        Student student = new Student();
        String id = studentElement.getAttribute("id");
        String name = studentElement.getElementsByTagName("name").item(0).getTextContent();
        String surname = studentElement.getElementsByTagName("surname").item(0).getTextContent();
        Element emailsElement = (Element) studentElement.getElementsByTagName("emails").item(0);
        List<String> emails = readEmails(emailsElement);
        String sAge = studentElement.getElementsByTagName("age").item(0).getTextContent();
        int age = Integer.parseInt(sAge);
        student = new Student(id, name, surname, emails, age);
        return student;
    }

    /**
     * reads emails element
     * @param emailsElement the emails element to read
     * @return email list
     */
    private List<String> readEmails(Element emailsElement) {
        List<String> emails = new ArrayList<>();
        NodeList emailNodes = emailsElement.getChildNodes();
        for (int i = 0; i < emailNodes.getLength(); i++) {
            Node emailNode = emailNodes.item(i);
            if (emailNode instanceof Element emailElement) {
                String email = emailElement.getTextContent();
                emails.add(email);
            }
        }
        return emails;
    }

    /**
     * write methods *
     */
    @Override
    public boolean save(String filename, School school) {
        boolean success = false;
        try {
            File file = new File(filename);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            //create empty document
            Document document = db.newDocument();
            //create and append root element
            Element schoolElement = document.createElement("school");
            document.appendChild(schoolElement);
            //create amb append child elements to root
            Element nameElement = XmlFilePersist.createSimpleTypeElement(document, "name", school.getName());
            schoolElement.appendChild(nameElement);
            Element groupsElement = document.createElement("groups");
            schoolElement.appendChild(groupsElement);
            school.getGroups().forEach((t) -> {
                // create child element
                Element groupElement = createGroupElement(document, t);
                // add to company element.
                groupsElement.appendChild(groupElement);
            });
            //write to file
            TransformerFactory tranformerFactory = TransformerFactory.newInstance();
            Transformer transformer = tranformerFactory.newTransformer();
            // Configure transformer -> set transformer properties.
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
            // define source and result for tranformer.
            Source source = new DOMSource(document);
            Result result = new StreamResult(file);
            transformer.transform(source, result);
            success = true;
        } catch (TransformerConfigurationException ex) {
//            Logger.getLogger(SchoolXmlDomPersist.class.getName()).log(Level.SEVERE, null, ex);
            success = false;
        } catch (TransformerException ex) {
//            Logger.getLogger(SchoolXmlDomPersist.class.getName()).log(Level.SEVERE, null, ex);
            success = false;
        } catch (ParserConfigurationException ex) {
//            Logger.getLogger(SchoolXmlDomPersist.class.getName()).log(Level.SEVERE, null, ex);
            success = false;
        }
        return success;
    }

    /**
     * creates a group element
     * @param document the document to work with
     * @param object group object
     * @return element
     */
    private Element createGroupElement(Document document, Group object) {
        // create group element.
        Element element = document.createElement("group");
        // create simple type elements.
        Element child;
        child = XmlFilePersist.createSimpleTypeElement(document, "name", object.getName());
        element.appendChild(child);
        child = XmlFilePersist.createSimpleTypeElement(document, "tutor", object.getTutor());
        element.appendChild(child);
        child = XmlFilePersist.createSimpleTypeElement(document, "curriculum", object.getCurriculum());
        element.appendChild(child);
        Element studentsElement = document.createElement("students");
        element.appendChild(studentsElement);
        // create complex type elements.
        object.getStudents().forEach((t) -> {
            // create child element
            Element studentElement = createStudentElement(document, t);
            // add to company element.
            studentsElement.appendChild(studentElement);
        });
        return element;
    }

    /**
     * creates a student element
     * @param document the document to work with
     * @param object student object
     * @return element
     */
    private Element createStudentElement(Document document, Student object) {
        // create student element.
        Element element = document.createElement("student");
        //create attribute
        element.setAttribute("id", object.getId());
        // create simple type elements.
        Element child;
        child = XmlFilePersist.createSimpleTypeElement(document, "name", object.getName());
        element.appendChild(child);
        child = XmlFilePersist.createSimpleTypeElement(document, "surname", object.getSurname());
        element.appendChild(child);
        Element emailsElement = document.createElement("emails");
        element.appendChild(emailsElement);
        // create complex type elements.
        object.getEmails().forEach((t) -> {
            // create child element
            Element emailElement = XmlFilePersist.createSimpleTypeElement(document, "email", t);
            // add to company element.
            emailsElement.appendChild(emailElement);
        });
        //
        child = XmlFilePersist.createSimpleTypeElement(document, "age", String.valueOf(object.getAge()));
        element.appendChild(child);
        return element;
    }

    /**
     * saves a list of students in xml file
     * @param filename the name of the file
     * @param students the list of students
     * @return true isf successful, false otherwise
     */
    public boolean saveStudentList(String filename, List<Student> students) {
        boolean success = false;
        try {
            File file = new File(filename);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            //create empty document
            Document document = db.newDocument();
            //create and append root element
            Element studentsElement = document.createElement("students");
            document.appendChild(studentsElement);
            //create amb append child elements to root
            students.forEach((s) -> {
                // create child element
                Element studentElement = createStudentElement(document, s);
                // add to company element.
                studentsElement.appendChild(studentElement);
            });
            //write to file
            TransformerFactory tranformerFactory = TransformerFactory.newInstance();
            Transformer transformer = tranformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            // define source and result for tranformer.
            Source source = new DOMSource(document);
            Result result = new StreamResult(file);
            transformer.transform(source, result);
            success = true;
        } catch (TransformerConfigurationException ex) {
//            Logger.getLogger(SchoolXmlDomPersist.class.getName()).log(Level.SEVERE, null, ex);
            success = false;
        } catch (TransformerException ex) {
//            Logger.getLogger(SchoolXmlDomPersist.class.getName()).log(Level.SEVERE, null, ex);
            success = false;
        } catch (ParserConfigurationException ex) {
//            Logger.getLogger(SchoolXmlDomPersist.class.getName()).log(Level.SEVERE, null, ex);
            success = false;
        }
        return success;
    }

    /**
     * transforms source file into target file using xslfile
     * @param sourceXmlFle the file to transform
     * @param targetHtmlFile the file to save result
     * @param xslFile the xsl file to use in transformation
     * @return true isf successful, false otherwise
     */
    public boolean transformFile(String sourceXmlFle, String targetHtmlFile, String xslFile) {
        boolean success = false;
        try {
            Source input  = new StreamSource(sourceXmlFle);
            Source xsl    = new StreamSource(xslFile);
            Result output = new StreamResult(targetHtmlFile);
            // Perform transformation.
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer(xsl);
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(input, output);
            success = true;
        } catch (TransformerException ex) {
            //Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
            success = false;
        } 
        return success;
    }

}
