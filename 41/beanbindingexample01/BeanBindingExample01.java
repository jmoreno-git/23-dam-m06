import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class BeanBindingExample01 {
  
    public static void main(String[] args) {
        
        BeanA source, target;
        //instantiate objects.
        source = new BeanA();
        target = new BeanA();        
        //initialize values.
        source.setX("B1-inic");
        target.setX("B2-inic");
        //create property change listener.
        PropertyChangeListener handler = new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                //copy property value of event source object to target object.
                target.setX((String)evt.getNewValue());                
            }           
        };
        
        alert("b1="+source.toString());
        alert("b2="+target.toString());
        //add property change listener to source object.
        source.addPropertyChangeListener(handler);
        //change source object.
        source.setX("B1-changed");
        //show objects.
        alert("b1="+source.toString());
        alert("b2="+target.toString());
        //change source object.
        source.setX("B1-final");
        //show objects.
        alert("b1="+source.toString());
        alert("b2="+target.toString());        
        
    }
    
    private static void alert(String s) {
        System.out.println(s);
    }
}
