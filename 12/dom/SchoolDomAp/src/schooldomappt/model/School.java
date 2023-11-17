package schooldomappt.model;

import java.util.ArrayList;
import java.util.List;

public class School {
    
    private String name;
    private List<Group> groups;
    
    public School(String name){
        this.name = name;
        this.groups = new ArrayList<>();
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

    public List<Group> getAllGroups() {
        //TODO
        return null;
    }
    
    public List<Student> getAllStudents() {
        //TODO
        return null;
    }
    
    public List<Student> getStudentsByGroup(Group group) {
         //TODO
        return null;       
    }    
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("School{");
        sb.append("name=").append(name);
        sb.append(", groups=").append(groups);
        sb.append('}');
        return sb.toString();
    }
    
}
