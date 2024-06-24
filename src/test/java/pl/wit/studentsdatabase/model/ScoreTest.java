package pl.wit.studentsdatabase.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * 
 * 
 * @author Bartłomiej Perkowski
 * 
 */
public class ScoreTest {
	
	@Test
	void scoreConstructor1Test(){
		
		Score s = new Score(1, 1, 12);
		
		assertTrue(s instanceof Score);
		
	}
	@Test
	void scoreConstructor2Test(){
		
		Score s = new Score(1, 1, 1, 12);
		assertTrue(s instanceof Score);
		
	}
	@Test
	void toStringTest(){
		
		Score s = new Score(1, 1, 1, 12);
		assertTrue(s instanceof Score);
		String result = "Score [id=" + s.getId() + ", studentId=" + s.getStudentId() + ", subjectId=" + s.getSubjectId() + ", scoredPoints="
				+ s.getScoredPoints() + "]";
		
		//System.out.println(check);
		
		assertTrue(result.equals("Score [id=1, studentId=1, subjectId=1, scoredPoints=12]"));
	}

}
