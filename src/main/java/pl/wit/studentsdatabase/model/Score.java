package pl.wit.studentsdatabase.model;

import pl.wit.studentsdatabase.repository.ObjectsIds;

/**
 * Klasa reprezentująca wynik danego studenta z danego przedmiotu.
 * 
 * 
 * @author Yuliia Loianich
 * 
 */
public class Score {
	
    /**
     * Identyfikator wyniku
     */
	private int id;
	
	/**
     * Identyfikator studenta
     */
	private int studentId;
	
	/**
     * Identyfikator przedmiotu
     */	
	private int subjectId;
	
	/**
     * Ilość uzyskanych punktów
     */
	private int scoredPoints;

	/**
     * Konstruktor używany podczas tworzenia nowego obiektu
     * 
     * @param studentId Identyfikator studenta
     * @param subjectId Identyfikator przedmiotu
     * @param scoredPoints Ilość uzyskanych punktów
     */
	public Score(int studentId, int subjectId, int scoredPoints) {
		super();
		
		// stworzenie i pobranie kolejnego id studenta
		ObjectsIds.getInstance().iterateLastUsedScoreId();
		this.id = ObjectsIds.getInstance().getLastUsedScoreId();
		
		this.studentId = studentId;
		this.subjectId = subjectId;
		this.scoredPoints = scoredPoints;
	}

	/**
     * Konstruktor używany podczas tworzenia obiektu na podstawie danych z pliku
     * 
     * @param id Identyfikator wyniku
     * @param studentId Identyfikator studenta
     * @param subjectId Identyfikator przedmiotu
     * @param scoredPoints Ilość uzyskanych punktów
     */
	public Score(int id, int studentId, int subjectId, int scoredPoints) {
		super();
		this.id = id;
		this.studentId = studentId;
		this.subjectId = subjectId;
		this.scoredPoints = scoredPoints;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	public int getScoredPoints() {
		return scoredPoints;
	}

	public void setScoredPoints(int scoredPoints) {
		this.scoredPoints = scoredPoints;
	}

	@Override
	public String toString() {
		return "Score [id=" + id + ", studentId=" + studentId + ", subjectId=" + subjectId + ", scoredPoints="
				+ scoredPoints + "]";
	}

}
