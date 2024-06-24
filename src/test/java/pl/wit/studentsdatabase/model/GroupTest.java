package pl.wit.studentsdatabase.model;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 
 * 
 * @author Bartłomiej Perkowski
 * 
 */
public class GroupTest {
	
	
	@Test
	void constructor1Test() {
		
		List<Integer> list = new ArrayList<>(Arrays.asList(1,2,3,4,5));
		Group g = new Group("gCode","spec","desc", list);
		
		assertTrue(g instanceof Group);

	}
	@Test
	void constructor2Test() {
		
		List<Integer> list = new ArrayList<>(Arrays.asList(1,2,3,4,5));
		Group g = new Group(0,"gCode","spec","desc", list);
		
		assertTrue(g instanceof Group);
		
	}
	@Test
	void toStringTest() {
		
		List<Integer> list = new ArrayList<>(Arrays.asList(1,2,3,4,5));
		Group g = new Group(0,"gCode","spec","desc", list);
		
		String result = "Group [id=" + g.getId() + ", groupCode=" + g.getGroupCode() + ", specialization=" + g.getSpecialization() + ", description="
		+ g.getDescription() + ", students=" + g.getStudents().toString() + "]";
		
		//System.out.println(result);
		
		assertTrue(result.equals("Group [id=0, groupCode=gCode, specialization=spec, description=desc, students=[1, 2, 3, 4, 5]]"));
		
		
	}
	@Test
	void getGroupCodeTest() {
		
		List<Integer> list = new ArrayList<>(Arrays.asList(1,2,3,4,5));
		Group g = new Group(0,"gCode","spec","desc", list);
		
		assertTrue(g.getId() == 0);
		assertTrue(g.getId() != 127);
		
	}

	
	
	
}
