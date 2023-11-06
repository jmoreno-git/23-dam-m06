package xmlschooldom.model.persistence;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
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
public class SchoolXmlDomPersist implements SchoolPersist {

    private String filename;

    public SchoolXmlDomPersist() {
    }

    public SchoolXmlDomPersist(String filename) {
        this.filename = filename;
    }

    public void setFileName(String filename) {
        this.filename = filename;
    }

    @Override
    public School load() {
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

    @Override
    public void save(School list) {
        //TODO
    }

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

}
