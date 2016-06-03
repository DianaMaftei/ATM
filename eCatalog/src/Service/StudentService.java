package Service;

import java.util.Scanner;

import entity.Materie;
import entity.Student;

public class StudentService {
	private Scanner userInput = new Scanner(System.in);
	private Student currentStudent = (Student) Login.currentUser;

	public void afiseazaOptiuniVizualizare() {
		System.out.println("La ce materie vrei sa vezi notele?");
		System.out.println("La ce materie vrei sa vezi absentele?");
		System.out.println("La ce materie vrei sa vezi media?");

		System.out.println("Acestea sunt materiile disponibile:");
		for (Materie m : currentStudent.getMaterii()) {
			System.out.println(m.getNume());
		}
		String materie = userInput.next();
		afiseazaNote(materie);

		afiseazaAbsente(materie);
		afiseazaMedie(materie);

		System.out.println("Materia nu exista. Selectati din lista existenta.");
	}

	public void afiseazaNote(String materie) {
		for (int i = 0; i < currentStudent.getMaterii().size(); i++) {
			if (materie.equalsIgnoreCase(currentStudent.getMaterii().get(i).getNume())) {
				System.out.printf("%s - ", currentStudent.getMaterii().get(i).getNume());
				System.out.printf(" %s \n", currentStudent.getMaterii().get(i).getNote());
				return;
			}
		}
	}

	public void afiseazaAbsente(String materie) {
		for (int i = 0; i < currentStudent.getMaterii().size(); i++) {
			if (materie.equalsIgnoreCase(currentStudent.getMaterii().get(i).getNume())) {
				System.out.printf("%s - ", currentStudent.getMaterii().get(i).getNume());
				int totalAbsente = 0;
				for(int j = 0; j < currentStudent.getMaterii().get(i).getAbsente().size(); j++){
					if (currentStudent.getMaterii().get(i).getAbsente().get(j).isAbsent()){
						System.out.printf(" %s ", currentStudent.getMaterii().get(i).getAbsente().get(j));		
						totalAbsente++;
					}
				}
				if(totalAbsente == 0){
					System.out.println("Nu ai nicio absenta! Felicitari!");
					return;
				}
				System.out.println();
				return;
			}
		}
	}

	public void afiseazaMedie(String materie) {
		for (int i = 0; i < currentStudent.getMaterii().size(); i++) {
			if (materie.equalsIgnoreCase(currentStudent.getMaterii().get(i).getNume())) {
				double total = 0;
				for(int j = 0; j < currentStudent.getMaterii().get(i).getNote().size(); j++){
					total += currentStudent.getMaterii().get(i).getNote().get(j).getValoare();
				}
				total = total / currentStudent.getMaterii().get(i).getNote().size();
				System.out.printf("Media la %s este: %.2f \n", materie, total);
				return;
			}

		}

	}

	public void afiseazaToateDatele(){
		
	}
}
