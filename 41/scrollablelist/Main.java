//package cat.proven.scrollablelist;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jose
 */
public class Main {

    public static void main(String[] args) {
        
        ScrollableList<String> model = new ScrollableList<>();
        ModelBeanListener listener = new ModelBeanListener();
//        //add change observer for the whole bean.
        model.addPropertyChangeListener(listener);
//        //add veto observer for the whole bean.
//        model.addVetoableChangeListener(listener);
//        //add observer for property 'position'.
//        model.addPropertyChangeListener("position", (PropertyChangeEvent evt) -> {
//            String msg = String.format("Current listener. Old value=%d. New value=%d", evt.getOldValue(), evt.getNewValue());
//            System.out.println(msg);
//        });
//        //add observer for property 'data'.
//        model.addPropertyChangeListener("data", (PropertyChangeEvent evt) -> {
//            StringBuilder sb = new StringBuilder();
//            ScrollableList source = (ScrollableList) evt.getSource();
//            sb.append("ModelBean {");
//            sb.append("[data="); sb.append(source.getData().toString()); sb.append("]");
//            sb.append("}");
//            System.out.println(sb.toString());
//        });
        //add veto observer for constraint verification.
        model.addVetoableChangeListener("data", new ModelBeanConstraintChecker());
//        //perform changes in bean.
        try {
            List<String> myList = new ArrayList<>();
            myList.add("a1");
            myList.add("a2");
            myList.add("a3");
            myList.add("a4");
            System.out.println("* Setting data=myList");
            model.setData(myList);
            model.setPosition(1);
            System.out.println(model.toString());
            System.out.println("* Adding new element a5");
            model.addElement("a5");
            System.out.println("* Setting element at position 2 to b3");
            model.setData(2, "b3");
            System.out.println("* Setting element at position 2 to a4 (duplicate)");
            model.setData(2, "a4");
            System.out.println("* Setting current to last");
            model.moveToLast();
            System.out.println("* Setting current to first");
            model.moveToFirst();
            System.out.println("* Setting current to current value + 2");
            model.moveCurrentRelative(2);
//            System.out.println("* Setting current to current value - 5");
//            model.moveCurrentRelative(-5);
//            System.out.println("* Setting current to current value +50");
//            model.moveCurrentRelative(50);
//            System.out.println("* Setting element at position 50 to b50");
//            model.setData(50, "b50");
            System.out.println("* Getting element at position 50");
            model.getData(50);
        } catch (PropertyVetoException e) {
            //e.printStackTrace();
            Logger.getLogger("cat.proven.scrollablelist").log(Level.SEVERE, e.getMessage());
        }
        
    }
    
}
