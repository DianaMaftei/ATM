package service;

import java.util.Scanner;

import entity.Admin;
import entity.Client;

public class Login {

	static final int MAX_LOGIN_RETRIES = 3;

	static Admin currentAdmin;
	static Client currentClient;
	private Feedback userFeedback;
	private Scanner userInput = new Scanner(System.in);
	private String userNameRead;

	public void doLogin(String typeOfUser) {
		// check user and password for a total of MAX tries
		userFeedback = new Feedback();
		for (int i = MAX_LOGIN_RETRIES; i > 0; i--) {
			userFeedback.displayMessageToUser(
					String.format("Enter your username and password! You've got %d more tries!\n", i), "out");
			userFeedback.displayMessageToUser("Username: ", "out");
			userNameRead = userInput.next().toLowerCase();

			if ("admin".equals(typeOfUser)) {
				if (doesUserExist(userNameRead, typeOfUser)) {
					System.out.print("Password: ");
					String passwordRead = userInput.next();
					if (checkAdminPassword(passwordRead)) {
						userFeedback.displayMessageToUser("You have been successfully logged in.", "out");
						AtmMain.setUserLoggedIn(true);
						return;
					} else {
						userFeedback.displayMessageToUser("Incorrect password, please try again.", "err");
					}
				} else {
					userFeedback.displayMessageToUser("Invalid user, please try again.", "err");
				}
			} else if ("client".equals(typeOfUser)) {
				if (doesUserExist(userNameRead, typeOfUser)) {
					userFeedback.displayMessageToUser("Pin: ", "out");
					String pinRead = userInput.next();
					if (checkClientPin(pinRead)) {
						if (currentClient.isActiveAccount()) {
							userFeedback.displayMessageToUser("You have been successfully logged in.", "out");
							AtmMain.setUserLoggedIn(true);
							return;
						} else {
							userFeedback.displayMessageToUser("Your account is frozen, contact your local bank.",
									"err");
						}

					} else {
						userFeedback.displayMessageToUser("Incorrect pin, please try again.", "err");
					}
				} else {
					userFeedback.displayMessageToUser("Invalid user, please try again.", "err");
				}

			}
		}

		// freeze account after the number of tries was exceeded
		if ("client".equals(typeOfUser)) {
			new AccountService().freezeAccount(currentClient);
			userFeedback.displayMessageToUser(
					"You have reached your maximum number of tries, your account has been frozen.", "err");

		} else if ("admin".equals(typeOfUser)) {
			userFeedback.displayMessageToUser("Unlawfull access! The ATM will now shut down!", "err");
			// TODO no admin can login for the next hour

		}
		//currentClient and currentAdmin are the users currently trying to login
		//if the login is unsuccessful, wipe the pointer to said account 
		currentClient = null;
		currentAdmin = null;

	}

	//only needed in login
	private boolean checkAdminPassword(String passWord) {
		if (passWord != null && passWord.length() == currentAdmin.getPassword().length()) {
			if (passWord.equals(currentAdmin.getPassword())) {
				return true;
			}
		}
		return false;
	}

	//also needed in add user option for admin
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

	public Client getClient(String userName){
		for (Client account : AtmMain.currentDatabase.getClientsList()) {
			if (userName.equalsIgnoreCase(account.getName())) {
				 return account;
			}
		}
		return null;
	}

	public boolean isPinFormatValid(String pin) {
		if ((pin.length() == 4)) {
			int numberOfValidDigits = 0;
			for (int i = 0; i < 4; i++) {
				if ("0123456789".contains("" + pin.charAt(i))) {
					numberOfValidDigits++;
				}
			}
			if (numberOfValidDigits == 4) {
				return true;
			}
		}
		return false;
	}

}
