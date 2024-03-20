package school.model;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public final class SchoolRepository {

    private final List<Group> groups;
    private final Random rand;

    public SchoolRepository() {
        rand = new Random();
        groups = generateGroups();
    }

    public void addGroup(Group group) {
        groups.add(group);
    }

    public List<Group> getGroups() {
        return groups;
    }

    public List<Group> generateGroups() {
        List<Group> data = new ArrayList<>();
        IntStream.range(1, 20).boxed().forEach((Integer t) -> {
            String name = String.format("group%02d", t);
            data.add(new Group(name, generateStudents(name)));
        });
        return data;
    }

    public List<Student> generateStudents(String prefix) {
        List<Student> data = new ArrayList<>();
        IntStream.range(1, 30).boxed().forEach((Integer t) -> {
            data.add(new Student(String.format("%sName%02d", prefix, t), rand.nextInt(20, 50)));
        });
        return data;
    }

}
