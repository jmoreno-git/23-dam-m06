package xmlschooldom.model;

import xmlschooldom.model.persistence.SchoolPersist;
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
        SchoolXmlDomPersist persister = new SchoolXmlDomPersist(filename);
        this.school = persister.load();
        boolean result;
        return (this.school != null);
    }
    
}
