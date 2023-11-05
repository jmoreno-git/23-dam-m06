
package cat.proven.employeefilepersist.model.persist;

import cat.proven.employeefilepersist.model.Address;
import cat.proven.employeefilepersist.model.Employee;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * File persistence implementationn for lists of employees in binary format
 * @author ProvenSoft
 */
public class EmployeeBinFilePersist implements FilePersistInterface<Employee> {

    /**
     * writes a list of employees to a file in binary format
     */
    @Override
    public int writeList(String filename, List<Employee> data) {
        System.out.println("TODO: writeList BIN");; //TODO
        return 0;
  
    }

    /**
     * reads a list of employees from a file in binary format
     */
    @Override
    public List<Employee> readList(String filename) {
        System.out.println("TODO: readList BIN");
        return null;
    }

   

   
    
}
