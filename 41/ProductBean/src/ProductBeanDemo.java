
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;

public class ProductBeanDemo {

    public static void main(String[] args) throws PropertyVetoException {
        PropertyChangeListener pcl = new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                System.out.println("Property change: " + evt.getPropertyName() + " old value: " + evt.getOldValue() + " new value: " + evt.getNewValue());
            }
        };
        VetoableChangeListener vcl = new VetoableChangeListener() {
            @Override
            public void vetoableChange(PropertyChangeEvent evt) throws PropertyVetoException {
                if (((double) evt.getNewValue()) < 0.0) {
                    throw new PropertyVetoException("Negative values not allowed", evt);
                }
            }
        };
        Product p = new Product("P1", "Desc P1", 10.0, 100.0, 5.0);
        p.addPropertyChangeListener(pcl);
        p.addVetoableChangeListener(vcl);
        try {
            p.setCostPrice(20.0);
            p.setCostPrice(-30.0);            
        } catch (PropertyVetoException e) {
            System.out.format("Invalid value %f for property '%s'%n", e.getPropertyChangeEvent().getNewValue(), e.getPropertyChangeEvent().getPropertyName());
        }
        System.out.println(p);
    }
}
