package service;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import entity.Admin;
import entity.Client;

public class Login {

	static final int MAX_LOGIN_RETRIES = 3;

	static Admin currentAdmin;
	static Client currentClient;
	private Feedback userFeedback;
	private Scanner userInput = new Scanner(System.in);
	private String userNameRead;
	private Pattern pr;
	private Matcher m;

	// TODO find a shorter/cleaner way
	public void doLogin(String typeOfUser) {
		// check user and password for a total of MAX tries
		userFeedback = new Feedback();
		for (int i = MAX_LOGIN_RETRIES; i > 0; i--) {
			System.out.println(String.format("Enter your username and password! You've got %d more tries!\n", i));
			userFeedback.displayMessageToUser("USERNAME");
			userNameRead = userInput.next().toLowerCase();

			if ("admin".equals(typeOfUser)) {
				if (doesUserExist(userNameRead, typeOfUser)) {
					System.out.print("Password: ");
					String passwordRead = userInput.next();
					if (checkAdminPassword(passwordRead)) {
						userFeedback.displayMessageToUser("SUCCESS");
						AtmMain.setUserLoggedIn(true);
						return;
					} else {
						userFeedback.displayMessageToUser("INCORRECT_PASS");
					}
				} else {
					userFeedback.displayMessageToUser("INVALID_USER");
				}
			} else if ("client".equals(typeOfUser)) {
				if (doesUserExist(userNameRead, typeOfUser)) {
					userFeedback.displayMessageToUser("PIN");
					String pinRead = userInput.next();
					if (checkClientPin(pinRead)) {
						if (currentClient.isActiveAccount()) {
							userFeedback.displayMessageToUser("SUCCESS");
							AtmMain.setUserLoggedIn(true);
							return;
						} else {
							userFeedback.displayMessageToUser("FROZEN");
						}

					} else {
						userFeedback.displayMessageToUser("INCORRECT_PIN");
					}
				} else {
					userFeedback.displayMessageToUser("INVALID_USER");
				}

			}
		}

		// TODO rewrite it: if someone accesses the ATM with 3 different invalid
		// usernames in a span of 5 minutes - block atm and alert the bank
		// TODO rewrite it: if the same account tried to access three times
		// freeze account after the number of tries was exceeded
		if ("client".equals(typeOfUser)) {
			new AccountService().freezeAccount(currentClient);
			userFeedback.displayMessageToUser("FREEZE");

		} else if ("admin".equals(typeOfUser)) {
			userFeedback.displayMessageToUser("UNLAFUL");
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
