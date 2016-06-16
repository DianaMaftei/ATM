package service;

import java.util.Scanner;

import entity.Client;

public class AccountService {
	
	private Scanner userInput = new Scanner(System.in);
	private Login localLogin = new Login();
	private Feedback feedback = new Feedback();
	
	public void changeUserPassword() {
		feedback.displayMessageToUser("CURRENT_PASS");
		String oldPassword = userInput.next();
		// if current password is valid
		if (oldPassword.equals(Login.currentClient.getPinNumber())) {
			// make valid only 4 digit pass
			feedback.displayMessageToUser("NEW_PASS");
			String newPassword1 = userInput.next();
			if (localLogin.isPinFormatValid(newPassword1)) {
				feedback.displayMessageToUser("RE_NEW_PASS");
				String newPassword2 = userInput.next();
				if (newPassword1.equals(newPassword2)) {
					Login.currentClient.setPinNumber((newPassword2));
					feedback.displayMessageToUser("SAVED_PASS");
				} else {
					feedback.displayMessageToUser("NO_MATCH_PASS");
				}
			} else {
				feedback.displayMessageToUser("INCORRECT_PASS");
			}
		} else {
			feedback.displayMessageToUser("INCORRECT_PASS");
		}
	}
	
	public void freezeAccount(Client accountToFreeze) {
		accountToFreeze.setActiveAccount(false);
	}
}
