package pl.wit.studentsdatabase.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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
import pl.wit.studentsdatabase.model.Group;
import pl.wit.studentsdatabase.repository.GroupRepository;

/**
 * 
 * Klasa tworząca panel do zarządzania grupami
 * 
 * 
 * @author Emil Sell
 * 
 */

public class GroupsPanelCreator implements ActionListener {

	private JPanel main;
	private JScrollPane tablePanel;
	private JTable table;
	private DefaultTableModel model;
	private JPanel optionPanel;
	private final String[] labels = { "Id", "Nazwa", "Kod grupy", "Specjalizacja", "Opis", "Lista studentów" };
	private JButton open;
	private JButton save;
	private JButton filter;
	private JButton edit;
	private JButton remove;
	private JButton add;

	GroupRepository repository;
	FileHandler<Group> fh;

	GroupsPanelCreator() {
		fh = null;
		repository = GroupRepository.getInstance();
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

	private void addRows(List<Group> groups) {
		repository.addGroups(groups);
		for (Group group : groups)
			addRow(group);
	}

	private void addRow(Group group) {
		model.addRow(new Object[] { group.getId(), group.getGroupCode(), group.getSpecialization(),
				group.getDescription(), group.getStudents()});
	}

	private void clearTable() {
		model.setRowCount(0);
	}

	private void add() {
		JTextField groupCode = new JTextField();
		JTextField specialization = new JTextField();
		JTextField desc = new JTextField();
		JTextField students = new JTextField();

		JPanel panel = new JPanel(new GridLayout(4, 2));
		panel.add(new JLabel("Kod grupy:"));
		panel.add(groupCode);
		panel.add(new JLabel("Specjalizacja:"));
		panel.add(specialization);
		panel.add(new JLabel("Opis:"));
		panel.add(desc);
		panel.add(new JLabel("Students"));
		panel.add(students);
		panel.setPreferredSize(new Dimension(500, 100));

		int option = JOptionPane.showConfirmDialog(null, panel, "Add Group", JOptionPane.OK_CANCEL_OPTION);
		if (option == JOptionPane.OK_OPTION) {
			
			Group group = new Group(groupCode.getText(), specialization.getText(), desc.getText(), getIntListFromString(students.getText()));
			repository.addGroup(group);
			addRow(group);
		}
	}
	
	private static List<Integer> getIntListFromString(String input) {
        List<Integer> integerList = new ArrayList<>();
        String[] parts = input.split("[,\\");

        for (String part : parts) {
            try {
                Integer number = Integer.parseInt(part.trim());
                integerList.add(number);
            } catch (NumberFormatException e) {
                // Handle non-integer inputs if needed
                System.err.println("Ignoring non-integer input: " + part);
            }
        }

        return integerList;
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
			int idv = (int) model.getValueAt(selectedRow, 0);
			String groupCodeV = (String) model.getValueAt(selectedRow, 1);
			String specializationV = (String) model.getValueAt(selectedRow, 2);
			String descV = (String) model.getValueAt(selectedRow, 3);
			List<Integer> list =  (List<Integer>) model.getValueAt(selectedRow, 4);
			
			JTextField id = new JTextField(String.valueOf(idv));
			id.setEditable(false);
			JTextField groupCode = new JTextField(groupCodeV);
			JTextField specialization = new JTextField(specializationV);
			JTextField desc = new JTextField(descV);
			JTextField students = new JTextField();

			JPanel panel = new JPanel(new GridLayout(5, 2));
			panel.add(new JLabel("Id:"));
			panel.add(id);
			panel.add(new JLabel("Kod grupy:"));
			panel.add(groupCode);
			panel.add(new JLabel("Specjalizacja:"));
			panel.add(specialization);
			panel.add(new JLabel("Opis:"));
			panel.add(desc);
			panel.add(new JLabel("Students"));
			panel.add(students);
			panel.setPreferredSize(new Dimension(500, 100));

			int option = JOptionPane.showConfirmDialog(null, panel, "Edit Group", JOptionPane.OK_CANCEL_OPTION);
			if (option == JOptionPane.OK_OPTION) {
				model.setValueAt(Integer.parseInt(id.getText()), selectedRow, 0);
				model.setValueAt(groupCode.getText(), selectedRow, 1);
				model.setValueAt(specialization.getText(), selectedRow, 2);
				model.setValueAt(desc.getText(), selectedRow, 3);
				model.setValueAt(getIntListFromString(students.getText()), selectedRow, 4);
				
				Group group = new Group(groupCode.getText(), specialization.getText(), desc.getText(), getIntListFromString(students.getText()));
				repository.addGroup(group);
				addRow(group);
			}
		} else {
			JOptionPane.showMessageDialog(main, "Nie wybrano wierszu", "Error", JOptionPane.ERROR_MESSAGE);
		}
		
	}

	private void save() {
		if (fh != null) {
			fh.overwrite(repository.getGroups());
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
			List<Group> list = (List<Group>) fh.readAll();
			if (list.get(0).getClass() == Group.class)
				repository.addGroups(list);
			addRows(repository.getGroups());
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
