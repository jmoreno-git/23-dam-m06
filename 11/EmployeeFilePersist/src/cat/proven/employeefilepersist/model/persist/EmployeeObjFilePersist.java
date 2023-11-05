package cat.proven.employeefilepersist.model.persist;

import cat.proven.employeefilepersist.model.Employee;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ProvenSoft
 */
public class EmployeeObjFilePersist implements FilePersistInterface<Employee> {

    @Override
    public int writeList(String filename, List<Employee> data) throws IOException {
        int counter = 0;
        try ( ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            for (Employee c : data) {
                oos.writeObject(c);
                counter++;
            }
        }
        return counter;
    }

    @Override
    public List<Employee> readList(String filename)
            throws ClassNotFoundException, IOException {
        List<Employee> data = new ArrayList<>();
        try ( ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename)) ) {
            Object obj;
            while ((obj = ois.readObject()) != null) {
                if (obj instanceof Employee) {
                    Employee p = (Employee) obj;
                    data.add(p);
                }
            }
        } catch (EOFException e) {
            
        }
        return data;
    }

}
