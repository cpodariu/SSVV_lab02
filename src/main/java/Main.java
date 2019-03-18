
import Repository.NoteRepo;
import Repository.StudentRepo;
import Repository.TemeRepo;
import Service.ServiceNote;
import Service.ServiceStudent;
import Service.ServiceTeme;
import UI.UI;
import Validator.NotaValidator;
import Validator.StudentValidator;
import Validator.TemeValidator;

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		StudentRepo rep = new StudentRepo(new StudentValidator(), "C:\\Users\\podar\\Desktop\\LabAssiAsseProjectV04\\LabAssiAsseProjectV04\\src\\studenti.xml");
		TemeRepo repo = new TemeRepo(new TemeValidator(), "C:\\Users\\podar\\Desktop\\LabAssiAsseProjectV04\\LabAssiAsseProjectV04\\src\\teme.xml");
		NoteRepo r = new NoteRepo(new NotaValidator());
		ServiceStudent srv = new ServiceStudent(rep);
		ServiceTeme serv = new ServiceTeme(repo);
		ServiceNote sv = new ServiceNote(r);
		UI ui = new UI(srv, serv, sv);
		ui.show();

	}
}
