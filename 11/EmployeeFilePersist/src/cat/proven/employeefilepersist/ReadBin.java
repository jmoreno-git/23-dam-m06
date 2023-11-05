
package cat.proven.employeefilepersist;

import cat.proven.employeefilepersist.model.Employee;
import cat.proven.employeefilepersist.model.persist.EmployeeBinFilePersist;
import java.util.List;
import cat.proven.employeefilepersist.model.persist.FilePersistInterface;

/**
 *
 * @author ProvenSoft
 */
public class ReadBin {

    public static void main(String[] args) {
        String filename = "mystaff.txt"; //TODO read this from arguments
        //TODO treat all errors.
        FilePersistInterface persister = new EmployeeBinFilePersist();
        List<Employee> data = persister.readList(filename);
        System.out.format("TODO: Show data"); //TODO
    }
    
  
} 
