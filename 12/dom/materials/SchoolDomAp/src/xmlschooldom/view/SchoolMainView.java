package xmlschooldom.view;

import java.util.List;
import xmlschooldom.model.Model;
import java.util.Scanner;
import xmlschooldom.controller.Controller;
import xmlschooldom.model.Student;


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

    public void displayList(List<? extends Object> list) {
        if (list != null) {
            for (Object o: list) {
                System.out.println(o.toString());
            }  
            System.out.format("%d elements listed\n", list.size());
        } else {
            showMessage("List is null");
        }
    }
    
}//end of class
