package service;

import java.util.Scanner;

import entity.Client;

public class AccountService {
	
	private Scanner userInput = new Scanner(System.in);
	private Login localLogin = new Login();
	private Feedback feedback = new Feedback();
	
	public void changePassWord() {
		feedback.displayMessageToUser("Type your current password.", "out");
		String oldPassword = userInput.next();
		// if current password is valid
		if (oldPassword.equals(Login.currentClient.getPinNumber())) {
			// make valid only 4 digit pass
			feedback.displayMessageToUser("Type a new 4 digit password.", "out");
			String newPassword1 = userInput.next();
			if (localLogin.isPinFormatValid(newPassword1)) {
				feedback.displayMessageToUser("Type the 4 digit password again.", "out");
				String newPassword2 = userInput.next();
				if (newPassword1.equals(newPassword2)) {
					Login.currentClient.setPinNumber((newPassword2));
					feedback.displayMessageToUser("Your password has been saved.", "out");
				} else {
					feedback.displayMessageToUser("The two passwords do not match. Please try again.\n", "err");
				}
			} 
		} else {
			feedback.displayMessageToUser("You did not type the correct password. Try again.\n", "err");
		}
	}
	
	public void freezeAccount(Client accountToFreeze) {
		accountToFreeze.setActiveAccount(false);
	}

}
