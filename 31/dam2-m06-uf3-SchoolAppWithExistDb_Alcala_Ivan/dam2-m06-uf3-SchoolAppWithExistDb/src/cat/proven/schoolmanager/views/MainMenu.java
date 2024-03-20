package cat.proven.schoolmanager.views;


/**
 *
 * @author ProvenSoft
 */
public class MainMenu extends Menu{
    public MainMenu(){
        setTitle("School Manager Menu");
        addOption(new Option("Exit", "exit"));
        addOption(new Option("List all groups", "listallgroups"));
        addOption(new Option("List all students", "listallstudents"));
        addOption(new Option("List group students", "listgroupstudents"));
        addOption(new Option("Add group", "addgroup"));
        addOption(new Option("Remove student", "removestudent"));
        addOption(new Option("Enroll student", "enrollstudent"));
    }
}
