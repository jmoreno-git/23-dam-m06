package cat.proven.schoolmanager.model;

import cat.proven.schoolmanager.model.persistance.SchoolDAOXml;


/**
 *
 * @author ProvenSoft
 */
public class SchoolModel {
   
    private final SchoolDAOXml schoolDAO;

    public SchoolModel() {
        schoolDAO = new SchoolDAOXml();
    }
    
   
    
}
