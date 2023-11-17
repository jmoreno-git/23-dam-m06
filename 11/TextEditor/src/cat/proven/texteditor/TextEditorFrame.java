 package cat.proven.texteditor;


import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * Main frame for text editor example
 * @author Jose
 */
public class TextEditorFrame extends JFrame implements ActionListener {

    private final String frameTitle;
    private final ActionListener actionListener;
    private final String aboutMessage;
    private TextEditorPanel editorPanel;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TextEditorFrame().setVisible(true);
        });
    }
    
    public TextEditorFrame() {
        actionListener = this;
        frameTitle = "Text editor";
        aboutMessage = "<html><p>TextEditor</p><p>(c) ProvenSoft 2023</p></html>";
        initComponents();
    }

    private void initComponents() {
           UIManager.put("swing.boldMetal", Boolean.FALSE);
           setTitle(frameTitle);
           setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
           addWindowListener(new WindowAdapter() {
               @Override
               public void windowClosing(WindowEvent e) {
                   handleExit();
               }
           });
           createMenuBar();
           Container contentPane = getContentPane();
           editorPanel = new TextEditorPanel();
           contentPane.add(editorPanel);
           pack();
    }

    private void createMenuBar() {
        JMenuBar mnuBar = new JMenuBar();
        JMenu mnu;
        JMenuItem mItem;
        //
        mnu = new JMenu("File");
            //
            mItem = new JMenuItem("Open");
                mItem.setActionCommand("open");
                mItem.addActionListener(actionListener);
            mnu.add(mItem);
            //
            mItem = new JMenuItem("Save");
                mItem.setActionCommand("save");
                mItem.addActionListener(actionListener);
            mnu.add(mItem);
            //
            mItem = new JMenuItem("Exit");
                mItem.setActionCommand("exit");
                mItem.addActionListener(actionListener);
            mnu.add(mItem);
            //
        mnuBar.add(mnu);
        //
        mnu = new JMenu("Help");
            //
            mItem = new JMenuItem("About");
                mItem.setActionCommand("about");
                mItem.addActionListener(actionListener);
            mnu.add(mItem);
            //
        mnuBar.add(mnu);
        //
        setJMenuBar(mnuBar);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        switch (action) {
            case "open" -> handleOpen();
            case "save" -> handleSave();
            case "exit" -> handleExit();
            case "about" -> handleAbout();
        }
    }

    private void handleAbout() {
        JOptionPane.showMessageDialog(this, aboutMessage, "About", JOptionPane.INFORMATION_MESSAGE);
    }

    private void handleExit() {
        int answer = JOptionPane.showConfirmDialog(this, "Sure to exit?", "Exit", JOptionPane.INFORMATION_MESSAGE);
        if (answer == JOptionPane.OK_OPTION) {
            System.exit(0);
        }
    }

    private void handleOpen() {
        editorPanel.handleOpen();
    }

    private void handleSave() {
        editorPanel.handleSave();
    }

}
