package Service;

import Domain.Student;
import Domain.Teme;
import Repository.StudentRepo;
import Repository.TemeRepo;
import Validator.StudentValidator;
import Validator.TemeValidator;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.theories.internal.Assignments;

import java.io.File;

public class ServiceAssignmentTest {
	private final String directoy = "testFiles";
	private final String assignmentPath = "testFiles/teme.xml";
	private final String mockAssignmentPath = "testFiles/mocks/teme.xml";

	ServiceTeme assignmentService;

	@Before
	public void setUp() throws Exception {
		FileUtils.copyFile(new File(mockAssignmentPath), new File(assignmentPath));
		TemeRepo rep = new TemeRepo(new TemeValidator(), assignmentPath);
		assignmentService = new ServiceTeme(rep);
	}

	@After
	public void tearDown() throws Exception {
		new File(assignmentPath).delete();
	}

	@Test
	public void addValidAssignment() {
		Teme t = new Teme(2, "da-te peste cap", 3, 5);

		assignmentService.add(t);

		Teme t2 = assignmentService.find(t.getID());

		assert (t.equals(t2));

	}

	@Test
	public void addInvalidAssignment() {
		Teme t = new Teme(1, "", -1, -1);
		try {
			assignmentService.add(t);
			assert (false);
		} catch (Exception e) {

		}
	}
}