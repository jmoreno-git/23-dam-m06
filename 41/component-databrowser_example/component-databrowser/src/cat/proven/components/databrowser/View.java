package cat.proven.components.databrowser;

import java.lang.reflect.Field;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jose
 * @param <T> the base type of data.
 * @param <I> the type of the unique field of objects.
 */
public class View<T, I> {
    
    private Controller controller;

    public View() {
    }

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }
    
    protected void showElement(T element) {
        StringBuilder sb = new StringBuilder();
        sb.append("===Element form===").append("\n");
        Class c = element.getClass();
        sb.append(c.getSimpleName()).append("\n");
        for(Field f: c.getDeclaredFields()) {
            try {
                boolean isAccessible = f.canAccess(element);
                f.setAccessible(true);
                sb.append(f.getName())
                        .append(":")
                        .append(((T)f.get(element)).toString())
                        .append("\n");
                f.setAccessible(isAccessible);
            } catch (IllegalArgumentException ex) {
                Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println(sb.toString());
    }

//<editor-fold defaultstate="collapsed" desc="user-interaction">
    protected void interact() {
        boolean exit = false;
        do {
            String action = readUserInput();
            switch (action) {
                case "0":
                    exit = true;
                    break;
                case "<":
                    controller.movePrevious();
                    break;
                case ">":
                    controller.moveNext();
                    break;
                case "<<":
                    controller.moveFirst();
                    break;
                case ">>":
                    controller.moveLast();
                    break;
            }
        } while (!exit);
    }
    
    private String readUserInput() {
        Scanner sc = new Scanner(System.in);
        return sc.next();
    }
//</editor-fold>
    
}
