package xmlschooldom.view;

public class SchoolMenu extends Menu {

    public SchoolMenu() {
        super("School manager");
        addOption(new Option("Exit","exit"));
        addOption(new Option("List all groups","groups/listall"));
        addOption(new Option("List all students","students/listall"));
        addOption(new Option("List students by group","students/listbygroup"));
        addOption(new Option("Load data from xml file","school/load"));
        addOption(new Option("Save data to xml file","school/save"));
        addOption(new Option("Save minor students to xml file","stud/minor/save"));
        addOption(new Option("Save students of group to xml file","stud/group/save"));
        addOption(new Option("Transform XML to HTMl using XSLT","transform/html"));
    }
    
    
}
