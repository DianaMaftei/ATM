package service;
/**
*
*@author diana.maftei[at]gmail.com
*/
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import entities.Admin;
import entities.Client;
import userInteraction.UserInterface;

public class Login {
	static final int MAX_LOGIN_RETRIES = 3;

	static Admin currentAdmin;
	static Client currentClient;
	private UserInterface userInterface;
	private Scanner userInput = new Scanner(System.in);
	private String userNameRead;
	private Pattern pr;
	private Matcher m;

	// TODO find a shorter/cleaner way
	public void doLogin(String typeOfUser) {
		// check user and password for a total of MAX tries
		userInterface = new UserInterface();
		for (int i = MAX_LOGIN_RETRIES; i > 0; i--) {
			if ("admin".equals(typeOfUser)) {
				System.out.println(String.format("Enter your username and password! You've got %d more tries!\n", i));
				userInterface.displayMessageToUser("USERNAME");
				userNameRead = userInput.next().toLowerCase();
				if (doesUserExist(userNameRead, typeOfUser)) {
					System.out.print("Password: ");
					String passwordRead = userInput.next();
					if (checkAdminPassword(passwordRead)) {
						userInterface.displayMessageToUser("SUCCESS");
						AtmMain.setUserLoggedIn(true);
						return;
					} else {
						userInterface.displayMessageToUser("INCORRECT_PASS");
					}
				} else {
					userInterface.displayMessageToUser("INVALID_USER");
				}
			} else if ("client".equals(typeOfUser)) {
				System.out.println(String.format("Enter your username and PIN number! You've got %d more tries!\n", i));
				userInterface.displayMessageToUser("USERNAME");
				userNameRead = userInput.next().toLowerCase();
				if (doesUserExist(userNameRead, typeOfUser)) {
					userInterface.displayMessageToUser("PIN");
					String pinRead = userInput.next();
					if (checkClientPin(pinRead)) {
						if (currentClient.isActiveAccount()) {
							userInterface.displayMessageToUser("SUCCESS");
							AtmMain.setUserLoggedIn(true);
							return;
						} else {
							userInterface.displayMessageToUser("FROZEN");
						}

					} else {
						userInterface.displayMessageToUser("INCORRECT_PIN");
					}
				} else {
					userInterface.displayMessageToUser("INVALID_USER");
				}
			}
		}

		// TODO rewrite it: if someone accesses the ATM with 3 different invalid
		// usernames in a span of 5 minutes - block atm and alert the bank
		// TODO rewrite it: if the same account tried to access three times
		// freeze account after the number of tries was exceeded
		if ("client".equals(typeOfUser)) {
			new AccountService().freezeAccount(currentClient);
			userInterface.displayMessageToUser("FREEZE");

		} else if ("admin".equals(typeOfUser)) {
			userInterface.displayMessageToUser("UNLAFUL");
			// TODO no admin can login for the next hour

		}
		// currentClient and currentAdmin are the users currently trying to
		// login
		// if the login is unsuccessful, wipe the pointer to said account
		currentClient = null;
		currentAdmin = null;

	}

	// only needed in login
	private boolean checkAdminPassword(String passWord) {
		if (passWord != null && passWord.length() == currentAdmin.getPassword().length()) {
			if (passWord.equals(currentAdmin.getPassword())) {
				return true;
			}
		}
		return false;
	}

	// TODO - check against feature envy
	// also needed with add user option for admin
	private boolean checkClientPin(String pin) {
		if (isPinFormatValid(pin)) {
			if (pin.equals(currentClient.getPinNumber())) {
				return true;
			}
		}
		return false;
	}

	public boolean doesUserExist(String userName, String typeOfUser) {
		if ("admin".equals(typeOfUser)) {
			for (Admin account : AtmMain.currentDatabase.getAdminsList()) {
				if (userName.equalsIgnoreCase(account.getUsername())) {
					currentAdmin = account;
					return true;
				}
			}

		} else if ("client".equals(typeOfUser)) {
			Client account = getClient(userName);
			if (account != null) {
				currentClient = account;
				return true;
			}
		}
		return false;
	}

	public Client getClient(String userName) {
		for (Client account : AtmMain.currentDatabase.getClientsList()) {
			if (userName.equalsIgnoreCase(account.getName())) {
				return account;
			}
		}
		return null;
	}

	public boolean isPinFormatValid(String pin) {
		// with RegEx
		String pattern = "^[0-9]{4}$";
		pr = Pattern.compile(pattern);
		m = pr.matcher(pin);

		if (m.matches()) {
			return true;
		}
		return false;
	}
}
