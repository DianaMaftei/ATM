package service;
/**
*
*@author diana.maftei[at]gmail.com
*/
import java.util.Scanner;

import entities.Client;
import userInteraction.UserInterface;

public class AccountService {
	
	private Scanner userInput = new Scanner(System.in);
	private Login localLogin = new Login();
	private UserInterface feedback = new UserInterface();
	
	public void changeUserPin() {
		feedback.displayMessageToUser("CURRENT_PIN");
		String oldPassword = userInput.next();
		if (oldPassword.equals(Login.currentClient.getPinNumber())) {
			feedback.displayMessageToUser("NEW_PIN");
			String newPassword1 = userInput.next();
			if (localLogin.isPinFormatValid(newPassword1)) {
				feedback.displayMessageToUser("RE_NEW_PIN");
				String newPassword2 = userInput.next();
				if (newPassword1.equals(newPassword2)) {
					Login.currentClient.setPinNumber((newPassword2));
					feedback.displayMessageToUser("SAVED_PIN");
				} else {
					feedback.displayMessageToUser("NO_MATCH_PIN");
				}
			} else {
				feedback.displayMessageToUser("INCORRECT_PIN");
			}
		} else {
			feedback.displayMessageToUser("INCORRECT_PIN");
		}
	}
	
	public void freezeAccount(Client accountToFreeze) {
		accountToFreeze.setActiveAccount(false);
	}
}
