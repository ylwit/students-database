package pl.wit.studentsdatabase.repository;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import pl.wit.studentsdatabase.model.Student;

/**
 * 
 * Klasa obsługująca operacje i/o dla repozytorium studentów
 * 
 * @author Emil Sell
 * 
 */

public class StudentFileHandler {

	// scieżka do pliku 
	private String fileName = "students";

	// odczyt całego pliku
	public List<Student> read() {
		try (DataInputStream stream = new DataInputStream(new FileInputStream(fileName))) {
			List<Student> students = new ArrayList<Student>();
			while (stream.available() > 0) {
				int id = stream.readInt();
				String firstName = stream.readUTF();
				String lastName = stream.readUTF();
				int albumId = stream.readInt();
				students.add(new Student(id, firstName, lastName, albumId));
			}
			return students;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	// nadpisanie pliku
	public void write(List<Student> students) {
		try (DataOutputStream stream = new DataOutputStream(new FileOutputStream(fileName))) {
			for (Student student : students) {
				stream.writeInt(student.getId());
				stream.writeUTF(student.getFirstName());
				stream.writeUTF(student.getLastName());
				stream.writeInt(student.getAlbumId());
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// dopisanie na końcu pliku listy nowych studentów
	public void append(List<Student> students) {
		try (DataOutputStream stream = new DataOutputStream(new FileOutputStream(fileName, true))) {
			for (Student student : students) {
				stream.writeInt(student.getId());
				stream.writeUTF(student.getFirstName());
				stream.writeUTF(student.getLastName());
				stream.writeInt(student.getAlbumId());
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// dopisanie na koncu listy nowego studenta
	public void append(Student student) {
		try (DataOutputStream stream = new DataOutputStream(new FileOutputStream(fileName, true))) {
				stream.writeInt(student.getId());
				stream.writeUTF(student.getFirstName());
				stream.writeUTF(student.getLastName());
				stream.writeInt(student.getAlbumId());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
