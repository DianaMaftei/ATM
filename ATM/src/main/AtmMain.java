//19.05.2016 - class
package main;

import java.util.Scanner;

public class AtmMain {

	static boolean isAtmTurnedOff = false; // has the ATM been shut down?
	static boolean isUserLoggedIn = false;
	private static Scanner userInput = new Scanner(System.in);

	public static void main(String[] args) {
		
		Database.createUsersAtStartup();
		
		while (!isAtmTurnedOff) {
			Login.doLogin();
			runAtmCommands();
		}
	}


	// run ATM menu
	public static void runAtmCommands() {
		if (isUserLoggedIn) {
			String nextCommand = "N";
			do {
				if(Login.loggedIn.getAccountType().equals("admin")){
					CommandsMenu.displayAdminMenu();
					CommandsMenu.optionsMenuAdmin();
				} else{
					CommandsMenu.displayUserMenu();
					CommandsMenu.optionsMenuUser();
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
