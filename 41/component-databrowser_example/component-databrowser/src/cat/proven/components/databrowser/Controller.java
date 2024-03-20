package cat.proven.components.databrowser;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jose.
 * @param <T> the base type of data.
 * @param <I> the type of the unique field ot objects.
 */
public class Controller<T, I> implements PropertyChangeListener, VetoableChangeListener {
    
    private Model model;
    private View view;
    
    public Controller() {
    }

    protected void start() {
        view.interact();
    }
    
    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        //Logger.getLogger(getClass()).log(Level.INFO, evt.toString());
        //System.out.println(evt.toString());
        String propertyName = evt.getPropertyName();
        T element = (T) evt.getNewValue();
        switch (propertyName) {
            case "selectedIndex" : 
            case "data":    
                view.showElement(model.getSelectedElement()); break;
        }
    }
    
    @Override
    public void vetoableChange(PropertyChangeEvent evt) throws PropertyVetoException {
        //Logger.getLogger(getClass()).log(Level.INFO, evt.toString());
        //System.out.println(evt.toString());
        String propertyName = evt.getPropertyName();
        T element = (T) evt.getNewValue();
        switch (propertyName) {
            case "selectedIndex" : 
                int index = (Integer)evt.getNewValue();
                if (!model.validIndex(index))
                    throw new PropertyVetoException("Out of range: "+index, evt);
            case "data":    
                if (element==null) throw new PropertyVetoException("data=null", evt);
                break;
        }
    }
    
    protected boolean moveNext() {
        boolean result = false;
        try {
            model.setSelectedIndex(model.getSelectedIndex()+1);
            result = true;
        } catch (PropertyVetoException ex) {
            //Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            result = false;
        }
        return result;
    }
    
    protected boolean movePrevious() {
        boolean result = false;
        try {
            model.setSelectedIndex(model.getSelectedIndex()-1);
            result = true;
        } catch (PropertyVetoException ex) {
            //Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            result = false;
        }
        return result;
    }
    
    protected boolean moveFirst() {
        boolean result = false;
        try {
            model.setSelectedIndex(0);
            result = true;
        } catch (PropertyVetoException ex) {
            //Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            result = false;
        }
        return result;
    }
    
    protected boolean moveLast() {
        boolean result = false;
        try {
            model.setSelectedIndex(model.getData().size()-1);
            result = true;
        } catch (PropertyVetoException ex) {
            //Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            result = false;
        }
        return result;
    }
    
}
