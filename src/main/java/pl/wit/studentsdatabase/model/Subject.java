package pl.wit.studentsdatabase.model;

import java.util.Arrays;

import pl.wit.studentsdatabase.repository.ObjectsIds;

/**
 * Klasa reprezentująca przedmiot nauczania. 
 * 
 * 
 * @author Yuliia Loianich
 * 
 */
public class Subject {

    /**
     * Identyfikator przedmiotu
     */
	private int id;
	
	/**
     * Nazwa przedmiotu
     */
	private String name;
	
	/**
     * Maksymalna liczba możliwych do uzyskania punktów z przedmiotu
     */
	private int maxPoints;
	
	/**
     * Tablica, która powinna zawierać trzy elementy określające minimalną liczbę punktów do uzyskania konkretnej oceny. 
     * Odpowiednio, element z indeksu o numerze:
     * 0 - minimalna liczba punktów aby uzyskać ocenę 3
     * 1 - minimalna liczba punktów aby uzyskać ocenę 4
     * 2 - minimalna liczba punktów aby uzyskać ocenę 5
     */
	private int[] gradingCriteria;

	/**
     * Konstruktor używany podczas tworzenia nowego obiektu
     * 
     * @param name Nazwa przedmiotu
     * @param maxPoints Maksymalna liczba możliwych do uzyskania punktów z przedmiotów
     * @param gradingCriteria Tablica, która powinna zawierać trzy elementy określające minimalną liczbę punktów do uzyskania konkretnej oceny. 
     */
	public Subject(String name, int maxPoints, int[] gradingCriteria) {
		super();
		
		// sprawdzam czy wartości podane w polach są ok, jeśli nie to rzucam wyjątek
		if(!areGradingCriteriaValid(maxPoints,gradingCriteria))
			throw new IllegalArgumentException("Dane dotyczące punktacji i ocen są niepoprawne");
		
		// stworzenie i pobranie kolejnego id studenta
		ObjectsIds.getInstance().iterateLastUsedSubjectId();
		this.id = ObjectsIds.getInstance().getLastUsedSubjectId();
		
		this.name = name;
		this.maxPoints = maxPoints;
		this.gradingCriteria = gradingCriteria;
	}
	
	/**
     * Konstruktor używany podczas tworzenia obiektu na podstawie danych z pliku
     * 
     * @param id Identyfikator przedmiotu
     * @param name Nazwa przedmiotu
     * @param maxPoints Maksymalna liczba możliwych do uzyskania punktów z przedmiotów
     * @param gradingCriteria Tablica, która powinna zawierać trzy elementy określające minimalną liczbę punktów do uzyskania konkretnej oceny. 
     */
	public Subject(int id, String name, int maxPoints, int[] gradingCriteria) {
		super();
		
		// sprawdzam czy wartości podane w polach są ok, jeśli nie to rzucam wyjątek
		if(!areGradingCriteriaValid(maxPoints,gradingCriteria))
			throw new IllegalArgumentException("Dane dotyczące punktacji i ocen są niepoprawne");
		
		this.id = id;
		this.name = name;
		this.maxPoints = maxPoints;
		this.gradingCriteria = gradingCriteria;
	}
	
	/**
     * Metoda walidująca poprawność danych określających skalę punktową dla ocen z przedmiotu i maksymalną wartość punktów
     * @param maxPoints Maksymalna liczba możliwych do uzyskania punktów z przedmiotów
     * @param gradingCriteria Tablica, która powinna zawierać trzy elementy określające minimalną liczbę punktów do uzyskania konkretnej oceny. 
     */
	private boolean areGradingCriteriaValid(int maxPoints, int[] gradingCriteria) {
		
		// maxPoints nie może mieć wartości mniejszej niż 1
		if (maxPoints < 1) {
			return false;
		}
		
		// tablica gradingCriteria musi mieć 3 elementy
		 if (gradingCriteria.length != 3) {
			return false;
		}
		 
		// pierwszy element tablicy gradingCriteria nie może być mniejszy równy od 0 i nie może być większy równy niż drugi element gradingCriteria
		if (gradingCriteria[0]<=0 || gradingCriteria[0] >= gradingCriteria[1]) {
			return false;
		}
		
		// drugi element tablicy gradingCriteria nie może być mniejszy równy od 3ciego elementu grading criteria 
		if (gradingCriteria[1] >= gradingCriteria[2]) {
			return false;
		}
		
		// trzeci element tablicy gradingCriteria nie może być mniejszy równy od 3ciego elementu grading criteria 
		if (gradingCriteria[2]>=maxPoints) {
			return false;
		}
		
		// w pozostałych wypadkach wstawione wartości są ok
		return true;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMaxPoints() {
		return maxPoints;
	}

	public void setMaxPoints(int maxPoints) {
		this.maxPoints = maxPoints;
	}

	public int[] getGradingCriteria() {
		return gradingCriteria;
	}

	public void setGradingCriteria(int[] gradingCriteria) {
		this.gradingCriteria = gradingCriteria;
	}
	
	/**
     * Metoda obliczająca ocenę na podstawie punktów z danego przedmiotu
     * 
     * @param pointsValue Ilość uzyskanych punktów
     * @return Uzyskana ocena
     */
	public int getMarkBasingOnNumberOfPoints(int pointsValue) {
		if(pointsValue >= gradingCriteria[2]) {
			return 5;
		} else if(pointsValue >= gradingCriteria[1]) {
			return 4;
		} else if(pointsValue >= gradingCriteria[0]) {
			return 3;
		} else {
			return 2;
		}
	}

	@Override
	public String toString() {
		return "Subject [id=" + id + ", name=" + name + ", maxPoints=" + maxPoints + ", gradingCriteria="
				+ Arrays.toString(gradingCriteria) + "]";
	}
	
}
