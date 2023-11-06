package schooldomappt;

import schooldomappt.controller.Controller;
import schooldomappt.model.Model;

public class SchoolXmlDomApp {
    public static void main(String[] args) {
        Model model = new Model();
        Controller controller = new Controller(model);
    }
    
}
