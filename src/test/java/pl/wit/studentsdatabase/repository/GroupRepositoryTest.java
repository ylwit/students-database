package pl.wit.studentsdatabase.repository;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import pl.wit.studentsdatabase.model.Group;

/**
 * 
 * 
 * @author Bartłomiej Perkowski
 * 
 */
public class GroupRepositoryTest {
	
	GroupRepository repo = GroupRepository.getInstance();
	/*
	 * Test konstruktora
	 */
	@Test
	void constructorTest() {					
		assertTrue(repo instanceof GroupRepository);
		//assertFalse(repo.getStudentByAlbumId(12340).getFirstName().isEmpty());
	}
	/*
	 * Test czy grupa została poprawnie dodana do repozytorium
	 */
	@Test
	void newGroupAddedTest() {
		List<Integer> studentsId = Arrays.asList(1, 2);

		repo.addGroup(new Group(1,"1234", "IO","opis", studentsId));
		
		assertFalse(repo.getGroupById(1) == null);
		
	}
	/*
	 * Metoda testująca czy grupa zostanie dobrze usunięta z repozytorum
	 */
	@Test
	void grouptDeletedSuccessfullyTest() {
		
		List<Integer> studentsId = Arrays.asList(1, 2);

		repo.addGroup(new Group(122,"1234", "IO","opis", studentsId));
		repo.addGroup(new Group(2,"7489", "PD","opis2", studentsId));
		
		repo.deleteById(122);
		assertFalse(repo.getGroups().contains(repo.getGroupById(122)));
	}
	/*
	 * Metoda testująca czy dane zostaną w prawidłowy sposób zaktualizowany
	 * po ich modyfikacji
	 */	
	@Test
	void groupUpdatedSuccessfullyTest() {
		
		List<Integer> studentsId = Arrays.asList(1, 2);
		repo.addGroup(new Group(1,"1234", "IO","opis", studentsId));
		repo.addGroup(new Group(2,"7489", "PD","opis2", studentsId));
		
		repo.update(new Group(1,"2212", "HH", "akkk", studentsId));
		assertTrue(repo.getGroupById(1).getSpecialization().equals("HH"));
	}
	/*
	 * Metoda sprawdzająca czy student jest poprawnie pobierany z grupy.
	 * Szukanie grup na podstawie studenta.
	 */
	@Test
	void getGroupsByStudentIdTest() {
		
		List<Integer> studentsId = Arrays.asList(1, 2);
		List<Integer> studentsId2 = Arrays.asList(3, 4);
		repo.addGroup(new Group(2,"1234", "IO","opis", studentsId));
		repo.addGroup(new Group(1,"1000", "DD","opis", studentsId));
		repo.addGroup(new Group(3,"7489", "PD","opis2", studentsId2));

		assertFalse(repo.getGroupsByStudentId(1).contains(repo.getGroupById(3)));
	}
	
	

}
