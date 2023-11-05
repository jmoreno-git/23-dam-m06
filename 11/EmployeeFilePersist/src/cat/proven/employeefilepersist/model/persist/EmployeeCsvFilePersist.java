package cat.proven.employeefilepersist.model.persist;

import cat.proven.employeefilepersist.model.Address;
import cat.proven.employeefilepersist.model.Employee;
import java.io.BufferedReader;
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

/**
 * File persistence implementationn for lists of employees in CSV format
 * @author ProvenSoft
 */
public class EmployeeCsvFilePersist implements FilePersistInterface<Employee> {

    /**
     * delimiter to be used in csv format
     */
    private String delimiter = ";";

    /**
     * get delimiter
     * @return delimiter
     */
    public String getDelimiter() {
        return delimiter;
    }

    /**
     * set delimiter
     * @param delimiter new value for delimiter
     */
    public void setDelimiter(String delimiter) {
        this.delimiter = delimiter;
    }

    /**
     * writes a list of employees to a file in binary format
     */
    @Override
    public int writeList(String filename, List<Employee> data) {
        System.out.println("TODO: writeList: CSV"); //TODO
        return 0;
    }

    /**
     * reads a list of employees from a file in binary format
     */
    @Override
    public List<Employee> readList(String filename) {
        System.out.println("TODO: readList: CSV"); //TODO
        return null;
    }

    
  

}
