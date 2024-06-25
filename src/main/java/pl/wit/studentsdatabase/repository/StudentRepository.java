package pl.wit.studentsdatabase.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ExecutorService;

import pl.wit.studentsdatabase.ThreadsConfiguration;
import pl.wit.studentsdatabase.model.Student;

/**
 * Klasa reprezentująca magazyn studentów.
 *
 *
 * @author Yuliia Loianich - całość kodu bez wielowątkowości Tomasz Pogorzelski
 *         Tomasz Pogorzelski - dodanie wielowątkowośco do metod
 * 
 *
 */
public class StudentRepository implements StudentDataAccess {

	/**
	 * Pole przechowujące instancję singletonu
	 */
	private static StudentRepository instance;

	/**
	 * Lista przechowująca wszystkie instancje studentów
	 */
	private List<Student> students;

	/**
	 * Konstruktor prywatny na potrzeby stworzenia singletona
	 */
	private StudentRepository() {
		students = new ArrayList<Student>();
	}

	/**
	 * Metoda mająca na celu stworzyć i/albo zwrócić jedną (jedyną) instancję
	 * obiektu typu StudentRepository
	 *
	 * @return singleton typu StudentRepository.
	 */
	public static StudentRepository getInstance() {
		if (instance == null) {
			instance = new StudentRepository();
		}
		return instance;
	}

	/**
	 * Metoda zwracająca listę wszystkich istniejących studentów
	 *
	 * @return lista wszystkich studentów
	 */
	@Override
	public List<Student> getStudents() {
		return students;
	}

	/**
	 * Metoda zwracająca Studenta na podstawie jego id
	 *
	 * @param studentId identyfikator studenta
	 * @return student pasujący do kryterium
	 */
	@Override
	public Student getStudentById(int studentId) {
		ExecutorService executor = Executors.newFixedThreadPool(ThreadsConfiguration.NUMBER_OF_THREADS);
		List<Callable<Student>> tasks = new ArrayList<>();
		for (Student student : students) {
			tasks.add(() -> {
				if (student.getId() == studentId) {
					return student;
				}
				return null;
			});
		}
		try {
			List<Future<Student>> results = executor.invokeAll(tasks);
			for (Future<Student> result : results) {
				Student student = result.get();
				if (student != null) {
					executor.shutdown();
					return student;
				}
			}
		} catch (InterruptedException | ExecutionException e) {
			((Throwable) e).printStackTrace();
		}
		executor.shutdown();
		return null;
	}

	/**
	 * Metoda zwracająca Studenta na podstawie id jego albumu
	 *
	 * @param albumId numer albumu studenta
	 * @return student pasujący do kryterium
	 */
	@Override
	public Student getStudentByAlbumId(int albumId) {
		ExecutorService executor = Executors.newFixedThreadPool(ThreadsConfiguration.NUMBER_OF_THREADS);
		List<Callable<Student>> tasks = new ArrayList<>();
		for (Student student : students) {
			tasks.add(() -> {
				if (student.getAlbumId() == albumId) {
					return student;
				}
				return null;
			});
		}
		try {
			List<Future<Student>> results = executor.invokeAll(tasks);
			for (Future<Student> result : results) {
				Student student = result.get();
				if (student != null) {
					executor.shutdown();
					return student;
				}
			}
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		executor.shutdown();
		return null;
	}

	/**
	 * Metoda zwracająca listę studentów na podstawie podanego nazwiska
	 *
	 * @param lastName nazwiska studentów
	 * @return lista studentów pasujących do kryterium
	 */
	@Override
	public List<Student> getStudentsByLastName(String lastName) {
		ExecutorService executor = Executors.newFixedThreadPool(ThreadsConfiguration.NUMBER_OF_THREADS);
		List<Callable<Student>> tasks = new ArrayList<>();
		List<Student> resultStudents = new ArrayList<>();

		for (Student student : students) {
			tasks.add(() -> {
				if (student.getLastName().equals(lastName)) {
					return student;
				}
				return null;
			});
		}

		try {
			List<Future<Student>> results = executor.invokeAll(tasks);
			for (Future<Student> result : results) {
				Student student = result.get();
				if (student != null) {
					resultStudents.add(student);
				}
			}
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		executor.shutdown();
		return resultStudents;
	}

	/**
	 * Metoda zwracająca listę studentów na podstawie podanego imienia
	 *
	 * @param firstName imię studentów
	 * @return lista studentów pasujących do kryterium
	 */
	@Override
	public List<Student> getStudentsByFirstName(String firstName) {
		ExecutorService executor = Executors.newFixedThreadPool(ThreadsConfiguration.NUMBER_OF_THREADS);
		List<Callable<Student>> tasks = new ArrayList<>();
		List<Student> resultStudents = new ArrayList<>();

		for (Student student : students) {
			tasks.add(() -> {
				if (student.getFirstName().equals(firstName)) {
					return student;
				}
				return null;
			});
		}

		try {
			List<Future<Student>> results = executor.invokeAll(tasks);
			for (Future<Student> result : results) {
				Student student = result.get();
				if (student != null) {
					resultStudents.add(student);
				}
			}
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		executor.shutdown();
		return resultStudents;
	}

	/**
	 * Metoda dodająca studenta do magazynu studentów
	 *
	 * @param newStudent student do dodania do magazynu studentów
	 */
	@Override
	public void addStudent(Student newStudent) {
		students.add(newStudent);
	}

	/**
	 * Metoda usuwająca studenta z magazynu studentów
	 *
	 * @param studentId identyfikator studenta do usunięcia z magazynu studentów
	 *
	 */
	@Override
	public void deleteStudentById(int studentId) {
		Student tempStudent = getStudentById(studentId);
		students.remove(tempStudent);
	}

	/**
	 * Metoda updatująca dane studenta w magazynie studentów
	 *
	 * @param sourceStudent student, którego dane mają zostać zupdatowane
	 *
	 */
	@Override
	public void updateStudent(Student sourceStudent) {
		Student targetStudent = getStudentById(sourceStudent.getId());

		targetStudent.setFirstName(sourceStudent.getFirstName());
		targetStudent.setLastName(sourceStudent.getLastName());
		targetStudent.setAlbumId(sourceStudent.getAlbumId());
	}

	/**
	 * Metoda dodająca studentów do magazynu studentów
	 *
	 * @param newStudents lista studentów do dodania do magazynu studentów
	 */
	@Override
	public void addStudents(List<Student> newStudents) {
		students.addAll(newStudents);
	}
}