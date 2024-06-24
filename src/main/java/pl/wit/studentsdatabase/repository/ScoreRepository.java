package pl.wit.studentsdatabase.repository;

import java.util.ArrayList;
import java.util.List;

import pl.wit.studentsdatabase.model.Score;
import pl.wit.studentsdatabase.model.Subject;

/**
 * 
 * Interfejs opisujący operacje jakie są możliwe do wykonania na danych wyników dostępnych w magazynie
 * 
 * @author Emil Sell
 * 
 */

public class ScoreRepository implements ScoreDataAccess {

	private static ScoreRepository instance;

	private List<Score> scores;

	ScoreRepository() {
		scores = new ArrayList<Score>();
	}

	public static ScoreRepository getInstance() {
		if (instance == null) {
			instance = new ScoreRepository();
		}
		return instance;
	}

	@Override
	public List<Score> getAll() {
		return scores;
	}

	@Override
	public Score getById(int id) {
		for (Score score : scores) {
			if (score.getId() == id)
				return score;
		}
		return null;
	}

	@Override
	public Score getByStudentId(int id) {
		for (Score score : scores) {
			if (score.getStudentId() == id)
				return score;
		}
		return null;
	}

	@Override
	public Score getBySubjectId(int id) {
		for (Score score : scores) {
			if (score.getSubjectId() == id)
				return score;
		}
		return null;
	}

	@Override
	public void deleteById(int id) {
		Score temp = getById(id);
		scores.remove(temp);
		
		// tu overwrite pliku 

	}

	@Override
	public void add(Score score) {
		scores.add(score);
		
		// tu append do pliku
	}

	@Override
	public void add(List<Score> scores) {
		scores.addAll(scores);
		
		// tu append do pliku 
	}

	@Override
	public void update(Score score) {
		Score target = getById(score.getId());
		
		target.setStudentId(score.getStudentId());
		target.setSubjectId(score.getSubjectId());
		target.setScoredPoints(score.getScoredPoints());
		
		// tu overwrite pliku 
	}

}
