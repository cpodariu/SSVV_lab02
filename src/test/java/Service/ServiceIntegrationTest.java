package Service;

import Domain.Nota;
import Domain.Student;
import Domain.Teme;
import Repository.NoteRepo;
import Repository.StudentRepo;
import Repository.TemeRepo;
import Validator.NotaValidator;
import Validator.StudentValidator;
import Validator.TemeValidator;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.*;

public class ServiceIntegrationTest {
	private final String directoy = "testFiles";
	private final String assignmentPath = "testFiles/teme.xml";
	private final String mockAssignmentPath = "testFiles/mocks/teme.xml";
	ServiceTeme assignmentService;

	private final String studentPath = "testFiles/students.xml";
	private final String mockStudentPath = "testFiles/mocks/students.xml";
	ServiceStudent studentService;

	ServiceNote gradeService;

	@Before
	public void setUpStudent() throws Exception {
		FileUtils.copyFile(new File(mockStudentPath), new File(studentPath));
		StudentRepo rep = new StudentRepo(new StudentValidator(), studentPath);
		studentService = new ServiceStudent(rep);
	}

	@Before
	public void setUpAssignment() throws Exception {
		FileUtils.copyFile(new File(mockAssignmentPath), new File(assignmentPath));
		TemeRepo rep = new TemeRepo(new TemeValidator(), assignmentPath);
		assignmentService = new ServiceTeme(rep);
	}

	@Before
	public void setUpGrade() throws Exception {
		NoteRepo gradeRepo = new NoteRepo(new NotaValidator());
		gradeService = new ServiceNote(gradeRepo);
	}

	@Test
	public void addValidStudent() {
		Student s = new Student("2", "Catalin", 936, "catalin@mail.com", "Dorinel");

		studentService.add(s);

		Student s2 = studentService.find(s.getID());

		assert (s.equals(s2));
	}

	@Test
	public void addValidAssignment() {
		Teme t = new Teme(2, "asdsadas", 3, 5);

		assignmentService.add(t);

		Teme t2 = assignmentService.find(t.getID());

		assert (t.equals(t2));

	}


	public static <T> Collection<T> iterableToCollection(Iterable<T> iterable) {
		Collection<T> collection = new ArrayList<T>();
		for (T e : iterable) {
			collection.add(e);
		}
		return collection;
	}

	@Test
	public void addValidGrade() {
		Nota n = new Nota(new AbstractMap.SimpleEntry<String, Integer>("1", 1),
				studentService.find("1"),
				iterableToCollection(assignmentService.all()).toArray(new Teme[100])[0],
				9, 7);

		gradeService.add(n, "mai invata");
	}

	@Test
	public void runAllIntegrationTests() {
		Student s = new Student("2", "Catalin", 936, "catalin@mail.com", "Dorinel");

		studentService.add(s);

		Student s2 = studentService.find(s.getID());

		assert (s.equals(s2));

		Teme t = new Teme(2, "asdsadas", 3, 5);

		assignmentService.add(t);

		Teme t2 = assignmentService.find(t.getID());

		assert (t.equals(t2));

		Nota n = new Nota(new AbstractMap.SimpleEntry<String, Integer>("1", 1),
				studentService.find("1"),
				iterableToCollection(assignmentService.all()).toArray(new Teme[100])[0],
				9, 7);

		gradeService.add(n, "mai invata");

	}

	@Test
	public void addStudentAndAssignment() {
		Student s = new Student("2", "Catalin", 936, "catalin@mail.com", "Dorinel");

		studentService.add(s);

		Student s2 = studentService.find(s.getID());

		assert (s.equals(s2));

		Teme t = new Teme(2, "asdsadas", 3, 5);

		assignmentService.add(t);

		Teme t2 = assignmentService.find(t.getID());

		assert (t.equals(t2));
	}

	@Test
	public void addStudentAndAssignmentAndGrade() {
		Student s = new Student("2", "Catalin", 936, "catalin@mail.com", "Dorinel");

		studentService.add(s);

		Student s2 = studentService.find(s.getID());

		assert (s.equals(s2));

		Teme t = new Teme(2, "asdsadas", 3, 5);

		assignmentService.add(t);

		Teme t2 = assignmentService.find(t.getID());

		assert (t.equals(t2));

		Nota n = new Nota(new AbstractMap.SimpleEntry<String, Integer>("1", 1),
				studentService.find("1"),
				iterableToCollection(assignmentService.all()).toArray(new Teme[100])[0],
				9, 7);

		gradeService.add(n, "mai invata");
	}
}
