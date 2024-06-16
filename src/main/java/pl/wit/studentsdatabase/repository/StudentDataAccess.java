package pl.wit.studentsdatabase.repository;

import java.util.List;

import pl.wit.studentsdatabase.model.Student;


/**
 * Interfejs opisujący operacje jakie są możliwe do wykonania na danych studentów dostępnych w magazynie
 * 
 * 
 * @author Yuliia Loianich
 * 
 */
public interface StudentDataAccess {
	
	List<Student> getStudents();
	
	Student getStudentById(int studentId);
	
	Student getStudentByAlbumId(int studentId);
	
	List<Student> getStudentsByLastName(String lastName);
	
	List<Student> getStudentsByFirstName(String firstName);
	
	void addStudent(Student newStudent);
	
	void addStudents(List<Student> newStudents);
	
	void deleteStudentById(int studentId);
	
	void updateStudent(Student student);

}