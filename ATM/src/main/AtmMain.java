//19.05.2016 - class
package main;

import java.util.InputMismatchException;
import java.util.Scanner;

public class AtmMain {

	static final String[] userList = { "admin", "diana", null, null, null, null, null, null, null, null };
	static String[] passwordList = { "ad", "dia", null, null, null, null, null, null, null, null };
	static double[] balanceList = { 100.00, 531.72, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 };
	static boolean[] activeAccountsList = { true, true, false, false, false, false, false, false, false, false };
	static String[][] userTransactions = new String[10][100];

	static final String COD_SUCCESS = "SUCCESS";
	static final String COD_USER = "WRONG_USER";
	static final String COD_PASS = "INVALID_PASS";
	static final String COD_FREEZE = "FREEZE";
	static final String COD_FROZEN = "FROZEN";
	static int maxTries; // how many times the user can try to
							// login

	static int indexUserInList = 0; // the position of the logged in account
	static boolean isUserLoggedIn = false; // was the login successful?
	static boolean isAtmTurnedOff = false; // has the ATM been shut down?

	static String user;
	static String pass;

	static Scanner input = new Scanner(System.in);

	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("Read the documentation!");
			return;
		} 
		
		maxTries = Integer.parseInt(args[0]);
		
		while (!isAtmTurnedOff) {
			doLogin();
			runAtmCommands();
		}
	}

	// get the password and username from client
	public static void readUserAndPass() {
		user = input.next().toLowerCase();
		pass = input.next();
	}

	// try to login based on the user and password given,
	public static void doLogin() {
		// check user and password for a total of MAX tries
		for (int i = maxTries; i > 0; i--) {
			System.out.printf("Enter your username and password! You've got %d more tries!\n", i);
			readUserAndPass();
			String result = checkLoginData(user, pass);
			feedbackToUser(result);

			if (COD_SUCCESS.equals(result)) {
				isUserLoggedIn = true;
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
		if (checkUser(user)) {
			if (passwordList[indexUserInList].equals(pass)) {
				if (activeAccountsList[indexUserInList] == false) {
					return COD_FROZEN;
				} else {
					return COD_SUCCESS;
				}
			} else {
				return COD_PASS;
			}
		}
		return COD_USER;
	}

	public static boolean checkUser(String user) {
		for (int i = 0; i < userList.length; i++) {
			if (user.equalsIgnoreCase(userList[i])) {
				// store the position in the list where the user was
				// found
				indexUserInList = i;
				return true;
			}
		}
		return false;
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
		activeAccountsList[indexUserInList] = false;
	}

	public static void displayOptionsMenu() {
		System.out.println("Choose what you want to do. Type the corresponding letter: ");
		System.out.println("\t (1) Check balance.");
		System.out.println("\t (2) Deposit money.");
		System.out.println("\t (3) Withdraw money.");
		System.out.println("\t (4) Check past transactions.");
		System.out.println("\t (5) Change password.");
		if (user.equalsIgnoreCase("admin")) {
			System.out.println("\t (6) Shut down machine for maintenance.");
			System.out.println("\t (7) Add new user.");
			System.out.println("\t (8) Make an inactive account active.");
		}
	}

	// perform commands based on the user inputed option
	public static void optionsMenu() {
		String optionUser = input.next().toUpperCase();
		switch (optionUser) {
		case "1":
			checkBalance();
			break;
		case "2":
			depositMoney();
			break;
		case "3":
			withdrawMoney();
			break;
		case "4":
			getUserTransactions();
			break;
		case "5":
			changePassWord();
			break;
		case "6":
			shutDownAtm();
			break;
		case "7":
			addNewUser();
			break;
		case "8":
			reActivateAccount();
			break;
		default:
			System.out.println("Please type a valid command.");
			break;
		}
	}

	// check the amount of money in the account
	public static void checkBalance() {
		System.out.printf("Your balance is: %.2f.\n", balanceList[indexUserInList]);
	}

	// withdraw an amount of money from the account
	public static void withdrawMoney() {
		try {
			System.out.println("How much money do you want to withdraw?");
			double sumToWithdraw = input.nextDouble();
			if (sumToWithdraw > 0) { // check if the sum is not negative
				if ((balanceList[indexUserInList] - sumToWithdraw) > 0) {
					balanceList[indexUserInList] -= sumToWithdraw;
					System.out.printf("You have withdrawn %.2f. Your current balance is %.2f.\n", sumToWithdraw,
							balanceList[indexUserInList]);
					// store transactions
					storeUserTransactions("Withdraw", sumToWithdraw, balanceList[indexUserInList]);

				} else {
					System.out.println("The sum you are trying to withdraw is larger than your current balance.\n");
				}
			} else {
				System.out.println("The sum you are trying to withdraw is not valid. Please try again.\n");
			}
		} catch (InputMismatchException e) {
			input.nextLine();
			System.out.println("You did not type a valid sum. Please try again.");
		}
	}

	// deposit an amount of money in the account
	public static void depositMoney() {
		try {
			System.out.println("How much money do you want to deposit?");
			double sumToDeposit = input.nextDouble();
			if (sumToDeposit > 0) { // check if the sum is not negative
				balanceList[indexUserInList] += sumToDeposit;
				System.out.printf("You have deposited %.2f. Your current balance is %.2f.\n", sumToDeposit,
						balanceList[indexUserInList]);
				// store transactions
				storeUserTransactions("Deposit", sumToDeposit, balanceList[indexUserInList]);
			} else {
				System.out.println("The sum you are trying to deposit is not valid. Please try again.\n");
			}
		} catch (InputMismatchException e) {
			input.nextLine();
			System.out.println("You did not type a valid sum. Please try again.\n");
		}
	}

	// change the user's password
	public static void changePassWord() {
		System.out.println("Type your current password.");
		String oldPassword = input.next();
		// if current password is valid
		if (oldPassword.equals(passwordList[indexUserInList])) {
			// make valid only 4 digit pass
			System.out.println("Type a new 4 digit password.");
			String newPassword1 = input.next();
			if (isPasswordFormatValid(newPassword1)) {
				System.out.println("Type the 4 digit password again.");
				String newPassword2 = input.next();
				if (newPassword1.equals(newPassword2)) {
					passwordList[indexUserInList] = newPassword2;
					System.out.println("Your password has been saved.");
				} else {
					System.out.println("The two passwords do not match. Please try again.\n");
				}
			} else {
				System.out.println("You did not type a valid 4 digit password. Try again.\n");
			}
		} else {
			System.out.println("You did not type the correct password. Try again.\n");
		}
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

	public static void shutDownAtm() {
		if (user.equalsIgnoreCase("admin")) {
			System.out.println("ATM is shutting down.");
			isAtmTurnedOff = true;
		} else {
			System.out.println("You do not have permition to perform this command.");
		}
	}

	// run ATM menu
	public static void runAtmCommands() {
		if (isUserLoggedIn) {
			String nextCommand = "N";
			do {
				displayOptionsMenu();
				optionsMenu();

				if (!isAtmTurnedOff) {
					do {
						System.out.println("Do you want to continue? Type \"Y\"  for yes or \"N\" for no.");
						nextCommand = input.next().toUpperCase();
					} while (!"YN".contains(nextCommand));
					if (nextCommand.equals("N")) {
						System.out.println("Have a good day!");
						isUserLoggedIn = false;
					}
				}

			} while (nextCommand.equals("Y"));
		}
	}

	// admin can add account - bonus
	public static void addNewUser() {
		if (user.equalsIgnoreCase("admin")) {
			System.out.println("Type a new account name:");
			String name = input.next();
			if (!checkUser(name)) {
				System.out.println("Type a 4 digit password for the new account:");
				String password = input.next();
				if (isPasswordFormatValid(password)) {
					for (int i = 0; i < userList.length; i++) {
						if (userList[i] == null) {
							userList[i] = name;
							passwordList[i] = password;
							activeAccountsList[i] = true;
							System.out.println("The account has been added.");
							return;
						}
					}
					System.out.println("System overload!");
				}
			} else {
				System.out.println("The name already exists. Please choose another one.");
			}
		} else {
			System.out.println("You do not have permition to perform this command.");
		}
	}

	public static void reActivateAccount() {
		if (user.equalsIgnoreCase("admin")) {
			System.out.println("Type the name of the account you want to make active.");
			String account = input.next();
			if (checkUser(account)) {
				if (activeAccountsList[indexUserInList]) {
					System.out.println("The account is already active.");
				} else {
					activeAccountsList[indexUserInList] = true;
					System.out.printf("The account %s is now active!\n", user);
				}
			} else {
				System.out.println("The account name is not valid.");
			}
		} else {
			System.out.println("You do not have permition to perform this command.");
		}
	}

	public static void storeUserTransactions(String transactionType, double sum, double balance) {
		for (int i = 0; i < userTransactions[indexUserInList].length; i++) {
			if (userTransactions[indexUserInList][i] == null) {
				userTransactions[indexUserInList][i] = String.format("%s , Sum: %.2f , Balance: %.2f", transactionType,
						sum, balance);
				break;
			}
		}

	}

	// transactions - bonus
	public static void getUserTransactions() {
		for (int i = 0; i < userTransactions[indexUserInList].length; i++) {
			if (userTransactions[indexUserInList][i] != null) {
				System.out.println(userTransactions[indexUserInList][i]);
			}
		}

	}
}
