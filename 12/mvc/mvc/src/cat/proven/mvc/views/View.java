package cat.proven.mvc.views;

import cat.proven.mvc.controllers.Controller;
import cat.proven.mvc.model.Model;

/**
 *
 * @author ProvenSoft
 */
public class View {
    private Model model;
    private Controller controller;
    
    private MvcMenu menu;
    
    private boolean exit;

    public View(Controller controller, Model model) {
        this.model = model;
        this.controller = controller;
        this.menu = new MvcMenu();
    }

    public boolean isExit() {
        return exit;
    }

    public void setExit(boolean exit) {
        this.exit = exit;
    }
    
    public void show() {
        exit = false;
        do {
            menu.show();
            String action = menu.getSelectedOptionActionCommand();
//            switch (action) {
//                case "exit":
//                    
//                    break;
//                case "greet":
//                    
//                    break;
//                default:
//                    throw new AssertionError();
//            }
            controller.processAction(action);
        } while (!exit);
    }
    
    public void showMessage(String message) {
        System.out.println(message);
    }
}
