package cat.proven.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ProvenSoft
 */
public class StringConverter {
    public static String obj2string(Object obj) {
        StringBuilder sb = new StringBuilder();
        Class c = obj.getClass();
        String className = c.getSimpleName();
        sb.append(className+"{");
        Field[] fields = c.getDeclaredFields();
        for (Field f: fields) {
            String fieldName = f.getName();
            Object fieldValue = null;
            try {
/*                
                f.setAccessible(true);
                fieldValue = f.get(obj);
                f.setAccessible(false);
*/     
                Method getter = c.getMethod("get"+capitalizeFirstLetter(fieldName));
                fieldValue = getter.invoke(obj);
             
            } catch (IllegalArgumentException ex) {
                Logger.getLogger("cat.proven.utils").log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger("cat.proven.utils").log(Level.SEVERE, null, ex);
            } catch (NoSuchMethodException ex) {
                Logger.getLogger(StringConverter.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SecurityException ex) {
                Logger.getLogger(StringConverter.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvocationTargetException ex) {
                Logger.getLogger(StringConverter.class.getName()).log(Level.SEVERE, null, ex);
            }
            sb.append(String.format("[%s=%s]", fieldName, fieldValue.toString()));
        }
        sb.append("}");
        return sb.toString();        
    }
    
    private static String capitalizeFirstLetter(String original) {
    if (original == null || original.length() == 0) {
        return original;
    }
    return original.substring(0, 1).toUpperCase() + original.substring(1);
}
}
