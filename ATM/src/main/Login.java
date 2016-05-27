package main;

import java.util.Scanner;
import main.Feedback;
import main.Database;
import main.Account;

public class Login {

	static final int MAX_LOGIN_RETRIES = 3;

	public Account loggedIn;
	public Account tryingToLogIn;
	private String userNameRead;
	private String passwordRead;
	private Feedback userFeedback;
	private Scanner userInput = new Scanner(System.in);

	public void doLogin() {
		// check user and password for a total of MAX tries
		userFeedback = new Feedback();

		for (int i = MAX_LOGIN_RETRIES; i > 0; i--) {
			System.out.printf("Enter your username and password! You've got %d more tries!\n", i);
			System.out.print("Username: ");
			userNameRead = userInput.next().toLowerCase();
			tryingToLogIn = checkIfUserExists(userNameRead);
			if (tryingToLogIn != null) {
				System.out.print("Password: ");
				passwordRead = userInput.next();
			} else {
				userFeedback.feedbackToUser(Feedback.CODE_USER);
				continue;
			}

			String result = checkPassword(passwordRead);
			userFeedback.feedbackToUser(result);
			if(result.equals(Feedback.CODE_FROZEN)){
				return;
			}

			if (Feedback.CODE_SUCCESS.equals(result)) {
				loggedIn = checkIfUserExists(userNameRead);
				AtmMain.isUserLoggedIn = true;
				return;
			}
		}
		// freeze account after the number of tries was exceeded
		new AccountService().freezeAccount(checkIfUserExists(userNameRead));
		userFeedback.feedbackToUser(Feedback.CODE_FREEZE);
	}

	public String checkPassword(String passWord) {
		if (passWord.equals(tryingToLogIn.getPassword())) {
			if (tryingToLogIn.isActiveAccount() == false) {
				return Feedback.CODE_FROZEN;
			} else {

				return Feedback.CODE_SUCCESS;
			}
		} else {
			return Feedback.CODE_PASS;
		}
	}

	public Account checkIfUserExists(String userName) {
		for (Account account : Database.accounts) {
			if (userName.equalsIgnoreCase(account.getName())) {
				return account;
			}
		}
		return null;
	}

	public boolean isPasswordFormatValid(String password) {
		if ((password.length() == 4)) {
			int numberOfValidDigits = 0;
			for (int i = 0; i < 4; i++) {
				if ("0123456789".contains("" + password.charAt(i))) {
					numberOfValidDigits++;
				}
			}
			if (numberOfValidDigits == 4) {
				return true;
			}
		} else {
			System.out.println("You did not type a valid password. Try again.");
		}
		return false;
	}
}
