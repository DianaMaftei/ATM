package main;

import java.util.Scanner;

import main.Login;

public class Account {
	private String name;
	private String password;
	private double balance;
	private String accountType;
	private boolean activeAccount;

	private Scanner userInput = new Scanner(System.in);

	public Account(String name, String password, String accountType) {
		super();
		this.name = name;
		this.password = password;
		this.balance = 0.0;
		this.accountType = accountType;
		this.activeAccount = true;
	}

	public String getPassword() {
		return password;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getName() {
		return name;
	}

	public String getAccountType() {
		return accountType;
	}

	public boolean isActiveAccount() {
		return activeAccount;
	}

	public void setActiveAccount(boolean activeAccount) {
		this.activeAccount = activeAccount;
	}

	// TODO not sure if this works
	public void addUser(String name, String password) {
		if ("admin".equals(Login.loggedIn.getAccountType())) {
			System.out.println("Type a new account name:");
			String newAccountName = userInput.next();
			if (Login.checkIfUserExists(newAccountName) == null) {
				System.out.println("Type a 4 digit password for the new account:");
				String newAccountPassword = userInput.next();
				Database.accounts.add(new Account(newAccountName, newAccountPassword, "user"));
				System.out.println("The account has been added.");
			} else {
				System.out.println("The name already exists. Please choose another one.");
			}
		} else{
			System.out.println("You do not have permition to perform this command.");
		}
	}

	// TODO changePassword
	public void changePassword(String newPassword) {
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

	// TODO change state of account
	public void reactivateAccount() {
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

	public static void freezeAccount() {
		// activeAccountsList[indexUserInList] = false;
	}

	public static void checkBalance() {
		// System.out.printf("Your balance is: %.2f.\n",
		// balanceList[indexUserInList]);
	}

}
