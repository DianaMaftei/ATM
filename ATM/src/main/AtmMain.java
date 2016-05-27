//19.05.2016 - class
package main;

import java.util.Scanner;

public class AtmMain {
	
	// has the ATM been shut down?
	static boolean isAtmTurnedOff = false; 
	static boolean isUserLoggedIn = false;
	private Scanner userInput = new Scanner(System.in);
	private CommandsMenu commandsMenu = new CommandsMenu();
	private Login localLogin = new Login();

	public static void main(String[] args) {
		new AtmMain().startAtm();
	}

	public void startAtm() {
		Database.createUsersAtStartup();

		while (!isAtmTurnedOff) {
			localLogin.doLogin();
			runAtmCommands();
		}
	}

	// run ATM menu
	public void runAtmCommands() {
		if (isUserLoggedIn) {
			String nextCommand = "N";
			do {
				if (localLogin.loggedIn.getAccountType().equals("admin")) {
					commandsMenu.displayAdminMenu();
					commandsMenu.optionsMenuAdmin();
				} else {
					commandsMenu.displayUserMenu();
					commandsMenu.optionsMenuUser();
				}

				if (!isAtmTurnedOff) {
					do {
						System.out.println("Do you want to continue? Type \"Y\"  for yes or \"N\" for no.");
						nextCommand = userInput.next().toUpperCase();
					} while (!"YN".contains(nextCommand));
					if (nextCommand.equals("N")) {
						System.out.println("Have a good day!");
						isUserLoggedIn = false;
					}
				}

			} while (nextCommand.equals("Y"));
		}
	}

}
