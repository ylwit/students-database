package pl.wit.studentsdatabase.view;

import javax.swing.*;

/**
 * Klasa z ramką do zarządzania zakładkami z panelami
 * 
 * 
 * @author Yuliia Loianich
 * 
 */
public class MainView extends JFrame {

    public MainView() {
        setTitle("Baza danych studentów");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTabbedPane tabbedPane = new JTabbedPane();

        // Zakładka "Students"
        JPanel studentsPanel = new StudentsPanelCreator().getPanel();
        tabbedPane.addTab("Studenci", studentsPanel);

        // Puste zakładki, które powinny zostać rozbudowane
        tabbedPane.addTab("Zapis/odczyt z pliku", new JPanel()); // TODO: tu powinien powstać panel dla ładowania i zapisywania danych do pliku
        tabbedPane.addTab("Grupy", new JPanel()); // TODO: tu powinien powstać panel dla grup
        tabbedPane.addTab("Przedmioty", new JPanel()); // TODO: tu powinien powstać panel dla przedmiotów
        tabbedPane.addTab("Wyniki", new JPanel()); // TODO: tu powinien powstać panel dla wyników

        add(tabbedPane);
    }

}