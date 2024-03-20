package cat.proven.schoolmanager.views;

import cat.proven.schoolmanager.controllers.MainController;
import cat.proven.schoolmanager.model.SchoolModel;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author ProvenSoft
 */
public class MainView {
    private final MainController control;
    private final SchoolModel model;
    private final MainMenu menu;
    
    public MainView(MainController control, SchoolModel model) {
        this.control = control;
        this.model = model;
        this.menu = new MainMenu();
    }
    
    
    /**
     * Prompts a message to the user and reads answer
     * @param message to show
     * @return the answer wrote by user
     */
    public String showInputDialog(String message) {
        System.out.print(message);
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    /**
     * Displays a message.
     *
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
            action = (action == null) ? "wrong_option" : action;
            control.executeAction(action);
        } while (true);
    }
    
    /**
     * shows a list of any element
     * @param <T> is a generic object for the list
     * @param elements refers a content of a list recieved lo display.
     */
    public <T> void displayList(List<T> elements) {
        if (!elements.isEmpty()) {
            for (T elem : elements) {
                showMessage(elem.toString());
            }
        } else {
            showMessage("Not data found");
        }

    }

   
}
