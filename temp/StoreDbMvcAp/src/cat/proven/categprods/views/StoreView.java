package cat.proven.categprods.views;

import cat.proven.categprods.controllers.StoreController;
import cat.proven.categprods.model.Category;
import cat.proven.categprods.model.StoreModel;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author ProvenSoft
 */
public class StoreView {

    private final StoreController controller;
    private final StoreModel model;
    
    private MainMenu mainMenu;
    private boolean exit;

    public StoreView(StoreController controller, StoreModel model) {
        this.controller = controller;
        this.model = model;
        this.mainMenu = new MainMenu();
    }

    public void display() {
        exit = false;
        do {
            mainMenu.show();
            String action = mainMenu.getSelectedOptionActionCommand();
            controller.processAction(action);
        } while(!exit);
    }

    /**
     * prompts a message to user and read answer
     * @param message the message to display
     * @return user's answer
     */
    public String inputString(String message) {
        System.out.print(message);
        Scanner sc = new Scanner(System.in);
        return sc.next();
    }

    /**
     * displays a message
     * @param message the message to display
     */
    public void displayMessage(String message) {
        System.out.println(message);
    }

    /**
     * close the view
     */
    public void close() {
        this.exit = true;
    }
    
    /**
     * displays a list of data
     * @param <T> data type to display
     * @param data the list to display
     */
    public <T> void displayList(List<T> data) {
        if (data != null) {
            data.forEach(System.out::println);
            System.out.format("%d elements displayed\n", data.size());
        }  
    } 
    
    /**
     * asks data for a category
     * @return a category object with entered data or null in case or error
     */
    public Category inputCategory() {
        String code = inputString("code: ");
        String name = inputString("name: ");
        return new Category(0, code, name);
    }    
    
}
