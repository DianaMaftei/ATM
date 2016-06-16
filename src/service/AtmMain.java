package service;

import userInteraction.UserInterface;

/**
 *
 * @author diana.maftei[at]gmail.com
 */
public class AtmMain {
	private static boolean isAtmTurnedOff;
	private static boolean isUserLoggedIn;
	private Login login = new Login();
	private static String menuSelect;
	public static Database currentDatabase = new Database();
	private static UserInterface userInterface;

	public static void main(String[] args) {
		userInterface = new UserInterface();
		new AtmMain().startAtm();
	}

	public void startAtm() {
		currentDatabase.initializeDatabase();
		while (!isAtmTurnedOff) {
			menuSelect = userInterface.selectAtmMenu();
			login.doLogin(menuSelect);
			while (isUserLoggedIn) {
				runAtmCommands(menuSelect);
			}
		}
	}

	public void runAtmCommands(String menuSelect) {
		if ("admin".equals(menuSelect)) {
			userInterface.doAdminCommand();
		} else if ("client".equals(menuSelect)) {
			userInterface.doClientCommand();
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
