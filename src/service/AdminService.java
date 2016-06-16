package service;
/**
*
*@author diana.maftei[at]gmail.com
*/
import java.util.Scanner;

import entities.Client;
import userInteraction.UserInterface;

public class AdminService {
	private Login login = new Login();
	private UserInterface feedback = new UserInterface();
	private Scanner userInput = new Scanner(System.in);

	public void addNewUser() {
		feedback.displayMessageToUser("NEW_ACCOUNT_NAME");
		String newAccountName = userInput.next();
		if (!login.doesUserExist(newAccountName, "client")) {
			feedback.displayMessageToUser("NEW_PIN");
			String newAccountPassword = userInput.next();
			if (login.isPinFormatValid(newAccountPassword)) {
				AtmMain.currentDatabase.getClientsList().add(new Client(newAccountName, newAccountPassword));
				System.out.println("The account " + newAccountName + " has been added.");
			} else {
				feedback.displayMessageToUser("INCORRECT_PIN_FORMAT");
			}
		} else {
			feedback.displayMessageToUser("USER_EXISTS");
		}
	}

	public void reactivateAccount() {
		feedback.displayMessageToUser("ACTIVATE_ACCOUNT");
		String clientNameRead = userInput.next();
		Client accountToMakeValid = login.getClient(clientNameRead);
		if (accountToMakeValid != null) {
			if (accountToMakeValid.isActiveAccount()) {
				feedback.displayMessageToUser("ACTIVE_ACCOUNT");
			} else {
				accountToMakeValid.setActiveAccount(true);
				System.out.println(String.format("The account %s is now active!\n", accountToMakeValid.getName()));
			}
		} else {
			feedback.displayMessageToUser("INVALID_ USER");
		}
	}

	public void shutDownAtm() {
		feedback.displayMessageToUser("SHUTTING_DOWN");
		AtmMain.setAtmTurnedOff(true);
		AtmMain.setUserLoggedIn(false);
	}
}
