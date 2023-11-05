
package cat.proven.employeefilepersist;

import cat.proven.employeefilepersist.model.Employee;
import cat.proven.employeefilepersist.model.Staff;
import cat.proven.employeefilepersist.model.persist.EmployeeBinFilePersist;
import java.util.List;
import cat.proven.employeefilepersist.model.persist.FilePersistInterface;

/**
 *
 * @author ProvenSoft
 */
public class WriteBin {

    public static void main(String[] args) {
        String filename = "mystaff.txt"; //TODO read this from arguments
        Staff model = new Staff();
        List<Employee> data = model.generateTestData();
        //TODO treat all errors.
        FilePersistInterface persister = new EmployeeBinFilePersist();
        int written = persister.writeList(filename, data);
        System.out.format("%d employees written\n", written);
    }
    
}
