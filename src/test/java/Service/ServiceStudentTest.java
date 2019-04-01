package Service;

import Domain.Student;
import Repository.StudentRepo;
import Validator.StudentValidator;
import Validator.ValidationException;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class ServiceStudentTest {
	private final String directoy = "testFiles";
	private final String studentPath = "testFiles/students.xml";
	private final String mockStudentPath = "testFiles/mocks/students.xml";

	ServiceStudent studentService;

	@Before
	public void setUp() throws Exception {
		FileUtils.copyFile(new File(mockStudentPath), new File(studentPath));
		StudentRepo rep = new StudentRepo(new StudentValidator(), studentPath);
		studentService = new ServiceStudent(rep);
	}

	@After
	public void tearDown() throws Exception {
		new File(studentPath).delete();
	}

	@Test
	public void addValidStudent() {
		Student s = new Student("2", "Catalin", 936, "catalin@mail.com", "Dorinel");

		studentService.add(s);

		Student s2 = studentService.find(s.getID());

		assert (s.equals(s2));
	}

	@Test
	public void addInvalidDuplicateIdStudentTest() {
		Student s = new Student("1", "Catalin", 936, "catalin@mail.com", "Dorinel");
		studentService.add(s);
		assert (!studentService.find("1").getNume().equals(s.getNume()));
	}

	@Test
	public void addInvalidIdStudentTest() {
		Student s = new Student(null, "Catalin", 936, "catalin@mail.com", "Dorinel");
		try {
			studentService.add(s);
			assert (false);
		} catch (ValidationException ignored) {
		}
	}

	@Test
	public void addInvalidGroupStudentTest() {
		Student s = new Student("2", "Catalin", -1, "catalin@mail.com", "Dorinel");
		try {
			studentService.add(s);
			assert (false);
		} catch (ValidationException ignored) {
		}
	}

	@Test
	public void addInvalidEmailStudentTest() {
		Student s = new Student("2", "Catalin", 936, "catalin.com", "Dorinel");
		try {
			studentService.add(s);
			assert (false);
		} catch (ValidationException ignored) {
		}
	}

	@Test
	public void addInvalidNameStudentTest() {
		Student s = new Student("2", "][][][][", 936, "catalin@abc.com", "Dorinel");
		try {
			studentService.add(s);
			assert (false);
		} catch (ValidationException ignored) {
		}
	}

	@Test
	public void addInvalidProfNameStudentTest() {
		Student s = new Student("2", "Catalin", 936, "catalin@abc.com", "[]][][][]");
		try {
			studentService.add(s);
			assert (false);
		} catch (ValidationException ignored) {
		}
	}
}