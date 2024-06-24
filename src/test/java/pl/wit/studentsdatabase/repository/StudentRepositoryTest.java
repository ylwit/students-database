package pl.wit.studentsdatabase.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertTrue;

import pl.wit.studentsdatabase.model.Student;


/**
 * 
 * 
 * @author Bartłomiej Perkowski
 * 
 */
public class StudentRepositoryTest {
	
	StudentRepository repo = StudentRepository.getInstance();
		
	@Test
	void constructorTest() {					
		assertTrue(repo instanceof StudentRepository);
	}
	@Test
	void newItemAddedTest() {
		repo.addStudent(new Student(1,"Jan","Kowalski",12340));
		assertFalse(repo.getStudents().isEmpty());
		
		repo.addStudent(new Student(2,"Jan","Kochanowski",12341));
		assertFalse(repo.getStudents().isEmpty());

		repo.addStudent(new Student(3,"Jan","Nowak",12342));
		assertFalse(repo.getStudents().isEmpty());
	}
	@Test
	void itemDeletedSuccessfullyTest() {
		repo.addStudent(new Student(1,"Jan","Kowalski",12340));
		assertFalse(repo.getStudents().isEmpty());
		assertTrue(repo.getStudents().size() == 1);	
		
		repo.addStudent(new Student(2,"Jan","Kochanowski",12341));
		assertFalse(repo.getStudents().isEmpty());
		assertTrue(repo.getStudents().size() == 2);	
		
		System.out.println(repo.getStudentById(1));
		repo.deleteStudentById(1);
		assertTrue(repo.getStudentById(1) == null);	
	}
	
	@Test
	void studentUpdatedSuccessfullyTest() {
		
		repo.addStudent(new Student(1,"Jan","Kowalski",12340));
		assertFalse(repo.getStudents().isEmpty());
		
		repo.updateStudent(new Student(1,"Adam", "Nowak", 100));
		assertTrue(repo.getStudentByAlbumId(100).getAlbumId() == 100);
	}
	
	
	}


