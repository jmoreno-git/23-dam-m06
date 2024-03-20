package school.views;


import school.model.Student;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class StudentsPanel extends JPanel {

    private JTable studentsTable;
    private DefaultTableModel tableModel;

    private List<Student> students;

    public StudentsPanel() {
        students = new ArrayList<>();
        initUI();
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
        updateStudentsTable(students);
    }

    private void initUI() {
        setLayout(new BorderLayout());
        tableModel = new StudentsTableModel(students);
        studentsTable = new JTable(tableModel);
        JScrollPane alumnosScrollPane = new JScrollPane(studentsTable);
        add(new JLabel("Students"), BorderLayout.NORTH);
        add(alumnosScrollPane, BorderLayout.CENTER);
    }

    public void updateStudentsTable(List<Student> students) {
        tableModel.setRowCount(0);
        for (Student student : students) {
            Object[] rowData = {student.getName(), student.getAge()};
            tableModel.addRow(rowData);
        }
    }
}

final class StudentsTableModel extends DefaultTableModel {

    private final String[] columnNames = {"Name", "Age"};

    public StudentsTableModel(List<Student> students) {
        for (String columnName : columnNames) {
            addColumn(columnName);
        }
        updateTableData(students);
    }

    @Override
    public String getColumnName(int column) {
        return super.getColumnName(column); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return switch (columnIndex) {
            case 0 ->
                String.class;
            case 1 ->
                Integer.class;
            default ->
                Object.class;
        };
    }

    public void updateTableData(List<Student> students) {
        setRowCount(0);
        for (Student student : students) {
            Object[] rowData = {student.getName(), student.getAge()};
            addRow(rowData);
        }
    }
}
