package Service;

import java.util.ArrayList;
import java.util.Scanner;

import entity.Absenta;
import entity.Nota;
import entity.Profesor;
import entity.Student;

public class ProfesorService {
	private Scanner userInput = new Scanner(System.in);
	private Profesor currentProfessor = (Profesor) Login.currentUser;
	private ArrayList<Student> eleviiDvs;

	public ProfesorService() {
		this.eleviiDvs = new ArrayList<Student>();
		for (int i = 0; i < eCatalogMain.currentDatabase.getStudentsList().size(); i++) {
			for (int j = 0; j < eCatalogMain.currentDatabase.getStudentsList().get(i).getMaterii().size(); j++) {
				if (currentProfessor.getMaterie().equalsIgnoreCase(
						eCatalogMain.currentDatabase.getStudentsList().get(i).getMaterii().get(j).getNume())) {
					eleviiDvs.add(eCatalogMain.currentDatabase.getStudentsList().get(i));
				}
			}
		}
	}

	public void afiseazaCatalog() {
		System.out.println("eCatalog");

		for (Student st : eCatalogMain.currentDatabase.getStudentsList()) {
			System.out.println(st.getNume().toUpperCase());
		}

	}

	public void adaugaNota() {
		System.out.println("Acestia sunt elevii de la cursul dumneavoasta: ");
		for (Student st : eleviiDvs) {
			System.out.println(st);
		}
		System.out.println("Carui elevi doriti sa-i adaugati note?");
		String numeElev = userInput.nextLine();
		for (int i = 0; i < eleviiDvs.size(); i++) {
			if (numeElev.equalsIgnoreCase(eleviiDvs.get(i).getNume())) {
				Student student = eleviiDvs.get(i);
				System.out.println("Ce nota doriti sa adaugati? ");
				int nota = userInput.nextInt();
				int indexDeMaterie = 0;
				for (int j = 0; j < student.getMaterii().size(); j++) {
					if (currentProfessor.getMaterie().equalsIgnoreCase(student.getMaterii().get(j).getNume())) {
						indexDeMaterie = j;
					}
				}
				// TODO get actual date
				student.getMaterii().get(indexDeMaterie).getNote().add(new Nota(nota, "today"));
				return;
			}
		}
		System.out.println("Numele tastat nu este valid.");

	}
	
	private void afiseazaAbsente(Student student, int indexDeMaterie){
		for (int x = 0; x < student.getMaterii().get(indexDeMaterie).getAbsente().size(); x++) {
			if (student.getMaterii().get(indexDeMaterie).getAbsente().get(x).isAbsent()) {
				System.out.printf("%s ", student.getMaterii().get(indexDeMaterie).getAbsente().get(x));
			}
		}
	}

	public void adaugaStudent() {
		System.out.println("Tasteaza numele si prenumele elevului.");
		String numeElev = userInput.nextLine();
		System.out.println("Tasteaza un username pentru elev.");
		String userName = userInput.next();
		System.out.println("Tasteaza o parola initiala.");
		String parola = userInput.next();
		eCatalogMain.currentDatabase.getUseri().add(new Student(numeElev, userName, parola));
	}

	public void motiveazaAbsenta() {
		System.out.println("Acestia sunt elevii de la cursul dumneavoasta: ");
		for (Student st : eleviiDvs) {
			System.out.println(st);
		}
		System.out.println("Carui elevi doriti sa-i motivati absentele?");
		String numeElev = userInput.nextLine();
		for (int i = 0; i < eleviiDvs.size(); i++) {
			if (numeElev.equalsIgnoreCase(eleviiDvs.get(i).getNume())) {
				Student student = eleviiDvs.get(i);
				int indexDeMaterie = 0;
				for (int j = 0; j < student.getMaterii().size(); j++) {
					if (currentProfessor.getMaterie().equalsIgnoreCase(student.getMaterii().get(j).getNume())) {
						indexDeMaterie = j;
					}
				}
				afiseazaAbsente(student, indexDeMaterie);
				System.out.println();
				System.out.println("Care absenta doriti sa motivati? ");
				String data = userInput.next();
				Absenta absenta = new Absenta(data);
				student.getMaterii().get(indexDeMaterie).getAbsente()
						.get(student.getMaterii().get(indexDeMaterie).getAbsente().indexOf(absenta)).setAbsent(false);
				System.out.println("Absenta a fost modificata.");
				afiseazaAbsente(student, indexDeMaterie);
				return;
			}
		}
		System.out.println("Numele tastat nu este valid.");

	}

}
