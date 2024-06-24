package pl.wit.studentsdatabase.repository;

import java.util.List;

import pl.wit.studentsdatabase.model.Score;


/**
 * 
 * Klasa reprezentująca magazyn wyników.
 * 
 * @author Emil Sell
 * 
 */

public interface ScoreDataAccess {

	List<Score> getAll();

	Score getById(int id);
	
	Score getByStudentId(int id);
	
	Score getBySubjectId(int id);

	void deleteById(int id);

	void add(Score score);

	void add(List<Score> scores);

	void update(Score score);
	
}
