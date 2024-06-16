package pl.wit.studentsdatabase.repository;


/**
 * Klasa typu singleton reprezentująca magazyn zawierający indeksy poszczególnych obiektów.
 * 
 * 
 * @author Yuliia Loianich
 * 
 */
public class ObjectsIds {
	
	/**
     * Pole przechowujące instancję singletonu
     */
	private static ObjectsIds instance;
	
    /**
     * Ostatnio wygenerowany identyfikator studenta
     */
	private int lastUsedStudentId = 0;
	
    /**
     * Ostatnio wygenerowany identyfikator grupy
     */
	private int lastUsedGroupId = 0;
	
    /**
     * Ostatnio wygenerowany identyfikator przedmiotu nauczania
     */
	private int lastUsedSubjectId = 0;
	
    /**
     * Ostatnio wygenerowany identyfikator wyniku studenta z danego przedmiotu nauczania
     */
	private int lastUsedScoreId = 0;

	/**
     * Konstruktor prywatny na potrzeby stworzenia singletona
     */
	private ObjectsIds() {
	}
	
	/**
     * Metoda mająca na celu stworzyć i/albo zwrócić jedną jedyną instancję obiektu typu ObjectIds
     * 
     * @return singleton typu ObjectIds.
     */
	public static ObjectsIds getInstance() {
		if (instance == null) {
			instance = new ObjectsIds();
		}
		return instance;
	}

	public int getLastUsedStudentId() {
		return lastUsedStudentId;
	}

	public void setLastUsedStudentId(int lastUsedStudentId) {
		this.lastUsedStudentId = lastUsedStudentId;
	}

	public int getLastUsedGroupId() {
		return lastUsedGroupId;
	}

	public void setLastUsedGroupId(int lastUsedGroupId) {
		this.lastUsedGroupId = lastUsedGroupId;
	}

	public int getLastUsedSubjectId() {
		return lastUsedSubjectId;
	}

	public void setLastUsedSubjectId(int lastUsedSubjectId) {
		this.lastUsedSubjectId = lastUsedSubjectId;
	}

	public int getLastUsedScoreId() {
		return lastUsedScoreId;
	}

	public void setLastUsedScoreId(int lastUsedScoreId) {
		this.lastUsedScoreId = lastUsedScoreId;
	}
	
	/**
     * Inkrementacja identyfikatora studenta
     * 
     */
	public void iterateLastUsedStudentId() {
		lastUsedStudentId++;
	}
	
	/**
     * Inkrementacja identyfikatora grupy studenckiej
     * 
     */
	public void iterateLastUsedGroupId() {
		lastUsedGroupId++;
	}
	
	/**
     * Inkrementacja identyfikatora przedmiotu
     * 
     */
	public void iterateLastUsedSubjectId() {
		lastUsedSubjectId++;
	}
	
	/**
     * Inkrementacja identyfikatora wyniku studenta z danego przedmiotu nauczania
     * 
     */
	public void iterateLastUsedScoreId() {
		lastUsedScoreId++;
	}

}
