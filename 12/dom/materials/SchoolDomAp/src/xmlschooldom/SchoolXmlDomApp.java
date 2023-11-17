package xmlschooldom;

import xmlschooldom.controller.Controller;
import xmlschooldom.model.Model;

public class SchoolXmlDomApp {
    
    public static void main(String[] args) {
        SchoolXmlDomApp ap = new SchoolXmlDomApp();
        ap.run();
    }

    private void run() {
        Model model = new Model();
        Controller controller = new Controller(model);

    }
    
}
