package main;

import java.util.InputMismatchException;
import java.util.Scanner;

public class AccountService {
	
	private static Scanner userInput = new Scanner(System.in);
	private static Login localLogin = new Login();

	public static void addNewUser() {
		if ("admin".equals(Login.loggedIn.getAccountType())) {
			System.out.println("Type a new account name:");
			String newAccountName = userInput.next();
			if (localLogin.checkIfUserExists(newAccountName) == null) {
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

	public static void changePassWord() {
		System.out.println("Type your current password.");
		String oldPassword = userInput.next();
		// if current password is valid
		if (oldPassword.equals(Login.loggedIn.getPassword())) {
			// make valid only 4 digit pass
			System.out.println("Type a new 4 digit password.");
			String newPassword1 = userInput.next();
			if (localLogin.isPasswordFormatValid(newPassword1)) {
				System.out.println("Type the 4 digit password again.");
				String newPassword2 = userInput.next();
				if (newPassword1.equals(newPassword2)) {
				Login.loggedIn.setPassword(newPassword2);
					System.out.println("Your password has been saved.");
				} else {
					System.out.println("The two passwords do not match. Please try again.\n");
				}
			} 
		} else {
			System.out.println("You did not type the correct password. Try again.\n");
		}
	}

	public static void reactivateAccount() {
		if ("admin".equals(Login.loggedIn.getAccountType())) {
			System.out.println("Type the name of the account you want to make active.");
			String accountToMakeValid = userInput.next();
			Account accountToReactivate = localLogin.checkIfUserExists(accountToMakeValid);
			if (accountToReactivate != null) {
				if (accountToReactivate.isActiveAccount()) {
					System.out.println("The account is already active.");
				} else {
					accountToReactivate.setActiveAccount(true);
					System.out.printf("The account %s is now active!\n", accountToReactivate.getName());
				}
			} else {
				System.out.println("The account name is not valid.");
			}
		} else {
			System.out.println("You do not have permition to perform this command.");
		}
	}

	public static void freezeAccount(Account accountToFreeze) {
		// activeAccountsList[indexUserInList] = false;
		accountToFreeze.setActiveAccount(false);
	}

	public static void checkBalance() {
		System.out.printf("Your balance is: %.2f.\n", Login.loggedIn.getBalance());
	}
	
	public static void withdrawMoney(){
		if(Login.loggedIn.getBalance() <= 0.0){
			System.out.println("You cannot withdraw money.");
			return;
		}
		try {
			System.out.println("How much money do you want to withdraw?");
			double sumToWithdraw = userInput.nextDouble();

			if (sumToWithdraw > 0) { // check if the sum is not negative
				if ((Login.loggedIn.getBalance() - sumToWithdraw) > 0) {
					Login.loggedIn.setBalance(Login.loggedIn.getBalance() - sumToWithdraw);
					System.out.printf("You have withdrawn %.2f. Your current balance is %.2f.\n", sumToWithdraw,
							Login.loggedIn.getBalance());
					// store transactions
					storeUserTransactions("Withdraw", sumToWithdraw, Login.loggedIn.getBalance());

				} else {
					System.out.println("The sum you are trying to withdraw is larger than your current balance.\n");
				}
			} else {
				System.out.println("The sum you are trying to withdraw is not valid. Please try again.\n");
			}
		} catch (InputMismatchException e) {
			userInput.nextLine();
			System.out.println("You did not type a valid sum. Please try again.");
		}
		
	}
	
	public static void depositMoney(){
		try {
			System.out.println("How much money do you want to deposit?");
			double sumToDeposit = userInput.nextDouble();
			if (sumToDeposit > 0) { // check if the sum is not negative
				Login.loggedIn.setBalance(Login.loggedIn.getBalance() + sumToDeposit);
				System.out.printf("You have deposited %.2f. Your current balance is %.2f.\n", sumToDeposit,
						Login.loggedIn.getBalance());
				// store transactions
				storeUserTransactions("Deposit", sumToDeposit, Login.loggedIn.getBalance());
			} else {
				System.out.println("The sum you are trying to deposit is not valid. Please try again.\n");
			}
		} catch (InputMismatchException e) {
			userInput.nextLine();
			System.out.println("You did not type a valid sum. Please try again.\n");
		}

	}
	
	public static void storeUserTransactions(String typeOfTransaction, double sumTransacted, double currentBalance){
		Transaction newTransaction = new Transaction(typeOfTransaction, sumTransacted, currentBalance);
		Login.loggedIn.getTransactions().add(newTransaction);
	}
	
	public static void getUserTransactions(){
		for(Transaction trans : Login.loggedIn.getTransactions()){
			System.out.println(trans.toString());
		}
	}
	
	public static void shutDownAtm() {
		if ("admin".equals(Login.loggedIn.getAccountType())) {
			System.out.println("ATM is shutting down.");
			AtmMain.isAtmTurnedOff = true;
		} else {
			System.out.println("You do not have permition to perform this command.");
		}
	}


}




