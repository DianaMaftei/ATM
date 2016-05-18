//18.05.2016 - class
package main;

import java.util.Scanner;

public class AtmMain {

	static final String[] userList = { "admin", "diana", "theo", "john", "charlie", "tim", "hannah" };
	static String[] passwordList = { "ad", "dia", "th", "jo", "ch", "tiha", "hannah123" };
	static double[] balanceList = { 0, 100.00, 4324.34, 6547.76, 30000.00, 2400.57, 1500.10 };
	static boolean[] activeAccountsList = { true, true, false, true, false, true, true };

	static final String COD_SUCCESS = "SUCCESS";
	static final String COD_USER = "WRONG_USER";
	static final String COD_PASS = "INVALID_PASS";
	static final String COD_FREEZE = "FREEZE";
	static final String COD_FROZEN = "FROZEN";
	static final int MAX_RETRIES = 3; // how many times the user can try to
										// login before his account gets frozen

	static int position = 0;

	static boolean loggedIn = false;

	static boolean shutDown = false;

	static String user;
	static String pass;
	static double userBalance;

	static Scanner input = new Scanner(System.in);

	public static void main(String[] args) {

		while (!shutDown) {

			doLogin();
			atmCommands();
		}
		// nu vrea sa continue, show menu for new user; else - show menu again
		// else -> try again - > start from prezinta optiune autentificare

	}

	// get the password and username from client
	public static void readUserAndPass() {
		user = input.next().toLowerCase();
		pass = input.next();
	}

	// try to login based on the user and password given,
	// insist if user/pass are incorrect, freeze account after MAX number of
	// incorrect tries
	public static void doLogin() {
		// check user and password for a total of MAX tries
		for (int i = MAX_RETRIES; i > 0; i--) {
			System.out.printf("Enter your username and password! You've got %d more tries!\n", i);
			readUserAndPass();
			String result = checkLoginData(user, pass);
			feedbackToUser(result);

			if (COD_SUCCESS.equals(result)) {
				userBalance = balanceList[position];
				loggedIn = true;
				return;
			}
			if (COD_FROZEN.equals(result)) {
				return;
			}
		}
		// freeze account after the number of tries was exceeded
		freezeAccount();
		feedbackToUser(COD_FREEZE);
		return;
	}

	// check the user and pass given in the userlist and passlist
	public static String checkLoginData(String user, String pass) {
		for (int i = 0; i < userList.length; i++) {
			if (userList[i].equals(user)) {
				if (passwordList[i].equals(pass)) {
					if (activeAccountsList[i] == false) {
						return COD_FROZEN;
					} else {
						// store the position in the list where the user was
						// found
						position = i;
						return COD_SUCCESS;
					}
				} else {
					return COD_PASS;
				}
			}
		}
		return COD_USER;
	}

	// display a message to the user based on the result of their input
	public static String feedbackToUser(String code) {
		switch (code) {
		case COD_SUCCESS:
			System.out.println("You have been successfully logged in.");
			break;

		case COD_PASS:
			System.out.println("Incorrect password, please try again.");
			break;

		case COD_USER:
			System.out.println("Invalid user, please try again.");
			break;

		case COD_FREEZE:
			System.out.println("You have reached your maximum number of tries, your account has been frozen.");
			break;

		case COD_FROZEN:
			System.out.println("Your account is frozen, contact your local bank.");
			break;

		default:
			break;
		}
		return COD_SUCCESS;
	}

	// bonus - freeze account if pass incorrect more than 3 times
	public static void freezeAccount() {
		activeAccountsList[position] = false;
	}

	// display account options
	// 5th option (exit) available if "admin" user is logged in
	public static void displayOptionsMenu() {
		System.out.println("Choose what you want to do. Type the corresponding number: ");
		System.out.printf("\t (1) Check balance.\n");
		System.out.printf("\t (2) Deposit money.\n");
		System.out.printf("\t (3) Withdraw money.\n");
		System.out.printf("\t (4) Change password.\n");
		if (user.equals("admin")) {
			System.out.printf("\t (5) Shut down machine for maintenance.\n");
		}
	}

	// perform commands based on the user inputed option
	// run method based on user option
	public static void optionsMenu() {
		int optionUser = input.nextInt();
		switch (optionUser) {
		case 1:
			checkBalance();
			break;
		case 2:
			depositMoney();
			break;
		case 3:
			withdrawMoney();
			break;
		case 4:
			changePassWord();
			break;
		case 5:
			exit();
			break;
		default:
			break;
		}
	}

	// check the amount of money in the account
	// check how much money is in the account
	public static void checkBalance() {
		System.out.printf("Your balance is: %.2f.\n", balanceList[position]);
	}

	// withdraw an amount of money from the accout
	// withdraw money from an account
	public static void withdrawMoney() {
		System.out.println("How much money do you want to withdraw?");
		double sumToWithdraw = input.nextDouble();
		if (sumToWithdraw > 0) { // check if the sum is not negative
			if ((userBalance - sumToWithdraw) > 0) {
				userBalance -= sumToWithdraw;
				System.out.printf("You have withdrawn %.2f. Your current balance is %.2f.\n", sumToWithdraw,
						userBalance);
			} else {
				System.out.println("The sum you are trying to withdraw is larger than your current balance.");
			}
		} else {
			System.out.println("The sum you are trying to withdraw is not valid. Please try again.");
		}
	}

	// deposit an amount of money in the account
	// deposit money into your account
	public static void depositMoney() {
		System.out.println("How much money do you want to deposit?");
		double sumToDeposit = input.nextDouble();
		if (sumToDeposit > 0) { // check if the sum is not negative
			userBalance += sumToDeposit;
			System.out.printf("You have deposited %.2f. Your current balance is %.2f.\n", sumToDeposit, userBalance);
		} else {
			System.out.println("The sum you are trying to deposit is not valid. Please try again.");
		}
	}

	// change the user's password
	// change user's password
	public static void changePassWord() {
		System.out.println("Type your current password.");
		String oldPassword = input.next();
		// if current password is valid
		if (oldPassword.equals(passwordList[position])) {
			System.out.println("Type your new password.");
			String newPassword1 = input.next();
			System.out.println("Type new password again.");
			String newPassword2 = input.next();
			if (newPassword1.equals(newPassword2)) {
				passwordList[position] = newPassword2;
				System.out.println("Your password has been saved.");
			}
		}
	}

	// shut down the ATM for maintenance
	// exit - admin - maintenence - CHECK WHAT USER IS LOGGED IN
	public static void exit() {
		if (user.equals("admin")) {
			System.out.println("ATM is shutting down.");
			shutDown = true;
		} else {
			System.out.println("You do not have permition to perform this command.");
		}
	}

	public static void atmCommands() {
		if (loggedIn) {
			String nextCommand = "N";
			do {
				displayOptionsMenu();
				optionsMenu();
				if (!shutDown) {
					System.out.println("Do you want to continue? Type \"Y\"  for yes or \"N\" for no.");
					nextCommand = input.next().toUpperCase();
					if(nextCommand.toUpperCase().equals("N")){
						loggedIn = false;
					}
				}

			} while (nextCommand.equals("Y"));
		}
	}

	// extras de cont bonus
	// admin can unlock - bonus

}
