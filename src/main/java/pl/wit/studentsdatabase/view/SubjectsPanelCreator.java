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
import pl.wit.studentsdatabase.model.Subject;
import pl.wit.studentsdatabase.repository.SubjectRepository;

/**
 * 
 * Klasa tworząca panel do zarządzania przedmiotami
 * 
 * 
 * @author Emil Sell
 * 
 */

public class SubjectsPanelCreator implements ActionListener {

	private JPanel main;
	private JScrollPane tablePanel;
	private JTable table;
	private DefaultTableModel model;
	private JPanel optionPanel;
	private final String[] labels = { "Id", "Nazwa", "Punkty MAX", "Punkty na 3", "Punkty na 4", "Punkty na 5" };
	private JButton open;
	private JButton save;
	private JButton filter;
	private JButton edit;
	private JButton remove;
	private JButton add;

	SubjectRepository repository;
	FileHandler<Subject> fh;

	SubjectsPanelCreator() {
		fh = null;
		repository = SubjectRepository.getInstance();
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

	private void addRows(List<Subject> subjects) {
		repository.add(subjects);
		for (Subject subject : subjects)
			addRow(subject);
	}

	private void addRow(Subject subject) {
		model.addRow(new Object[] { subject.getId(), subject.getName(), subject.getMaxPoints(),
				subject.getGradingCriteria()[0], subject.getGradingCriteria()[1], subject.getGradingCriteria()[2] });
	}

	private void clearTable() {
		model.setRowCount(0);
	}

	private void add() {
		JTextField nameField = new JTextField();
		JTextField maxPointsField = new JTextField();
		JTextField gradingCriteria3Field = new JTextField();
		JTextField gradingCriteria4Field = new JTextField();
		JTextField gradingCriteria5Field = new JTextField();

		JPanel panel = new JPanel(new GridLayout(5, 2));
		panel.add(new JLabel("Nazwa:"));
		panel.add(nameField);
		panel.add(new JLabel("Punkty MAX:"));
		panel.add(maxPointsField);
		panel.add(new JLabel("Punkty na 3:"));
		panel.add(gradingCriteria3Field);
		panel.add(new JLabel("Punkty na 4:"));
		panel.add(gradingCriteria4Field);
		panel.add(new JLabel("Punkty na 5:"));
		panel.add(gradingCriteria5Field);
		panel.setPreferredSize(new Dimension(600, 100));

		int option = JOptionPane.showConfirmDialog(null, panel, "Add Student", JOptionPane.OK_CANCEL_OPTION);
		if (option == JOptionPane.OK_OPTION) {
			int[] gradingCriteria = {Integer.parseInt(gradingCriteria3Field.getText()), Integer.parseInt(gradingCriteria4Field.getText()), Integer.parseInt(gradingCriteria5Field.getText())};
			Subject subject = new Subject(nameField.getText(), Integer.parseInt(maxPointsField.getText()), gradingCriteria);
			repository.add(subject);
			addRow(subject);
		}
	}

	private void remove() {
		int selectedRow = table.getSelectedRow();
		if (selectedRow != -1) {
			model.removeRow(selectedRow);
			repository.deleteById((int) model.getValueAt(selectedRow, 0));
		} else {
			JOptionPane.showMessageDialog(main, "Nie wybrano wierszu", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void edit() {
		int selectedRow = table.getSelectedRow();
		if (selectedRow != -1) {
			int id = (int) model.getValueAt(selectedRow, 0);
			String name = (String) model.getValueAt(selectedRow, 1);
			int maxPoints = (int) model.getValueAt(selectedRow, 3);
			int points3 = (int) model.getValueAt(selectedRow, 4);
			int points4 = (int) model.getValueAt(selectedRow, 5);
			int points5 = (int) model.getValueAt(selectedRow, 6);

			JTextField idField = new JTextField(String.valueOf(id));
			idField.setEditable(false);
			JTextField nameField = new JTextField(name);
			JTextField maxPointsField = new JTextField(String.valueOf(maxPoints));
			JTextField gradingCriteria3Field = new JTextField(String.valueOf(points3));
			JTextField gradingCriteria4Field = new JTextField(String.valueOf(points4));
			JTextField gradingCriteria5Field = new JTextField(String.valueOf(points5));

			JPanel panel = new JPanel(new GridLayout(6, 2));
			panel.add(new JLabel("Id:"));
			panel.add(idField);
			panel.add(new JLabel("Nazwa:"));
			panel.add(nameField);
			panel.add(new JLabel("Punkty MAX:"));
			panel.add(maxPointsField);
			panel.add(new JLabel("Punkty na 3:"));
			panel.add(gradingCriteria3Field);
			panel.add(new JLabel("Punkty na 4:"));
			panel.add(gradingCriteria4Field);
			panel.add(new JLabel("Punkty na 5:"));
			panel.add(gradingCriteria5Field);
			panel.setPreferredSize(new Dimension(600, 100));

			int option = JOptionPane.showConfirmDialog(null, panel, "Edit Student", JOptionPane.OK_CANCEL_OPTION);
			if (option == JOptionPane.OK_OPTION) {
				model.setValueAt(Integer.parseInt(idField.getText()), selectedRow, 0);
				model.setValueAt(nameField.getText(), selectedRow, 1);
				model.setValueAt(Integer.parseInt(maxPointsField.getText()), selectedRow, 2);
				model.setValueAt(Integer.parseInt(gradingCriteria3Field.getText()), selectedRow, 3);
				model.setValueAt(Integer.parseInt(gradingCriteria4Field.getText()), selectedRow, 4);
				model.setValueAt(Integer.parseInt(gradingCriteria5Field.getText()), selectedRow, 5);
				int[] gradingCriteria = {Integer.parseInt(gradingCriteria3Field.getText()), Integer.parseInt(gradingCriteria4Field.getText()), Integer.parseInt(gradingCriteria5Field.getText())};
				Subject subject = new Subject(nameField.getText(), Integer.parseInt(maxPointsField.getText()), gradingCriteria);
				repository.update(subject);
				addRow(subject);
	
		} else {
			JOptionPane.showMessageDialog(main, "Nie wybrano wierszu", "Error", JOptionPane.ERROR_MESSAGE);
		}
		}
	}

	private void save() {
		if (fh != null) {
			fh.overwrite(repository.getAll());
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
			List<Subject> list = (List<Subject>) fh.readAll();
			if (list.get(0).getClass() == Subject.class)
				repository.add(list);
			addRows(repository.getAll());
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
