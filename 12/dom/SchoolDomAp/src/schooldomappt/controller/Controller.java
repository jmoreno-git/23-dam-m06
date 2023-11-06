package schooldomappt.controller;

import schooldomappt.model.Model;
import schooldomappt.view.SchoolMainView;

public class Controller {
    private Model model;
    private SchoolMainView view;

    public Controller() {
    }

    public Controller(Model model) {
        this.model = model;
        this.view = new SchoolMainView(this, model);
        this.view.display();
    }

    /**
     * Processes requests from the view.
     * @param action to execute.
     */
    public void executeAction(String action) {
        switch (action) {
            case "exit": 
                exitApplication();
                break;
            case "groups/all":
                listAllGroups();
                break;
            case "students/bygroup":
                listStudentsByGroup();
                break; 
            case "students/all":
                listAllStudents();
                break;
            case "schollfromxml":
                loadSchoolFromXml();
                break;
            case "wrong_option":
            default:
                view.showMessage("Wrong option");
                    break;
        }
    }

    public void exitApplication() {
        System.exit(0);
    }

    private void listAllStudents() {
        view.showMessage("listAllStudents: Not implemented yet!");
    }

    private void listAllGroups() {
        view.showMessage("listAllGroups: Not implemented yet!");
    }

    private void listStudentsByGroup() {
        view.showMessage("listStudentsByGroup: Not implemented yet!");
    }

    private void loadSchoolFromXml() {
        view.showMessage("loadSchoolFromXml: Not implemented yet!");
        
    }
}
