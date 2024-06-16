package pl.wit.studentsdatabase.model;

import java.util.List;

import pl.wit.studentsdatabase.repository.ObjectsIds;

/**
 * Klasa reprezentująca grupę.
 * 
 * 
 * @author Yuliia Loianich
 * 
 */
public class Group {
	
    /**
     * Identyfikator grupy
     */
	private int id;
	
    /**
     * Kod grupy
     */
	private String groupCode;
	
	/**
     * Specjalizacja grupy
     */
	private String specialization;
	
	/**
     * Opis grupy
     */
	private String description;
	
	/**
     * Lista identyfikatorów studentów należących do grupy
     */
	private List<Integer> students;

	/**
     * Konstruktor używany podczas tworzenia nowego obiektu
     * 
     * @param groupCode Kod grupy
     * @param specialization Specjalizacja grupy
     * @param description Opis grupy
     * @param students Lista identyfikatorów studentów należących do grupy
     */
	public Group(String groupCode, String specialization, String description, List<Integer> students) {
		super();
		
		// stworzenie i pobranie kolejnego id studenta
		ObjectsIds.getInstance().iterateLastUsedGroupId();
		this.id = ObjectsIds.getInstance().getLastUsedGroupId();
		
		this.groupCode = groupCode;
		this.specialization = specialization;
		this.description = description;
		this.students = students;
	}
	
	/**
     * Konstruktor używany podczas tworzenia obiektu na podstawie danych z pliku
     * 
     * @param id Identyfikator grupy
     * @param groupCode Kod grupy
     * @param specialization Specjalizacja grupy
     * @param description Opis grupy
     * @param students Lista identyfikatorów studentów należących do grupy
     * 
     */
	public Group(int id, String groupCode, String specialization, String description, List<Integer> students) {
		super();
		this.id = id;
		this.groupCode = groupCode;
		this.specialization = specialization;
		this.description = description;
		this.students = students;
	}

	public int getId() {
		return id;
	}

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Integer> getStudents() {
		return students;
	}

	public void setStudents(List<Integer> students) {
		this.students = students;
	}

	@Override
	public String toString() {
		return "Group [id=" + id + ", groupCode=" + groupCode + ", specialization=" + specialization + ", description="
				+ description + ", students=" + students + "]";
	}	

}
