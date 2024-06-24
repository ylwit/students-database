package pl.wit.studentsdatabase.repository;

import java.util.List;

import pl.wit.studentsdatabase.model.Subject;

/**
 * Interfejs opisujący operacje jakie są możliwe do wykonania na danych przedmiotów dostępnych w magazynie
 * 
 * @author Emil Sell
 * 
 */

public interface SubjectDataAccess {

	List<Subject> getAll();

	Subject  getById(int id);

	void deleteById(int id);

	void add(Subject subject);

	void add(List<Subject> entities);

	void update(Subject subject);

	Subject getByName(String name);

}
