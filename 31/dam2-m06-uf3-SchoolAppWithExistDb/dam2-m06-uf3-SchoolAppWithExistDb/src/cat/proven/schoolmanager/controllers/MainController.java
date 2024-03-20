package cat.proven.schoolmanager.controllers;

import cat.proven.schoolmanager.model.SchoolModel;
import cat.proven.schoolmanager.views.MainView;


/**
 *
 * @author ProvenSoft
 */
public class MainController {

    private final SchoolModel model;
    private final MainView view;

    public MainController(SchoolModel model) {
        this.model = model;
        this.view = new MainView(this, model);
        this.view.display();
    }

    public void executeAction(String action) {
        switch (action) {
            case "exit":
                exitApplication();
                break;
            case "listallgroups":
                searchAllGroups();
                break;
            case "listallstudents":
                searchAllStudents();
                break;
            case "listgroupstudents":
                searchStudentsByGroup();
                break;
            case "addgroup":
                addGroup();
                break;
            case "removestudent":
                removeStudent();
                break;
            case "enrollstudent":
                enrollStudent();
                break;
            default:
                view.showMessage("Wrong option.");
                break;
        }
    }

    /**
     * Exits the application.
     */
    private void exitApplication() {
        System.exit(0);
    }

   
    private void searchAllGroups() {
     

    }

    
    private void searchAllStudents() {
        
    }

    
    private void searchStudentsByGroup() {
       
    }

    
    private void addGroup() {
       
    }

   
    private void removeStudent() {
       
    }

   
    private void enrollStudent() {
       
    }
}
