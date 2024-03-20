package person;

/**
 *
 * @author ProvenSoft
 */
public class PersonTester {

    public static void main(String[] args) {
//        Person p1 = new Person("1111A", "name01", "addr01", "93001", 1991);
//        System.out.println("p1:"+p1.toString());
//        Person p2 = new Person();
//        p2.setNif("1112B");
//        p2.setName("name02");
//        p2.setAddress("addr02");
//        p2.setPhone("93002");
//        p2.setBirthYear(1992);
//        System.out.println("p2:"+p2.toString());
//        Person p3 = new Person(p1);
//        System.out.println("p3:"+p3.toString());
        //
        Employee e1 = new Employee("1112A", "name11", "addr11", "93011", 1981, 1000.0, "marketing", 2010);
        System.out.println("e1:"+e1.toString());
        Employee e2 = new Employee(e1);
        System.out.println("e2:"+e2.toString());
        if (e1.equals(e2)) {
            System.out.println("e1==e2");
        } else {
            System.out.println("e1!=e2");
        }
        System.out.println(Person.validNif("12345678Z"));
        System.out.println(Employee.validNif("12345678Z"));
        System.out.println(Person.validNif("11111111Z"));
    }
    
}
