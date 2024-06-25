package pl.wit.studentsdatabase.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import pl.wit.studentsdatabase.FileHandler;
import pl.wit.studentsdatabase.model.Student;
import pl.wit.studentsdatabase.repository.StudentRepository;

/**
 * 
 * Klasa tworząca panel do zarządzania studentami
 * 
 * 
 * @author Emil Sell
 * 
 */

public class StudentsPanelCreatorV2 implements ActionListener {

	private JPanel main;
	private JScrollPane tablePanel;
	private JTable table;
	private DefaultTableModel model;
	private JPanel optionPanel;
	private final String[] labels = { "Id", "Imię", "Nazwisko", "Nr Albumu" };
	private JButton open;
	private JButton save;
	private JButton filter;
	private JButton edit;
	private JButton remove;
	private JButton add;

	StudentRepository repository;
	FileHandler<Student> fh;

	StudentsPanelCreatorV2() {
		fh = null;
		repository = StudentRepository.getInstance();
		model = new DefaultTableModel(labels, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false; // All cells are non-editable
			}
		};
		table = new JTable(model);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablePanel = new JScrollPane(table);
		main = new JPanel(new BorderLayout());

		optionPanel = new JPanel();
		open = new JButton("Open file");
		open.addActionListener(this);
		save = new JButton("Save to file");
		save.addActionListener(this);

		filter = new JButton("Filtruj");
		filter.addActionListener(this);
		add = new JButton("Add");
		add.addActionListener(this);
		remove = new JButton("Remove");
		remove.addActionListener(this);
		edit = new JButton("Edit");
		edit.addActionListener(this);

		optionPanel.add(open);
		optionPanel.add(save);
		optionPanel.add(add);
		optionPanel.add(remove);
		optionPanel.add(edit);

		main.add(tablePanel, BorderLayout.NORTH);
		main.add(optionPanel, BorderLayout.SOUTH);
	}

	public JPanel getPanel() {
		return main;
	}

	private void addRows(List<Student> students) {
		repository.addStudents(students);
		for (Student student : students)
			addRow(student);
	}

	private void addRow(Student student) {
		model.addRow(
				new Object[] { student.getId(), student.getFirstName(), student.getLastName(), student.getAlbumId() });
	}

	private void clearTable() {
		model.setRowCount(0);
	}

	private void add() {
		JTextField firstNameField = new JTextField();
		JTextField lastNameField = new JTextField();
		JTextField albumIdField = new JTextField();

		JPanel panel = new JPanel(new GridLayout(3, 2));
		panel.add(new JLabel("Imię:"));
		panel.add(firstNameField);
		panel.add(new JLabel("Nazwisko:"));
		panel.add(lastNameField);
		panel.add(new JLabel("Nr Albumu:"));
		panel.add(albumIdField);
		panel.setPreferredSize(new Dimension(400, 100));

		int option = JOptionPane.showConfirmDialog(null, panel, "Add Student", JOptionPane.OK_CANCEL_OPTION);
		if (option == JOptionPane.OK_OPTION) {
			Student student = new Student(firstNameField.getText(), lastNameField.getText(),
					Integer.parseInt(albumIdField.getText()));
			repository.addStudent(student);
			addRow(student);
		}
	}

	private void remove() {
		int selectedRow = table.getSelectedRow();
		if (selectedRow != -1) {
			model.removeRow(selectedRow);
			repository.deleteStudentById((int) model.getValueAt(selectedRow, 0));
		} else {
			JOptionPane.showMessageDialog(main, "Nie wybrano wierszu", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void edit() {
		int selectedRow = table.getSelectedRow();
		if (selectedRow != -1) {
			int id = (int) model.getValueAt(selectedRow, 0);
			String firstName = (String) model.getValueAt(selectedRow, 1);
			String lastName = (String) model.getValueAt(selectedRow, 2);
			int albumId = (int) model.getValueAt(selectedRow, 3);

			JTextField idField = new JTextField(String.valueOf(id));
			idField.setEditable(false);
			JTextField firstNameField = new JTextField(firstName);
			JTextField lastNameField = new JTextField(lastName);
			JTextField albumIdField = new JTextField(String.valueOf(albumId));

			JPanel panel = new JPanel(new GridLayout(4, 2));
			panel.add(new JLabel("Id:"));
			panel.add(idField);
			panel.add(new JLabel("Imię:"));
			panel.add(firstNameField);
			panel.add(new JLabel("Nazwisko:"));
			panel.add(lastNameField);
			panel.add(new JLabel("Nr Albumu:"));
			panel.add(albumIdField);
			panel.setPreferredSize(new Dimension(400, 100));

			int option = JOptionPane.showConfirmDialog(null, panel, "Edit Student", JOptionPane.OK_CANCEL_OPTION);
			if (option == JOptionPane.OK_OPTION) {
				model.setValueAt(idField.getText(), selectedRow, 0);
				model.setValueAt(firstNameField.getText(), selectedRow, 1);
				model.setValueAt(lastNameField.getText(), selectedRow, 2);
				model.setValueAt(albumIdField.getText(), selectedRow, 3);

				repository.updateStudent(new Student(firstNameField.getText(), lastNameField.getText(),
						Integer.parseInt(albumIdField.getText())));
			}
		} else {
			JOptionPane.showMessageDialog(main, "Nie wybrano wierszu", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void save() {
		if (fh != null) {
			fh.overwrite(repository.getStudents());
		} else {
			JOptionPane.showMessageDialog(main, "Nie można nadpisać pliku", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void open() {
		String path;
		JFileChooser fileChooser = new JFileChooser();
		int response = fileChooser.showOpenDialog(null);
		if (response == JFileChooser.APPROVE_OPTION) {
			clearTable();
			path = fileChooser.getSelectedFile().getAbsolutePath();
			fh = new FileHandler<>(path);
			List<Student> list = (List<Student>) fh.readAll();
			if (list.get(0).getClass() == Student.class)
				repository.addStudents(list);
			addRows(repository.getStudents());
		} else {
			JOptionPane.showMessageDialog(main, "Nie wybrano pliku", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void filter() {

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton source = (JButton) e.getSource();
		if (source == open) {
			open();
		} else if (source == save) {
			save();
		} else if (source == edit) {
			edit();
		} else if (source == remove) {
			remove();
		} else if (source == add) {
			add();
		} else if (source == filter) {
			filter();
		}
	}

}
