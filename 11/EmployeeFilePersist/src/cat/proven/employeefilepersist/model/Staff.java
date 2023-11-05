package cat.proven.employeefilepersist.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ProvenSoft
 */
public class Staff {

    /**
     * generates test data for the application
     *
     * @return list of employees
     */
    public List<Employee> generateTestData() {
        List<Employee> data = new ArrayList<>();
        data.add(new Employee("phone001", "name001", 11, false, 1001.0, new Address("Street01", 11, "9311")));
        data.add(new Employee("phone002", "name002", 12, false, 1001.0, new Address("Street02", 12, "9312")));
        data.add(new Employee("phone003", "name003", 13, true, 1001.0, new Address("Street03", 13, "9313")));
        data.add(new Employee("phone004", "name004", 14, false, 1001.0, new Address("Street04", 14, "9314")));
        data.add(new Employee("phone005", "name005", 15, true, 1001.0, new Address("Street05", 15, "9315")));
        return data;
    }
}
