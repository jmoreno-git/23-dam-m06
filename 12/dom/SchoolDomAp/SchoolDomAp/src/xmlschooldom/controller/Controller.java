package xmlschooldom.controller;

import java.util.List;
import xmlschooldom.model.Group;
import xmlschooldom.model.Model;
import xmlschooldom.model.School;
import xmlschooldom.model.Student;
import xmlschooldom.view.SchoolMainView;

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
            case "list_all_groups":
                list_all_groups();
                break;
            case "list_students_by_group":
                list_students_by_group();
                break; 
            case "list_all_students":
                list_all_students();
                break;
            case "load_data_from_xml_file":
                load_data_from_xml_file();
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

    private void list_all_students() {
        School school = model.getSchool();
        if (school != null) {
            List<Student> students = school.getAllStudents();
            view.displayList(students);
        } else {
            view.showMessage("School not read yet");
        }
    }

    private void list_all_groups() {
        School school = model.getSchool();
        if (school != null) {
            List<Group> groups = school.getGroups();
            view.displayList(groups);
        } else {
            view.showMessage("School not read yet");
        }
    }

    private void list_students_by_group() {
        School school = model.getSchool();
        if (school != null) {
            String groupName = view.showInputDialog("Group: ");
            Group group = school.getGroupByName(groupName);
            if (group != null) {
              List<Student> students = school.getStudentsByGroup(group);
                view.displayList(students);              
            } else {
                view.showMessage("Group not found");
            }
        } else {
            view.showMessage("School not read yet");
        }
    }

    private void load_data_from_xml_file() {
        String filename = view.showInputDialog("Filename: ");
        boolean success = model.readSchool(filename);
        String message = success ? "Successfully read" : "Read fail";
        view.showMessage(message);
    }
  
    
    
}
