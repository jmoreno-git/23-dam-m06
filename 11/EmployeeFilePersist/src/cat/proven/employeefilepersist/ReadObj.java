
package cat.proven.employeefilepersist;

import cat.proven.employeefilepersist.model.Employee;
import cat.proven.employeefilepersist.model.persist.EmployeeObjFilePersist;
import java.util.List;
import cat.proven.employeefilepersist.model.persist.FilePersistInterface;
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ProvenSoft
 */
public class ReadObj {

    public static void main(String[] args) {
        String filename = "mystaff.txt"; //TODO read this from arguments
        //TODO treat all errors.
        FilePersistInterface persister = new EmployeeObjFilePersist();
        try {
            List<Employee> data = persister.readList(filename);
            //System.out.format("TODO: Show data"); //TODO
            System.out.println(data);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ReadObj.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ReadObj.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ReadObj.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
     
}
