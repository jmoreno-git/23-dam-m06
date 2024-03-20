package cat.proven.schoolmanager.controllers;

import cat.proven.schoolmanager.model.SchoolModel;
import cat.proven.schoolmanager.views.MainView;
import javax.xml.xquery.XQException;

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

    /**
     * List all Groups, if there are no groups it is null
     */
    private void searchAllGroups() {
        try {
            view.showLnMessage("\n===List all Groups===");
            String query = "declare variable $z as xs:integer external; "
                    + "for $x in doc('dam2/school.xml')/school/groups/group "
                    + "return concat('Group{id=', $x/@id, ', name=', $x/groupname, ', description=', $x/description, ', duration=', $x/duration, '}')";

            model.execQuery(query);
        } catch (XQException ex) {
            view.showLnMessage(ex.getMessage());
        } catch (Exception e) {
            view.showLnMessage(e.getMessage());
        }
    }

    /**
     * List all Students, if there are no students it is null
     */
    private void searchAllStudents() {
        try {
            view.showLnMessage("\n===List all Students===");
            String query = "declare variable $z as xs:integer external; "
                    + "for $x in doc('dam2/school.xml')/school/groups/group/enrolled/student "
                    + "return concat('Student{id=', $x/@id, ', studentname=', $x/studentname, ', age=', $x/age, '}')";

            model.execQuery(query);
        } catch (XQException ex) {
            view.showLnMessage(ex.getMessage());
        } catch (Exception e) {
            view.showLnMessage(e.getMessage());
        }
    }

    /**
     * Lis Groups By Group, asks the user for the group to search for, if the
     * group does not exist it will be null, if the group exists it will show
     * the students, if there are no students it is null
     */
    private void searchStudentsByGroup() {
        try {
            view.showLnMessage("\n===List Students By Group===");
            view.showLnMessage("Fill in all the data to search for the student of a group");
            view.showMessage("Group ID: ");
            String groupID = model.inputStringScanner();
            String query = "declare variable $z as xs:integer external; "
                    + "for $x in doc('dam2/school.xml')/school/groups/group[@id='" + groupID + "']/enrolled/student "
                    + "return concat('Student{id=', $x/@id, ', name=', $x/studentname, ', age=', $x/age, '}')";

            model.execQuery(query);
        } catch (NumberFormatException nfe) {
            view.showLnMessage(nfe.getMessage());
        } catch (XQException ex) {
            view.showLnMessage(ex.getMessage());
        } catch (Exception e) {
            view.showLnMessage(e.getMessage());
        }
    }

    /**
     * Add Gruop, asks the user for the group to add, if the group exists it is
     * null, if it does not exist add the group
     */
    private void addGroup() {
        try {
            view.showLnMessage("\n===Add Group===");
            view.showLnMessage("Fill in all the data to add the group");
            view.showMessage("Group ID: ");
            String groupID = model.inputStringScanner();
            view.showMessage("Group Name: ");
            String groupName = model.inputStringScanner();
            view.showMessage("Description: ");
            String description = model.inputStringScanner();
            view.showMessage("Duration: ");
            int duration = model.inputIntScanner();
            String query = "update insert "
                    + "<group ='" + groupID + "'>"
                    + "<groupname>" + groupName + "</groupname>"
                    + "<description>" + description + "</description>"
                    + "<duration>" + duration + "</duration>"
                    + "</group>"
                    + "into doc('dam2/school.xml')/school/groups";

            model.execAlter(query);
        } catch (NumberFormatException nfe) {
            view.showLnMessage(nfe.getMessage());
        } catch (XQException ex) {
            view.showLnMessage(ex.getMessage());
        } catch (Exception e) {
            view.showLnMessage(e.getMessage());
        }
    }

    /**
     * Remove Groups, asks the user for the student to delete, if the student
     * does not exist it is null, if the student exists it will be deleted
     */
    private void removeStudent() {
        try {
            view.showLnMessage("\n===Remove Student===");
            view.showLnMessage("Fill in all the data to delete the student");
            view.showMessage("Student ID: ");
            String studentID = model.inputStringScanner();
            String query = "for $e in doc('dam2/school.xml')/school/groups/group/enrolled/student "
                    + "where $e/@id = '" + studentID + "' "
                    + "return update delete $e";

            model.execAlter(query);
        } catch (NumberFormatException nfe) {
            view.showLnMessage(nfe.getMessage());
        } catch (XQException ex) {
            view.showLnMessage(ex.getMessage());
        } catch (Exception e) {
            view.showLnMessage(e.getMessage());
        }
    }

    /**
     * Enroll Groups, asks the user for the student to enroll, if the student
     * exists it is null, if the user does not exist it will be enrolled
     */
    private void enrollStudent() {
        try {
            view.showLnMessage("\n===Enroll Student===");
            view.showLnMessage("Fill in all the information to enroll a student");
            view.showMessage("Group ID: ");
            String groupID = model.inputStringScanner();
            view.showMessage("Student ID: ");
            String studentID = model.inputStringScanner();
            view.showMessage("Student Name: ");
            String studentName = model.inputStringScanner();
            view.showMessage("Age: ");
            int age = model.inputIntScanner();
            String query = "update insert "
                    + "<student id='" + studentID + "'>"
                    + "<studentname>" + studentName + "</studentname>"
                    + "<age>" + age + "</age>"
                    + "</student>"
                    + "into doc('dam2/school.xml')/school/groups/group[@id='" + groupID + "']/enrolled";

            model.execAlter(query);

            query = "declare variable $z as xs:integer external; "
                    + "for $z in doc('dam2/school.xml')/school/groups/group/enrolled/student "
                    + "where $z/@id='" + studentID + "' "
                    + "return concat('Student{id=', $z/@id, ', name=', $z/studentname, ', age=', $z/age, '}')";
            model.execQuery(query);
        } catch (NumberFormatException nfe) {
            view.showLnMessage(nfe.getMessage());
        } catch (XQException ex) {
            view.showLnMessage(ex.getMessage());
        } catch (Exception e) {
            view.showLnMessage(e.getMessage());
        }
    }

}
