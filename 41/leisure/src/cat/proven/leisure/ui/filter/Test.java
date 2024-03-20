package cat.proven.leisure.ui.filter;

import cat.proven.leisure.persist.ActivityListDao;
import cat.proven.leisure.persist.UserListDao;
import javax.swing.JFrame;

/**
 *
 * @author Jose Moreno
 */
public class Test {

    public static void main(String[] args) {
//        new ActivityListDao()
//                .selectAll()
//                .forEach(System.out::println);
//        new UserListDao()
//                .selectAll()
//                .forEach(System.out::println);
        JFrame frame = new JFrame("test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new FilterActivityPanel());
        frame.setSize(400, 300);
        frame.setVisible(true);
    }
    
}
