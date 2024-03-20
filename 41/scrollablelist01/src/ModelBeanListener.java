//package cat.proven.scrollablelist;

import java.beans.IndexedPropertyChangeEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;

/**
 *
 * @author Jose
 */
public class ModelBeanListener implements PropertyChangeListener, VetoableChangeListener {

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        String msg = String.format("Change. Property=%s. Old value=%s, New value=%s. ", evt.getPropertyName(), evt.getOldValue(), evt.getNewValue());
        if (evt instanceof IndexedPropertyChangeEvent) {
            IndexedPropertyChangeEvent ievt = (IndexedPropertyChangeEvent) evt;
            msg += String.format("Index=%d", ievt.getIndex());
        }
        System.out.println(msg);
    }

    @Override
    public void vetoableChange(PropertyChangeEvent evt) throws PropertyVetoException {
        String msg = String.format("Veto. Property=%s. Old value=%s, New value=%s. ", evt.getPropertyName(), evt.getOldValue(), evt.getNewValue());
        System.out.println(msg);
    }
    
}
