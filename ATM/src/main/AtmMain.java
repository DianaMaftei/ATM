//modificat

package main;

import java.util.Scanner;

public class AtmMain {

	static final String[] userList = { "admin", "diana", "theo", "john", "charlie", "tim", "hannah" };
	static String[] passwordList = { "ad1720m1n", "d17n5a", "t853j", "j0hn0", "charl133", "t1mt1m", "hannah123" };
	static double[] balanceList = { 0, 3434.43, 4324.34, 6547.76, 30000.00, 2400.57, 1500.10 };
	static boolean[] activeAccountsList = { true, true, false, true, false, true, true };

	static final String COD_SUCCESS = "SUCCESS";
	static final String COD_USER = "WRONG_USER";
	static final String COD_PASS = "INVALID_PASS";
	static final String COD_FREEZE = "FREEZE";
	static final String COD_FROZEN = "FROZEN";
	static final int MAX_RETRIES = 3; // how many times the user can try to
										// login before his account gets frozen

	static int position = 0;

	static String user;
	static String pass;
	static double userBalance;

	static Scanner input = new Scanner(System.in);

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// display the login menu, which gets the user and password
		// check if the user and password are valid, if the account is active
		// and offer feedback to user
		// if doLogin is successful, show menu for account - displaybalance,
		// withdraw sum, deposit sum, etc
		doLogin();

		//
		// nu vrea sa continue, show menu for new user; else - show menu again
		// else -> try again - > start from prezinta optiune autentificare

	}

	// display login menu
	public static void readUserAndPass() {
		user = input.next().toLowerCase();
		pass = input.next();
	}

	// autentificare (optional - register) - doLogin cu parametri user and
	public static boolean doLogin() {
		// check user and password
		for (int i = MAX_RETRIES; i > 0; i--) {
			System.out.printf("Enter your username and password! You've got %d more tries!\n", i);
			readUserAndPass();
			String result = checkLoginData(user, pass);
			feedbackToUser(result);

			if (COD_SUCCESS.equals(result)) {
				return true;
			}
			if(COD_FROZEN.equals(result)){
				return false;
			}
		}
		//freeze account after too many tries
		feedbackToUser(COD_FREEZE);
		return false;
	}

	public static String checkLoginData(String user, String pass) {
		for (int i = 0; i < userList.length; i++) {
			if (userList[i].equals(user)) {
				if (passwordList[i].equals(pass)) {
					if (activeAccountsList[i] == false) {
						return COD_FROZEN;
					} else {
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

	}

	// display account options - with exist option if admin is logged in
	public static void displayOptionsMenu() {
		System.out.println("Choose what you want to do: ");
		System.out.printf("\t 1.Check balance.\n");
		System.out.printf("\t 2.Deposit money.\n");
		System.out.printf("\t 3.Withdraw money.\n");
		System.out.printf("\t 4.Change password.\n");
		if (user.equals("admin")) {
			System.out.printf("\t 5.Shut down machine for maintenance.\n");
		}
	}

	// run method based on user option
	public static void optionsMenu() {
		displayOptionsMenu();

		int optionUser = input.nextInt();
		switch (optionUser) {
		case 1:

			break;
		case 2:

			break;
		case 3:

			break;
		case 4:

			break;
		case 5:

			break;

		default:
			break;
		}

	}

	// vizualizare sold - from client class
	// retragere numerar - from client class

	public static void withdrawMoney(double sumToWithdraw) {
		if (sumToWithdraw > 0) { // check if the sum is not negative
			if ((userBalance - sumToWithdraw) > 0) {
				userBalance -= sumToWithdraw;
			} else {
				System.out.println("The sum you are trying to withdraw is larger than your current balance.");
			}
		} else {
			System.out.println("Please insert a valid sum.");
		}
	}

	// depunere numerar - from client class
	public static void depositMoney(double sumToDeposit) {
		if (sumToDeposit > 0) { // check if the sum is not negative
			userBalance += sumToDeposit;
		} else {
			System.out.println("Please insert a valid sum.");
		}
	}

	// schimbare parola

	public static void changePassWord() {
		System.out.println("Type your current password.");
		String oldPassword = input.next();
		// if current password is valid
		System.out.println("Type your new password.");
		String newPassword1 = input.next();
		System.out.println("Type new password again.");
		String newPassword2 = input.next();
		if (newPassword1.equals(newPassword2)) {
			passwordList[position] = newPassword2;
		}

	}

	// extras de cont bonus
	// blocheaza account if false
	// admin can unlock

	// exit - admin - mentenanta - CHECK WHAT USER IS LOGGED IN

}
