package Service;

import java.util.Scanner;

import entity.Materie;
import entity.Student;
import entity.User;

public class Login {
	Scanner userInput = new Scanner(System.in);
	String nume;
	String parola;
	static User currentUser;

	public void afiseazaMeniuLogare() {
		System.out.println("Bine ati venit! Tastati numele si parola pentru a va loga.");
		nume = userInput.next();
		parola = userInput.next();
	}

	public void doLogin() {
		for (User user : eCatalogMain.currentDatabase.getUseri()) {
			if (nume.equalsIgnoreCase(user.getUserName())) {
				if (parola.equalsIgnoreCase(user.getParola())) {
					System.out.println("Te-ai logat cu succes!");
					currentUser = user;
					return;
				}
				System.out.println("Parola incorecta!");
				return;
			}
		}
		System.out.println("Username-ul nu exista.");
	}

	
}
