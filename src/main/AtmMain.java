//19.05.2016 - class
package main;

import java.util.Scanner;

public class AtmMain {

	// has the ATM been shut down?
	private static boolean isAtmTurnedOff = false;
	private static boolean isUserLoggedIn = false;
	private Login localLogin = new Login();
	private static String menuSelect;
	public static Database currentDatabase = new Database();

	public static void main(String[] args) {
		// client or admin
		menuSelect = "client";
		new AtmMain().startAtm();
	}

	public void startAtm() {
		currentDatabase.initializeDatabase();

		while (!isAtmTurnedOff) {
			localLogin.doLogin(menuSelect);
			while (isUserLoggedIn) {
				runAtmCommands(menuSelect);
			}
		}
	}

	// run ATM menu
	public void runAtmCommands(String menuSelect) {
		if ("admin".equals(menuSelect)) {
			new AdminMenu().RunAdminMenu();
		} else if ("client".equals(menuSelect)) {
			new ClientMenu().RunClientMenu();
		}
	}

	public static boolean isUserLoggedIn() {
		return isUserLoggedIn;
	}

	public static void setUserLoggedIn(boolean isUserLoggedIn) {
		AtmMain.isUserLoggedIn = isUserLoggedIn;
	}

	public static boolean isAtmTurnedOff() {
		return isAtmTurnedOff;
	}

	public static void setAtmTurnedOff(boolean isAtmTurnedOff) {
		AtmMain.isAtmTurnedOff = isAtmTurnedOff;
	}
}
