package schooldomappt.model.persistence;

import java.util.List;
import schooldomappt.model.School;

public interface SchoolPersist {
    public School load();
    public void save(School school);
    
}
