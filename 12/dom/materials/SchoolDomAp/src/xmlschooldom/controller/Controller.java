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
            case "exit" ->  exitApplication();
            case "groups/listall" -> listAllGroups();
            case "students/listall" -> listAllStudents();
            case "students/listbygroup" -> listStudentsByGroup();
            case "school/load" -> loadDataFromXmlfile();
            case "school/save" -> saveDataToXmlFile();
            case "stud/minor/save" -> saveMinorStudentsToXmlFile();
            case "stud/group/save" -> saveStudentsOfGroupToXmlFile();
            case "transform/html" -> transformFromXmlToHtml();
            default -> view.showMessage("Wrong option");
        }
    }

     public void exitApplication() {
        System.exit(0);
    }

    private void listAllStudents() {
        School school = model.getSchool();
        if (school != null) {
            List<Student> students = school.getAllStudents();
            view.displayList(students);
        } else {
            view.showMessage("School not read yet");
        }
    }

    private void listAllGroups() {
        School school = model.getSchool();
        if (school != null) {
            List<Group> groups = school.getGroups();
            view.displayList(groups);
        } else {
            view.showMessage("School not read yet");
        }
    }

    private void listStudentsByGroup() {
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

    private void loadDataFromXmlfile() {
        String filename = view.showInputDialog("Filename: ");
        boolean success = model.readSchool(filename);
        String message = success ? "Successfully read" : "Read fail";
        view.showMessage(message);
    }

    private void saveDataToXmlFile() {
        String filename = view.showInputDialog("Filename: ");
        boolean success = model.writeSchool(filename);
        String message = success ? "Successfully saved" : "Write fail";
        view.showMessage(message);
    }

    private void saveMinorStudentsToXmlFile() {
        String filename = view.showInputDialog("Filename: ");
        boolean success = model.writeMinorStudents(filename);
        String message = success ? "Successfully saved" : "Write fail";
        view.showMessage(message);
    }

    private void saveStudentsOfGroupToXmlFile() {
        String filename = view.showInputDialog("Filename: ");
        String groupName = view.showInputDialog("Group name: ");
        Group group = model.getSchool().getGroupByName(groupName);
        boolean success = model.writeStudentsByGroup(filename, group);
        String message = success ? "Successfully saved" : "Write fail";
        view.showMessage(message);
    }
  
    private void transformFromXmlToHtml() {
        // Read file names from user.
        String xmlInputFilename = view.showInputDialog("Enter source XML file name: ");
        String xslInputFilename = view.showInputDialog("Enter source XSL file name: ");
        String xmlOutputFilename = view.showInputDialog("Enter destination XML file name: ");
        boolean success = model.transformXmlToHtml(xmlInputFilename, xmlOutputFilename, xslInputFilename);
        String message = success ? "Successfully transformed" : "Transformation fail";
        view.showMessage(message);
    }
    
}
