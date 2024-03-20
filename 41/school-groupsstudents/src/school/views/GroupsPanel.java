package school.views;


import school.model.Group;
import school.model.SchoolRepository;
import java.awt.BorderLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class GroupsPanel extends JPanel {
    private final SchoolRepository grupoRepository;
    private JList<Group> groupList;

    public GroupsPanel(SchoolRepository grupoRepository) {
        this.grupoRepository = grupoRepository;
        initUI();
    }

    private void initUI() {
        setLayout(new BorderLayout());

        groupList = new JList<>(new DefaultListModel<>());
        for (Group group : grupoRepository.getGroups()) {
            ((DefaultListModel<Group>) groupList.getModel()).addElement(group);
        }
        JScrollPane groupScrollPane = new JScrollPane(groupList);
        add(new JLabel("Groups"), BorderLayout.NORTH);
        add(groupScrollPane, BorderLayout.CENTER);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        groupList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                Group selectedGrupo = groupList.getSelectedValue();
                listener.propertyChange(new PropertyChangeEvent(this, "selectedGroup", null, selectedGrupo));
            }
        });
    }
}
