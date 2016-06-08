package service;

import java.util.Scanner;

import entity.Client;

public class AdminService {

	private Login localLogin = new Login();
	private Feedback feedback = new Feedback();
	private Scanner userInput = new Scanner(System.in);

	public void addNewUser() {
		feedback.displayMessageToUser("Type a new account name:", "out");
		String newAccountName = userInput.next();
		if (!localLogin.doesUserExist(newAccountName, "client")) {
			feedback.displayMessageToUser("Type a 4 digit password for the new account:", "out");
			String newAccountPassword = userInput.next();
			if (localLogin.isPinFormatValid(newAccountPassword)) {
				AtmMain.currentDatabase.getClientsList().add(new Client(newAccountName, newAccountPassword));
				feedback.displayMessageToUser("The account " + newAccountName + " has been added.", "out");
			}else{
				feedback.displayMessageToUser("Incorrect password format. Try again.", "err");
			}
			
		} else {
			feedback.displayMessageToUser("The name already exists. Please choose another one.", "err");
		}

	}

	public void reactivateAccount() {
		feedback.displayMessageToUser("Type the name of the account you want to make active.", "out");
		String clientNameRead = userInput.next();
		Client accountToMakeValid = localLogin.getClient(clientNameRead);
		if (accountToMakeValid != null) {
			if (accountToMakeValid.isActiveAccount()) {
				feedback.displayMessageToUser("The account is already active.", "err");
			} else {
				accountToMakeValid.setActiveAccount(true);
				feedback.displayMessageToUser(String.format("The account %s is now active!\n", accountToMakeValid.getName()), "out");
			}
		} else {
			feedback.displayMessageToUser("The account name is not valid.", "err");
		}

	}

	public void shutDownAtm() {
		feedback.displayMessageToUser("ATM is shutting down.", "out");
		AtmMain.setAtmTurnedOff(true);
		AtmMain.setUserLoggedIn(false);

	}
}
