package pl.wit.studentsdatabase.model;

import pl.wit.studentsdatabase.repository.ObjectsIds;


/**
 * Klasa reprezentująca studenta.
 * 
 * 
 * @author Yuliia Loianich
 * 
 */
public class Student {
	
    /**
     * Identyfikator studenta
     */
	private int id;
	
    /**
     * Imię studenta
     */
	private String firstName;
	
    /**
     * Nazwisko studenta
     */
	private String lastName;
	
    /**
     * Numer albumu studenta
     */
	private int albumId;

	/**
     * Konstruktor używany podczas tworzenia nowego obiektu
     * 
     * @param firstName Imie studenta
     * @param lastName Nazwisko studenta
     * @param albumId Numer albumu studenta
     */
	public Student(String firstName, String lastName, int albumId) {		
		super();
		
		// stworzenie i pobranie kolejnego id studenta
		ObjectsIds.getInstance().iterateLastUsedStudentId();
		this.id = ObjectsIds.getInstance().getLastUsedStudentId();
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.albumId = albumId;
	}

	/**
     * Konstruktor używany podczas tworzenia obiektu na podstawie danych z pliku
     * 
     * @param id Identyfikator studenta
     * @param firstName Imie studenta
     * @param lastName Nazwisko studenta
     * @param albumId Numer albumu studenta
     * 
     */
	public Student(int id, String firstName, String lastName, int albumId) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.albumId = albumId;
	}

	public int getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAlbumId() {
		return albumId;
	}

	public void setAlbumId(int albumId) {
		this.albumId = albumId;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", albumId=" + albumId
				+ "]";
	}
}
