package cat.proven.components.guidatabrowser;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;

/**
 *
 * @author Jose.
 * @param <T> the base type of data.
 * @param <I> the type of the unique field ot objects.
 */
public class Controller<T, I> 
        implements PropertyChangeListener, VetoableChangeListener,
        ActionListener {
    
    private Model model;
    private DataBrowser view;
    
    public Controller() {
    }
    
    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public DataBrowser getView() {
        return view;
    }

    public void setView(DataBrowser view) {
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
                view.setElement(model.getSelectedElement()); break;
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

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        if (action != null) {
            //System.out.println(action);
            switch (action) {
                case "<<":
                    moveFirst();
                    break;
                case "<":
                    movePrevious();
                    break;
                case ">":
                    moveNext();
                    break;
                case ">>":
                    moveLast();
                    break;
            }
        }
        
    }
    
}
