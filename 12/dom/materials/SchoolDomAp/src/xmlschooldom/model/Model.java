package xmlschooldom.model;

import java.util.ArrayList;
import java.util.List;
import xmlschooldom.model.persistence.SchoolXmlDomPersist;

/**
 *
 * @author ProvenSoft
 */
public class Model {

    public School school;

    public Model() {

    }

    public School getSchool() {
        return school;
    }

    public boolean readSchool(String filename) {
        SchoolXmlDomPersist persister = new SchoolXmlDomPersist();
        this.school = persister.load(filename);
        return (this.school != null);
    }

    public boolean writeSchool(String filename) {
        SchoolXmlDomPersist persister = new SchoolXmlDomPersist();
        boolean result = persister.save(filename, school);
        return result;
    }

    public boolean writeMinorStudents(String filename) {
        final int MINOR_AGE = 18;
        SchoolXmlDomPersist persister = new SchoolXmlDomPersist();
        List<Student> minorStudList = new ArrayList<>();
        school.getGroups()
            .forEach(g -> {
                g.getStudents()
                    .stream()
                    .filter(s -> (s.getAge() < MINOR_AGE))
                    .forEachOrdered(s -> {
                        minorStudList.add(s);
                    });
            });
        boolean result = persister.saveStudentList(filename, minorStudList);
        return result;
    }  
    
    public boolean writeStudentsByGroup(String filename, Group group) {
        SchoolXmlDomPersist persister = new SchoolXmlDomPersist();
        List<Student> studList = school.getStudentsByGroup(group);
        boolean result = persister.saveStudentList(filename, studList);
        return result;
    }     

    public boolean transformXmlToHtml(String sourceXmlFle, String targetHtmlFile, String xslFile) {
        SchoolXmlDomPersist persister = new SchoolXmlDomPersist();
        boolean result = persister.transformFile(sourceXmlFle, targetHtmlFile, xslFile);
        return result;
    }
    
}
