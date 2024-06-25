package pl.wit.studentsdatabase.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import pl.wit.studentsdatabase.model.Student;
import pl.wit.studentsdatabase.repository.StudentRepository;

/**
 * Klasa tworząca panel do zarządzania studentami
 * 
 * 
 * @author Yuliia Loianich
 * 
 */
public class StudentsPanelCreator {
	
	private JPanel mainPanel;
	
	private JLabel jblStudentId;
    private JTextField txtStudentId;
    
    private JLabel jblFirstName;
    private JTextField txtFirstName;
    
    private JLabel jblLastName;
    private JTextField txtLastName;
    
    private JLabel jblAlbumId;
    private JTextField txtAlbumId;
    
    private JButton btnAddStudent;
    private JButton btnDeleteStudent;
    private JButton btnUpdateStudent;
    
    private JTextField txtSearchField;
    private JButton btnSearchById;
    private JButton btnSearchByAlbumId;
    private JButton btnSearchByFirstName;
    private JButton btnSearchByLastName;
    
    private JTextArea txaStudentList;
	
	
	public StudentsPanelCreator() {
        mainPanel = new JPanel(new BorderLayout());

        // Budowanie panelu do dodawania i aktualizacji studentów
        JPanel pnlStudentRepoModification = new JPanel(new GridBagLayout());
        pnlStudentRepoModification.setBorder(BorderFactory.createTitledBorder("Edycja"));

        GridBagConstraints gridContraints = new GridBagConstraints();
        gridContraints.insets = new Insets(5, 5, 5, 5);
        gridContraints.fill = GridBagConstraints.HORIZONTAL;

        // Dodawanie komponentów do panelu z odpowiednimi proporcjami
        jblStudentId = new JLabel("Identyfikator studenta:");
        txtStudentId = new JTextField();
        addLabelAndField(pnlStudentRepoModification, gridContraints, jblStudentId, txtStudentId, 0);
        
        jblFirstName = new JLabel("Imię:");
        
        txtFirstName = new JTextField();
        addLabelAndField(pnlStudentRepoModification, gridContraints, jblFirstName, txtFirstName, 1);
        
        jblLastName = new JLabel("Nazwisko:");
        txtLastName = new JTextField();
        addLabelAndField(pnlStudentRepoModification, gridContraints, jblLastName, txtLastName, 2);
        
        jblAlbumId = new JLabel("Nr lbumu:");
        txtAlbumId = new JTextField();
        addLabelAndField(pnlStudentRepoModification, gridContraints, jblAlbumId, txtAlbumId, 3);
        

        // Wymiarowanie przycisków
        gridContraints.gridx = 0;
        gridContraints.gridy = 4;
        gridContraints.gridwidth = 1;
        gridContraints.weightx = 1;
        gridContraints.fill = GridBagConstraints.BOTH; 
        
        btnAddStudent = new JButton("Iteracyjnie dodaj studenta");
        pnlStudentRepoModification.add(btnAddStudent, gridContraints);

        gridContraints.gridx = 1;
        btnUpdateStudent = new JButton("Zmień dane studenta");
        pnlStudentRepoModification.add(btnUpdateStudent, gridContraints);

        gridContraints.gridx = 2;
        btnDeleteStudent = new JButton("Usuń studenta używając identyfikatora");
        pnlStudentRepoModification.add(btnDeleteStudent, gridContraints);

        mainPanel.add(pnlStudentRepoModification, BorderLayout.NORTH);

        // Panel do wyszukiwania studentów
        JPanel searchPanel = new JPanel(new GridBagLayout());
        searchPanel.setBorder(BorderFactory.createTitledBorder("Wyszukiwanie"));

        GridBagConstraints sc = new GridBagConstraints();
        sc.insets = new Insets(5, 5, 5, 5);
        sc.fill = GridBagConstraints.HORIZONTAL;
        sc.gridx = 0;
        sc.gridwidth = 1;
        sc.weightx = 1;

        // Pole na wartości
        sc.gridy = 0;
        txtSearchField = new JTextField();
        txtSearchField.setPreferredSize(new Dimension(txtAlbumId.getPreferredSize()));
        searchPanel.add(txtSearchField, sc);

        // Przyciski
        sc.fill = GridBagConstraints.HORIZONTAL;
        sc.gridy = 1;
        btnSearchById = new JButton("Szukaj po identyfikatorze");
        searchPanel.add(btnSearchById, sc);

        sc.gridy = 2;
        btnSearchByAlbumId = new JButton("Szukaj po nr albumu");
        searchPanel.add(btnSearchByAlbumId, sc);

        sc.gridy = 3;
        btnSearchByFirstName = new JButton("Szukaj po imieniu");
        searchPanel.add(btnSearchByFirstName, sc);

        sc.gridy = 4;
        btnSearchByLastName = new JButton("Szukaj po nazwisku");
        searchPanel.add(btnSearchByLastName, sc);

        mainPanel.add(searchPanel, BorderLayout.WEST);

        // Wyświetlanie listy studentów
        txaStudentList = new JTextArea();
        txaStudentList.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(txaStudentList);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Listenery dla przycisków
        btnAddStudent.addActionListener(new AddStudentListener());
        btnUpdateStudent.addActionListener(new UpdateStudentListener());
        btnDeleteStudent.addActionListener(new DeleteStudentListener());
        btnSearchById.addActionListener(new SearchByIdListener());
        btnSearchByAlbumId.addActionListener(new SearchByAlbumIdListener());
        btnSearchByFirstName.addActionListener(new SearchByFirstNameListener());
        btnSearchByLastName.addActionListener(new SearchByLastNameListener());
    }
	
    public JPanel getPanel() {
		return mainPanel;
	}

	// Dodawanie labelki i pola tekstowego w jednym wierszu
    private void addLabelAndField(JPanel panel, GridBagConstraints c, JLabel label, JTextField textField, int yPos) {
        c.gridx = 0;
        c.gridy = yPos;
        c.gridwidth = 1;
        c.weightx = 0.2;
        panel.add(label, c);

        c.gridx = 1;
        c.gridy = yPos;
        c.gridwidth = 4;
        c.weightx = 0.8;
        panel.add(textField, c);
    }

    private class AddStudentListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String firstName = txtFirstName.getText();
            String lastName = txtLastName.getText();
            int albumId;
            try {
                albumId = Integer.parseInt(txtAlbumId.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(mainPanel, "Nr albumu ma niepoprawną wartość. Nr albumu musi być numerem.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Student student = new Student(firstName, lastName, albumId);
            StudentRepository.getInstance().addStudent(student);
            updateStudentList();
            resetFieldsValues();
        }
    }

    private class UpdateStudentListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int studentId;
            try {
                studentId = Integer.parseInt(txtStudentId.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(mainPanel, "Identyfikator studenta ma niepoprawną wartość. Identyfikator studenta musi być numerem", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String firstName = txtFirstName.getText();
            String lastName = txtLastName.getText();
            int albumId;
            try {
                albumId = Integer.parseInt(txtAlbumId.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(mainPanel, "Nr albumu ma niepoprawną wartość. Nr albumu musi być numerem", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Student student = new Student(studentId, firstName, lastName, albumId);
            StudentRepository.getInstance().updateStudent(student);
            updateStudentList();
            resetFieldsValues();
        }
    }

    private class DeleteStudentListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int studentId;
            try {
                studentId = Integer.parseInt(txtStudentId.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(mainPanel, "Identyfikator studenta ma niepoprawną wartość. Identyfikator studenta musi być numerem", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            StudentRepository.getInstance().deleteStudentById(studentId);
            updateStudentList();
            //resetFieldsValues();
        }
    }

    private class SearchByIdListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int studentId;
            try {
                studentId = Integer.parseInt(txtSearchField.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(mainPanel, "Identyfikator studenta ma niepoprawną wartość. Identyfikator studenta musi być numerem", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Student student = StudentRepository.getInstance().getStudentById(studentId);
            displaySingleStudent(student);
        }
    }

    private class SearchByAlbumIdListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int albumId;
            try {
                albumId = Integer.parseInt(txtSearchField.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(mainPanel, "Nr albumu ma niepoprawną wartość. Nr albumu musi być numerem", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Student student = StudentRepository.getInstance().getStudentByAlbumId(albumId);
            displaySingleStudent(student);
        }
    }

    private class SearchByFirstNameListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String firstName = txtSearchField.getText();
            List<Student> students = StudentRepository.getInstance().getStudentsByFirstName(firstName);
            displayStudents(students);
        }
    }

    private class SearchByLastNameListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String lastName = txtSearchField.getText();
            List<Student> students = StudentRepository.getInstance().getStudentsByLastName(lastName);
            displayStudents(students);
        }
    }

    private void updateStudentList() {
        List<Student> students = StudentRepository.getInstance().getStudents();
        displayStudents(students);
    }

    void displayStudents(List<Student> students) {
        txaStudentList.setText("");
        if (students != null && !students.isEmpty()) {
            for (Student student : students) {
                txaStudentList.append(student.toString() + "\n");
            }
        } else {
            txaStudentList.append("Brak danych.\n");
        }
    }

    private void displaySingleStudent(Student student) {
        txaStudentList.setText("");
        if (student != null) {
            txaStudentList.append(student.toString() + "\n");
        } else {
            txaStudentList.append("Brak danych.\n");
        }
    }

    private void resetFieldsValues() {
        txtStudentId.setText("");
        txtFirstName.setText("");
        txtLastName.setText("");
        txtAlbumId.setText("");
        txtSearchField.setText("");
    }

}
