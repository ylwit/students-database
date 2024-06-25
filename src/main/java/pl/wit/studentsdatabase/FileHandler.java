package pl.wit.studentsdatabase;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import pl.wit.studentsdatabase.model.Group;
import pl.wit.studentsdatabase.model.Score;
import pl.wit.studentsdatabase.model.Student;
import pl.wit.studentsdatabase.model.Subject;

/**
 * 
 * Klasa obsługująca operacje i/o dla repozytoriów
 * 
 * @author Emil Sell
 * 
 */

public class FileHandler<T> {

	// scieżka do pliku
	private String fileName;

	public FileHandler(String fileName) {
		this.fileName = fileName;
	}

	// odczyt całego pliku
	public List<?> readAll() {
		try (DataInputStream stream = new DataInputStream(new FileInputStream(fileName))) {
			String type = stream.readUTF();
			switch (type) {
			case "STUDENT": {
				List<Student> students = new ArrayList<>();
				while (stream.available() > 0) {
					Student student = readStudent(stream);
					if (student != null)
						students.add(student);
				}
				return students;
			}
			case "GROUP": {
				List<Group> groups = new ArrayList<>();
				while (stream.available() > 0) {
					Group group = readGroup(stream);
					if (group != null)
						groups.add(group);
				}
				return groups;
			}
			case "SUBJECT": {
				List<Subject> subjects = new ArrayList<>();
				while (stream.available() > 0) {
					Subject subject = readSubject(stream);
					if (subject != null)
						subjects.add(subject);
				}
				return subjects;
			}
			case "SCORE": {
				List<Score> scores = new ArrayList<>();
				while (stream.available() > 0) {
					Score score = readScore(stream);
					if (score != null)
						scores.add(score);
				}
				return scores;
			}
			default:
				return null;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	// nadpisanie pliku
	public void overwrite(List<T> entities) {
		try (DataOutputStream stream = new DataOutputStream(new FileOutputStream(fileName))) {
			if (entities.get(0).getClass() == Student.class) {
				stream.writeUTF("STUDENT");
			} else if (entities.get(0).getClass() == Group.class) {
				stream.writeUTF("GROUP");
			} else if (entities.get(0).getClass() == Subject.class) {
				stream.writeUTF("SUBJECT");
			} else if (entities.get(0).getClass() == Score.class) {
				stream.writeUTF("SCORE");
			} else {
				throw new IllegalArgumentException("");
			}
			write(entities, stream);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private Student readStudent(DataInputStream stream) {
		try {
			int id = stream.readInt();
			String firstName = stream.readUTF();
			String lastName = stream.readUTF();
			int albumId = stream.readInt();
			return new Student(id, firstName, lastName, albumId);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private Group readGroup(DataInputStream stream) {
		try {
			int id = stream.readInt();
			String groupCode = stream.readUTF();
			String specialization = stream.readUTF();
			String description = stream.readUTF();
			List<Integer> students = new ArrayList<>();
			int size = stream.readInt();
			for (int i = 0; i < size; ++i)
				students.add(stream.readInt());
			return new Group(id, groupCode, specialization, description, students);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private Subject readSubject(DataInputStream stream) {
		try {
			int id = stream.readInt();
			String name = stream.readUTF();
			int maxPoints = stream.readInt();
			int[] gradingCriteria = new int[3];
			for (int i = 0; i < 3; ++i)
				gradingCriteria[i] = (stream.readInt());
			return new Subject(id, name, maxPoints, gradingCriteria);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private Score readScore(DataInputStream stream) {
		try {
			int id = stream.readInt();
			int studentId = stream.readInt();
			int subjectId = stream.readInt();
			int scoredPoints = stream.readInt();
			return new Score(id, studentId, subjectId, scoredPoints);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private void write(List<T> entities, DataOutputStream stream) {
		if (entities.get(0).getClass() == Student.class) {
			for (T entity : entities)
				writeStudent((Student) entity, stream);
		} else if (entities.get(0).getClass() == Group.class) {
			for (T entity : entities)
				writeGroup((Group) entity, stream);
		} else if (entities.get(0).getClass() == Subject.class) {
			for (T entity : entities)
				writeSubject((Subject) entity, stream);
		} else if (entities.get(0).getClass() == Score.class) {
			for (T entity : entities)
				writeScore((Score) entity, stream);
		} else {
			throw new IllegalArgumentException("");
		}
	}

	private void writeStudent(Student student, DataOutputStream stream) {
		try {
			stream.writeInt(student.getId());
			stream.writeUTF(student.getFirstName());
			stream.writeUTF(student.getLastName());
			stream.writeInt(student.getAlbumId());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void writeGroup(Group group, DataOutputStream stream) {
		try {
			stream.writeInt(group.getId());
			stream.writeUTF(group.getGroupCode());
			stream.writeUTF(group.getSpecialization());
			stream.writeUTF(group.getDescription());
			List<Integer> students = group.getStudents();
			stream.writeInt(students.size());
			for (int studentId : students)
				stream.writeInt(studentId);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void writeSubject(Subject subject, DataOutputStream stream) {
		try {
			stream.writeInt(subject.getId());
			stream.writeUTF(subject.getName());
			stream.writeInt(subject.getMaxPoints());
			int[] gradingCriteria = subject.getGradingCriteria();
			for (int c : gradingCriteria)
				stream.writeInt(c);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void writeScore(Score score, DataOutputStream stream) {
		try {
			stream.writeInt(score.getId());
			stream.writeInt(score.getStudentId());
			stream.writeInt(score.getSubjectId());
			stream.writeInt(score.getScoredPoints());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
