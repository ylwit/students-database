package pl.wit.studentsdatabase.view;


import javax.swing.*;

/**
 * Klasa z ramką do zarządzania zakładkami z panelami
 * 
 * 
 * @author Yuliia Loianich
 * @coauthor Emil Sell
 * 
 */
public class MainView extends JFrame {

    public MainView() {
        setTitle("Baza danych studentów");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTabbedPane tabbedPane = new JTabbedPane();
        
        // Students
        JPanel studentsPanel1 = new StudentsPanelCreator().getPanel();
        tabbedPane.addTab("Students Pierwotny", studentsPanel1);   
        
        // Students
        JPanel studentsPanel = new StudentsPanelCreatorV2().getPanel();
        tabbedPane.addTab("Studenci", studentsPanel);        
        
        // Groups
        JPanel groupsPanel = new GroupsPanelCreator().getPanel();
        tabbedPane.addTab("Grupy", groupsPanel);
        
        // Subjects
        JPanel subjectPanel = new SubjectsPanelCreator().getPanel();
        tabbedPane.addTab("Przedmioty", subjectPanel);

        // Scores 
        //JPanel scoresPanel = new ScoresPanelCreator().getPanel();
        //tabbedPane.addTab("Wyniki", scoresPanel);

        add(tabbedPane);
    }

}