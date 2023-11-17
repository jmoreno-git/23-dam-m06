package xmlschooldom.view;

public class SchoolMenu extends Menu {

    public SchoolMenu() {
        super("School manager");
        addOption(new Option("Exit","exit"));
        addOption(new Option("List all students","list_all_students"));
        addOption(new Option("List all groups","list_all_groups"));
        addOption(new Option("List students by group","list_students_by_group"));
        addOption(new Option("Load data from xml file","load_data_from_xml_file"));
    }
    
    
}
