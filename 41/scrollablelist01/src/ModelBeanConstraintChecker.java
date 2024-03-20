/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package cat.proven.scrollablelist;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;

/**
 *
 * @author alumne
 */
public class ModelBeanConstraintChecker implements VetoableChangeListener{

    @Override
    public void vetoableChange(PropertyChangeEvent evt) throws PropertyVetoException {
        ScrollableList source = (ScrollableList) evt.getSource();
        if (source.getData().contains(evt.getNewValue())) {
            throw new PropertyVetoException(
                String.format("Constraint violation: %s already in data source", 
                        evt.getNewValue()), 
                evt);
        }
        if (evt.getPropertyName().equals("position")) {
            int pos = (int) evt.getNewValue();
            if ( (pos<0) || (pos>=source.getData().size()) ) {
            throw new PropertyVetoException(
                String.format("Invalid position: %d", 
                        pos), 
                evt);                
            }
        }
    }
 
}
