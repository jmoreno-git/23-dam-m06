package xmlschooldom.model;

import java.util.ArrayList;
import java.util.List;

public class School {
    
    private String name;
    private List<Group> groups;
    
    public School(){
    }
    
    public School(String name, List<Group> groups){
        this.name = name;
        this.groups = groups;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public boolean addGroup(Group group) {
        //TODO
        return false;
    }

    public Group getGroupByName(String name) {
        Group found = null;
        for (Group g: groups) {
            if (g.getName().equals(name)) {
                found = g;
                break;
            }
        }
        return found;
    }
    
    public List<Student> getAllStudents() {
        List<Student> allStudents = new ArrayList<>();
        for (Group g: groups) {
            allStudents.addAll(g.getStudents());
        }
        return allStudents;
    }
    
    public List<Student> getStudentsByGroup(Group group) {
        return group.getStudents();       
    }    
    
    @Override
    public String toString() {
        return "School{" + "name=" + name + ", groups=" + groups + '}';
    }
    
}
