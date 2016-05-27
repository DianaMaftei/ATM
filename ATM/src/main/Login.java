package main;

import java.util.Scanner;
import main.Feedback;
import main.Database;
import main.Account;

public class Login {

	static final int MAX_LOGIN_RETRIES = 3;

	public static Account loggedIn;
	private static String userName;
	private String password;

	private Scanner userInput = new Scanner(System.in);

	public void doLogin() {
		// check user and password for a total of MAX tries

		for (int i = MAX_LOGIN_RETRIES; i > 0; i--) {
			System.out.printf("Enter your username and password! You've got %d more tries!\n", i);
			System.out.print("Username: ");
			userName = userInput.next().toLowerCase();
			if(checkIfUserExists(userName) != null){
				System.out.print("Password: ");
				password = userInput.next();
			}else{
				continue;
			}

			String result =  checkPassword(password);
			Feedback.feedbackToUser(result);

			if (Feedback.COD_SUCCESS.equals(result)) {
				loggedIn = checkIfUserExists(userName);
				return;
			}
		}
		// freeze account after the number of tries was exceeded
		Account.freezeAccount();
		Feedback.feedbackToUser(Feedback.COD_FREEZE);
	}

	public String checkPassword(String passWord) {
		if (passWord.equals(loggedIn.getPassword())) {
			if (loggedIn.isActiveAccount() == false) {
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
		Feedback.feedbackToUser(Feedback.COD_USER);
		return null;
	}

}
