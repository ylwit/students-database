package pl.wit.studentsdatabase.repository;

import java.util.ArrayList;
import java.util.List;

import pl.wit.studentsdatabase.model.Subject;

/**
 * 
 * Klasa reprezentująca magazyn przedmiotów.
 * 
 * @author Emil Sell
 * 
 */

public class SubjectRepository implements SubjectDataAccess {

	private static SubjectRepository instance;

	private List<Subject> subjects;

	SubjectRepository() {
		subjects = new ArrayList<Subject>();
	}

	public static SubjectRepository getInstance() {
		if (instance == null) {
			instance = new SubjectRepository();
		}
		return instance;
	}

	@Override
	public List<Subject> getAll() {
		return subjects;
	}

	@Override
	public Subject getByName(String name) {
		for (Subject subject : subjects) {
			if (subject.getName() == name)
				return subject;
		}
		return null;
	}

	@Override
	public Subject getById(int subjectId) {
		for (Subject subject : subjects) {
			if (subject.getId() == subjectId)
				return subject;
		}
		return null;
	}

	@Override
	public void add(Subject newSubject) {
		subjects.add(newSubject);
		
		// tu append do pliku 
	}

	@Override
	public void add(List<Subject> newSubjects) {
		subjects.addAll(newSubjects);
		
		// tu append do pliku 
	}

	@Override
	public void deleteById(int subjectId) {
		Subject temp = getById(subjectId);
		subjects.remove(temp);
		
		// tu overwrite pliku 
	}

	@Override
	public void update(Subject subject) {
		Subject target = getById(subject.getId());
		
		target.setName(subject.getName());
		target.setMaxPoints(subject.getMaxPoints());
		target.setGradingCriteria(subject.getGradingCriteria());
		
		// tu overwrite pliku 
	}

}
