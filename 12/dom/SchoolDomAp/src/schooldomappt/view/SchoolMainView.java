package schooldomappt.view;

import schooldomappt.model.Model;
import java.util.Scanner;
import schooldomappt.controller.Controller;


public class SchoolMainView {

    private final Controller control;
    private final Model model;
    private final SchoolMenu menu;

    public SchoolMainView(Controller control, Model model) {
        this.control = control;
        this.model = model;
        this.menu = new SchoolMenu();
    }

    public String showInputDialog(String message) {
        System.out.print(message);
        Scanner sc = new Scanner(System.in);
        return sc.next();
    }

    /**
     * Displays a message.
     * @param message to display
     */
    public void showMessage(String message) {
        System.out.println(message);
    }

    /**
     * Shows the view.
     */
    public void display() {
        do {
            menu.show();
            String action = menu.getSelectedOptionActionCommand();
            action = (action==null) ? "wrong_option" : action;
            control.executeAction(action);           
        } while (true);
    }   
    
}//end of class
