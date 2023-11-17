package cat.proven.mvc.controllers;

import cat.proven.mvc.model.Model;
import cat.proven.mvc.views.View;

/**
 *
 * @author ProvenSoft
 */
public class Controller {

    private Model model;
    private View view;

    public Controller(Model model) {
        this.model = model;
        this.view = new View(this, model);
    }

    public void start() {
        view.show();
    }
    
    public void processAction(String action) {
        switch (action) {
            case "exit":
                doExit();
                break;
            case "greet":
                doGreet();
                break;
            default:
                throw new AssertionError();
        }
    }

    private void doExit() {
        //System.exit(0);
        view.setExit(true);
    }

    private void doGreet() {
        String name = model.getName();
        view.showMessage("Hello "+name);
    }

}
