package xmlschooldom.model.persistence;

import xmlschooldom.model.School;

public interface SchoolPersist {
    public School load();
    public void save(School school);
    
}
