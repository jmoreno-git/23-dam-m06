
package cat.proven.components.databrowserdemo;

import cat.proven.components.databrowser.DataBrowser;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jose.
 */
public class Main {
    
    private DataBrowser<Friend, String> browser1;
    private DataBrowser<Product, Integer> browser2;
    
    public static void main(String[] args) {
        Main ap = new Main();
        ap.run();
    }
   
    private void run() {
        
        //Thread to change data asynchronously (simulates interaction with other component).
        Thread t1 = new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10000);
                    List<Friend> data2 = loadTestData2();
                    browser1.setData(data2);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }            
        };
        t1.start();    
        //instantiate data browser1 component.
        browser1 = new DataBrowser<>();
        //set 1st test data set.
        List<Friend> data1 = loadTestData1();
        browser1.setData(data1);
        //start working data browser1.
        browser1.start();
//        browser1.moveForward();
//        browser1.moveBackward();
//        browser1.moveEnd();
//        browser1.moveStart();

        browser2 = new DataBrowser<>();
        List<Product> data3 = loadTestData3();
        browser2.setData(data3);
        browser2.start();
    }

    private List<Friend> loadTestData1() {
        List<Friend> d = new ArrayList<>();
        d.add(new Friend("101", "name101", 11));
        d.add(new Friend("102", "name102", 12));
        d.add(new Friend("103", "name103", 13));
        d.add(new Friend("104", "name104", 14));
        d.add(new Friend("105", "name105", 15));
        d.add(new Friend("106", "name106", 16));
        return d;
    }
    
    private List<Friend> loadTestData2() {
        List<Friend> d = new ArrayList<>();
        d.add(new Friend("201", "name201", 21));
        d.add(new Friend("202", "name202", 22));
        d.add(new Friend("203", "name203", 23));
        d.add(new Friend("204", "name204", 24));
        d.add(new Friend("205", "name205", 25));
        d.add(new Friend("206", "name206", 26));
        return d;
    }
    
    private List<Product> loadTestData3() {
        List<Product> d = new ArrayList<>();
        d.add(new Product(101, "code101", "desc101", 101.0));
        d.add(new Product(102, "code102", "desc102", 102.0));
        d.add(new Product(103, "code103", "desc103", 103.0));
        d.add(new Product(104, "code104", "desc104", 104.0));
        d.add(new Product(105, "code105", "desc105", 105.0));
        d.add(new Product(106, "code106", "desc106", 106.0));
        return d;
    }
    
}
