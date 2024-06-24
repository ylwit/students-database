package pl.wit.studentsdatabase.repository;

import java.util.ArrayList;
import java.util.List;

import pl.wit.studentsdatabase.model.Group;

/**
 * 
 * 
 * @author Bartłomiej Perkowski
 * 
 */
public class GroupRepository implements GroupDataAccess{

	// TODO: Operacje wielowątkowe na Listach
	
	/**
     * Pole przechowujące instancję singletonu
     */
	private static GroupRepository instance;
	
	/**
     * Lista przechowująca wszystkie obiekty grup.
     */
	private List<Group> groups;
	
	/**
     * Konstruktor prywatny na potrzeby stworzenia tabeli danych
     */
	private GroupRepository() {
		groups = new ArrayList<Group>();
	}
	
	/**
     * Metoda mająca na celu stworzyć i/albo zwrócić jedną (jedyną) instancję obiektu typu StudentRepository
     * 
     * @return singleton typu GroupRepository.
     */
	public static GroupRepository getInstance() {
		if (instance == null) {
			instance = new GroupRepository();
		}
		return instance;
	}

	/**
     * Metoda zwracająca grupy
     * 
     * @return lista wszystkich studentów
     */
	@Override
	public List<Group> getGroups() {
		return groups;
	}

	/**
     * Metoda zwracająca Grupę na podstawie kodu
     * 
     *  @param code identyfikator grupy
     *  @return Group zidentyfikowany
     */
	@Override
	public Group getGroupByCode(String code) {
		// TODO: chcę stworzyć mechanizm wykonujący te zadanie ale wykorzystując wielowątkowść przy użyciu ilości wątków definiowanych w pl.wit.studentsdatabase.ThreadsConfiguration
		for(Group g : groups) {
			if (g.getGroupCode() == code)
				return g;
		}
		return null;
	}
	
	/**
     * Pobranie grupy za pomocą id
     * 
     * @param groupId
     * @return grupa o danym id
     */
	
	@Override
	public Group getGroupById(int groupId) {
		// TODO: mechanizm wykonujący te zadanie ale wykorzystując wielowątkowść przy użyciu ilości wątków definiowanych w pl.wit.studentsdatabase.ThreadsConfiguration
		for(Group g : groups) {
			if (g.getId() == groupId)
				return g;
		}
		return null;
	}

	/**
     * Metoda zwracająca listę studentów na podstawie podanego nazwiska
     * 
     * @param kind - typ specjalizacji
     * @return lista znalezionych grup
     */
	@Override
	public List<Group> getGroupBySpecialisation(String kind) {
		// TODO: mechanizm wykonujący te zadanie ale wykorzystując wielowątkowść przy użyciu ilości wątków definiowanych w pl.wit.studentsdatabase.ThreadsConfiguration
		List<Group> resultStudentList = new ArrayList<>();
		for(Group g : groups) {
			if (g.getSpecialization() == kind)
				resultStudentList.add(g);
		}
		return null;
	}

	/**
     * Metoda zwracająca grupy przy pomocy studenta
     * 
     * @param id studenta
     * @return lista pasujących grup
     */
	@Override
	public List<Group> getGroupsByStudentId(int studentId) {
		// TODO: mechanizm wykonujący te zadanie ale wykorzystując wielowątkowść przy użyciu ilości wątków definiowanych w pl.wit.studentsdatabase.ThreadsConfiguration
		List<Group> foundGroups = new ArrayList<>();
		for(Group g : groups) {
			for (int id : g.getStudents()) {
				if (id == studentId)
					foundGroups.add(getGroupById(id));
				
			}
		}
		return foundGroups;
	}
	/**
	 * Metoda szukająca grup przy pomocy opisu
	 * 
	 * @param String opis
	 * @return lista pasujących grup
	 */
	@Override
	public List<Group> getGroupsByDescription(String description) {
		// TODO: mechanizm wykonujący te zadanie ale wykorzystując wielowątkowść przy użyciu ilości wątków definiowanych w pl.wit.studentsdatabase.ThreadsConfiguration
		List<Group> foundGroups = new ArrayList<>();
		for(Group g : groups) {
			if (g.getDescription().contains(description))
				foundGroups.add(g);
		}
		return foundGroups;
	}

	/**
     * Metoda dodająca grupe do tabeli
     * 
     * @param newGroup nowa grupa
     */
	@Override
	public void addGroup(Group newGroup) {
		groups.add(newGroup);	
	}

	/**
     * Metoda usuwająca studenta z magazynu studentów
     * 
     * @param studentId identyfikator studenta do usunięcia z magazynu studentów
     * 
     */
	@Override
	public void deleteById(int id) {
		Group g = getGroupById(id);
		groups.remove(g);
	}

	/**
     * Metoda update danych grupy
     * 
     * @param targetGroup - grupa która ma zostać zaktualizowana
     * 
     */
	@Override
	public void update(Group group) {
		Group targetGroup = getGroupById(group.getId());
		
		targetGroup.setDescription(group.getDescription());
		targetGroup.setGroupCode(group.getGroupCode());
		targetGroup.setSpecialization(group.getSpecialization());
		targetGroup.setStudents(group.getStudents());
	}

	/**
     * Metoda dodająca listę wiele grup jednocześnie
     * 
     * @param newGroups lista grup
     */
	
	@Override
	public void addGroups(List<Group> newGroups) {
		groups.addAll(newGroups);
	}
}
