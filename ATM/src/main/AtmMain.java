//19.05.2016 - class
package main;

import java.util.InputMismatchException;
import java.util.Scanner;

public class AtmMain {

	static boolean isUserLoggedIn = false; // was the login successful?
	static boolean isAtmTurnedOff = false; // has the ATM been shut down?

	public static void main(String[] args) {

		while (!isAtmTurnedOff) {
			doLogin();
			runAtmCommands();
		}
	}


	public static void shutDownAtm() {
		if (user.equalsIgnoreCase("admin")) {
			System.out.println("ATM is shutting down.");
			isAtmTurnedOff = true;
		} else {
			System.out.println("You do not have permition to perform this command.");
		}
	}

	// run ATM menu
	public static void runAtmCommands() {
		if (isUserLoggedIn) {
			String nextCommand = "N";
			do {
				displayOptionsMenu();
				optionsMenu();

				if (!isAtmTurnedOff) {
					do {
						System.out.println("Do you want to continue? Type \"Y\"  for yes or \"N\" for no.");
						nextCommand = input.next().toUpperCase();
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
