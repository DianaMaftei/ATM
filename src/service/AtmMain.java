package service;

import java.util.Scanner;

public class AtmMain {

	// has the ATM been shut down?
	private static boolean isAtmTurnedOff;
	private static boolean isUserLoggedIn;
	private Login localLogin = new Login();
	private static String menuSelect;
	public static Database currentDatabase = new Database();
	private static Feedback userFeedback;

	public static void main(String[] args) {
		userFeedback = new Feedback();
		new AtmMain().startAtm();
	}

	public void startAtm() {
		currentDatabase.initializeDatabase();
		while (!isAtmTurnedOff) {
			Scanner sc = new Scanner(System.in);
			// clients and admins open different menus - front of ATM or inside
			// menu, on the back
			userFeedback.displayMessageToUser("ACCESS");
			menuSelect = sc.next();
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
