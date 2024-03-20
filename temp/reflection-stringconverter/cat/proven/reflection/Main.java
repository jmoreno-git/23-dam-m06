package cat.proven.reflection;
/**
 *
 * @author ProvenSoft
 */
public class Main {

    public static void main(String[] args) {
        Product p = new Product(1, "desc1", 101, 11);
        System.out.println("p:"+p.toString());
        Customer c = new Customer("11A", "Peter", "123");
        System.out.println("c:"+c.toString());    
    }
    
}
