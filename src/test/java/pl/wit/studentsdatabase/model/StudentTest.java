package pl.wit.studentsdatabase.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assert.assertTrue;

/**
 * 
 * 
 * @author Bartłomiej Perkowski
 * 
 */
public class StudentTest {
	
	
	@Test
	void toStringTest() {
		
			Student s = new Student(1,"Natalie", "Kowalski", 7771);
			String output = "Student [id=" + s.getId() + ", firstName=" + s.getFirstName() + ", lastName=" + s.getLastName() + ", albumId=" + s.getAlbumId()
					+ "]";
			
//			System.out.println(rest);
			assertTrue(output.equals("Student [id=1, firstName=Natalie, lastName=Kowalski, albumId=7771]"));
			assertFalse(output.equals("Student [id=2, firstName=Natalia, lastName=Kowalski, albumId=7771]"));

	}
	
	@Test
	void constructor1Test() {
		Student s = new Student("Natalie", "Nowak", 1000);
		assertTrue(s instanceof Student);
	}
	@Test
	void constructor2Test() {
		Student s = new Student("Natalia", "Nowak", 10000);
		assertTrue(s instanceof Student);
	}

}
