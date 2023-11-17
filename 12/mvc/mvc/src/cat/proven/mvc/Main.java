package cat.proven.mvc;

import cat.proven.mvc.controllers.Controller;
import cat.proven.mvc.model.Model;

/**
 *
 * @author ProvenSoft
 */
public class Main {

    public static void main(String[] args) {
        Model model = new Model();
        model.setName("Peter");
        Controller controller = new Controller(model);
        controller.start();
    }
    
}
