package person;

/**
 *
 * @author ProvenSoft
 */
public class Employee extends Person {
    private double salary;
    private String department;
    private int hiringYear;

    public Employee() {
    }

    public Employee( 
            String nif, 
            String name, 
            String address, 
            String phone, 
            int birthYear,
            double salary, 
            String department, 
            int hiringYear
    ) {
        super(nif, name, address, phone, birthYear);
        this.salary = salary;
        this.department = department;
        this.hiringYear = hiringYear;
    }

    public Employee(Employee other) {
        super(other);
        this.salary = other.salary;
        this.department = other.department;
        this.hiringYear = other.hiringYear;
    }
    
    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getHiringYear() {
        return hiringYear;
    }

    public void setHiringYear(int hiringYear) {
        this.hiringYear = hiringYear;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Employee{");
        sb.append(super.toString());
        sb.append("salary=").append(salary);
        sb.append(", department=").append(department);
        sb.append(", hiringYear=").append(hiringYear);
        sb.append('}');
        return sb.toString();
    }
    
    
}
