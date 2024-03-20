package cat.proven.schoolmanager.model;

import java.util.List;
import java.util.Objects;

/**
 *
 * @author ProvenSoft
 */
public class Group {
    private String id;
    private String name;
    private String description;
    private int duration;
    private List<Student> students;

    public Group() {
    }

    public Group(String id, String name, String description, int duration, List<Student> students) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.students = students;
    }
    
    public Group(Group other) {
        this.id = other.id;
        this.name = other.name;
        this.description = other.description;
        this.duration = other.duration;
        this.students = other.students;
    }

    public Group(String id, String name, String description, int duration) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.duration = duration;
    }
    
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Group other = (Group) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Group{id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", description=").append(description);
        sb.append(", duration=").append(duration);
        sb.append('}');
        return sb.toString();
    }
    
    
    
    
    
    
}
