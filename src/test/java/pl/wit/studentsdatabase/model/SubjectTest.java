package pl.wit.studentsdatabase.model;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import junit.framework.AssertionFailedError;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assert.assertTrue;


/**
 * 
 * 
 * @author Bartłomiej Perkowski
 * 
 */
public class SubjectTest {
	
	@Test
	void constructor1Test() {
		int[] pt = {10,20,25};
		Subject s = new Subject("Matematyka", 30 , pt);
	}
	@Test
	void constructor2Test() {
		int id = 0;
		int[] pt = {10,20,25};
		Subject s = new Subject(0,"Matematyka", 30 , pt);
	}
	@Test
	void getMarkBasingOnNumberOfPointsTest() {
		int id = 0;
		int[] pt = {10,20,25};
		Subject s = new Subject(0,"Matematyka", 30 , pt);
		int sign = s.getMarkBasingOnNumberOfPoints(21);
		assertEquals(4, sign);
	}
	@Test
	void toStringTest() {
		int id = 0;
		int[] pt = {10,20,25};
		Subject s = new Subject(0,"Matematyka", 30 , pt);
		
		assertTrue(s.toString().equals("Subject [id=0, name=Matematyka, maxPoints=30, gradingCriteria=[10, 20, 25]]"));
	}

}
