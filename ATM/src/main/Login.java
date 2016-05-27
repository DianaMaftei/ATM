package main;

import java.util.Scanner;
import main.Feedback;
import main.Database;
import main.Account;

public class Login {

	static final int MAX_LOGIN_RETRIES = 3;

	public static Account loggedIn;
	public static Account tryingToLogIn;
	private static String userNameRead;
	private static String passwordRead;
	private static Feedback userFeedback;
	private static Scanner userInput = new Scanner(System.in);

	public static void doLogin() {
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
				userFeedback.feedbackToUser(Feedback.COD_USER);
				continue;
			}

			String result = checkPassword(passwordRead);
			userFeedback.feedbackToUser(result);
			if(result.equals(Feedback.COD_FROZEN)){
				return;
			}

			if (Feedback.COD_SUCCESS.equals(result)) {
				loggedIn = checkIfUserExists(userNameRead);
				AtmMain.isUserLoggedIn = true;
				return;
			}
		}
		// freeze account after the number of tries was exceeded
		AccountService.freezeAccount(checkIfUserExists(userNameRead));
		userFeedback.feedbackToUser(Feedback.COD_FREEZE);
	}

	public static String checkPassword(String passWord) {
		if (passWord.equals(tryingToLogIn.getPassword())) {
			if (tryingToLogIn.isActiveAccount() == false) {
				return Feedback.COD_FROZEN;
			} else {

				return Feedback.COD_SUCCESS;
			}
		} else {
			return Feedback.COD_PASS;
		}
	}

	public static Account checkIfUserExists(String userName) {
		for (Account account : Database.accounts) {
			if (userName.equalsIgnoreCase(account.getName())) {
				return account;
			}
		}
		return null;
	}

	public static boolean isPasswordFormatValid(String password) {
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
