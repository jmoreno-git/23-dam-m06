
package cat.proven.employeefilepersist;

import cat.proven.employeefilepersist.model.Employee;
import cat.proven.employeefilepersist.model.persist.EmployeeCsvFilePersist;
import java.util.List;
import cat.proven.employeefilepersist.model.persist.FilePersistInterface;

/**
 *
 * @author ProvenSoft
 */
public class ReadCsv {
    public static void main(String[] args) {
        String filename = "mystaff.txt"; //TODO read this from arguments
        String delimiter = ":";
        //TODO treat all errors.
        FilePersistInterface persister = new EmployeeCsvFilePersist();
        ((EmployeeCsvFilePersist) persister).setDelimiter(delimiter);
        List<Employee> data = persister.readList(filename);
        System.out.format("TODO: Show data"); //TODO
      
    }
    
    
}
