package service;

import java.util.Scanner;

import entity.Client;

public class AdminService {

	private Login localLogin = new Login();
	private Feedback feedback = new Feedback();
	private Scanner userInput = new Scanner(System.in);

	public void addNewUser() {
		feedback.displayMessageToUser("NEW_ACCOUNT_NAME");
		String newAccountName = userInput.next();
		if (!localLogin.doesUserExist(newAccountName, "client")) {
			feedback.displayMessageToUser("NEW_PASS");
			String newAccountPassword = userInput.next();
			if (localLogin.isPinFormatValid(newAccountPassword)) {
				AtmMain.currentDatabase.getClientsList().add(new Client(newAccountName, newAccountPassword));
				System.out.println("The account " + newAccountName + " has been added.");
			}else{
				feedback.displayMessageToUser("INCORRECT_PASS_FORMAT");
			}
			
		} else {
			feedback.displayMessageToUser("USER_EXISTS");
		}

	}

	public void reactivateAccount() {
		feedback.displayMessageToUser("ACTIVATE_ACCOUNT");
		String clientNameRead = userInput.next();
		Client accountToMakeValid = localLogin.getClient(clientNameRead);
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
