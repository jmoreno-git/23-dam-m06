package school.views;

import school.model.Group;
import school.model.SchoolRepository;
import javax.swing.*;
import java.awt.*;

public class SchoolApp extends JFrame {

    private final SchoolRepository model;
    private final GroupsPanel groupsPanel;
    private final StudentsPanel studentsPanel;

    public SchoolApp() {
        setTitle("School application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        model = new SchoolRepository();

        groupsPanel = new GroupsPanel(model);
        studentsPanel = new StudentsPanel();

        groupsPanel.addPropertyChangeListener((evt) -> {
            if (evt.getPropertyName().equals("selectedGroup")) {
                Group selectedGroup = (Group) evt.getNewValue();
                if (selectedGroup != null) {
                    studentsPanel.setStudents(selectedGroup.getStudents());
                }
            }            
        });
        add(groupsPanel, BorderLayout.WEST);
        add(studentsPanel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new SchoolApp().setVisible(true);
        });
    }
}
