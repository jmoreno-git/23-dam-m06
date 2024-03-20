package cat.proven.schoolmanager;

import cat.proven.schoolmanager.controllers.MainController;
import cat.proven.schoolmanager.model.SchoolModel;

/**
 *
 * @author ProvenSoft
 */
public class Main {

    public static void main(String[] args) {
        Main app = new Main();
        app.run();
    }

    private void run() {
        SchoolModel model = new SchoolModel();
        MainController controller = new MainController(model);
    }
    
}
