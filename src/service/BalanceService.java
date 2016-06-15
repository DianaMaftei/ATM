package service;

import java.util.InputMismatchException;
import java.util.Scanner;

import entity.Transaction;

public class BalanceService {

	private Scanner userInput = new Scanner(System.in);
	private Feedback feedback = new Feedback();

	public void checkBalance() {
		feedback.displayMessageToUser(String.format("Your balance is: %.2f.\n", Login.currentClient.getBalance()),
				"out");
	}

	public void withdrawMoney() {
		if (Login.currentClient.getBalance() <= 0.0) {
			feedback.displayMessageToUser("You cannot withdraw money at this time. Your balance is negative.", "err");
			return;
		}
		try {
			feedback.displayMessageToUser("How much money do you want to withdraw?", "out");
			double sumToWithdraw = userInput.nextDouble();

			if (sumToWithdraw > 0) { // check if the sum is not negative
				if ((Login.currentClient.getBalance() - sumToWithdraw) >= 0) {
					Login.currentClient.setBalance(Login.currentClient.getBalance() - sumToWithdraw);
					feedback.displayMessageToUser(
							String.format("You have withdrawn %.2f. Your current balance is %.2f.\n", sumToWithdraw,
									Login.currentClient.getBalance()),
							"out");
					// store transactions
					Login.currentClient.storeUserTransactions(Login.currentClient, "Withdrew", sumToWithdraw,
							Login.currentClient.getBalance());

				} else {
					feedback.displayMessageToUser(
							"The sum you are trying to withdraw is larger than your current balance.\n", "err");
				}
			} else {
				feedback.displayMessageToUser("The sum you are trying to withdraw is not valid. Please try again.\n",
						"err");
			}
		} catch (InputMismatchException e) {
			userInput.nextLine();
			feedback.displayMessageToUser("You did not type a valid sum. Please try again.", "err");
		}

	}

	public void depositMoney() {
		try {
			feedback.displayMessageToUser("How much money do you want to deposit?", "out");
			double sumToDeposit = userInput.nextDouble();
			if (sumToDeposit > 0) { // check if the sum is not negative
				Login.currentClient.setBalance(Login.currentClient.getBalance() + sumToDeposit);
				feedback.displayMessageToUser(String.format("You have deposited %.2f. Your current balance is %.2f.\n",
						sumToDeposit, Login.currentClient.getBalance()), "out");
				// store transactions
				Login.currentClient.storeUserTransactions(Login.currentClient, "Deposited", sumToDeposit,
						Login.currentClient.getBalance());
			} else {
				feedback.displayMessageToUser("The sum you are trying to deposit is not valid. Please try again.\n",
						"err");
			}
		} catch (InputMismatchException e) {
			userInput.nextLine();
			feedback.displayMessageToUser("You did not type a valid sum. Please try again.\n", "err");
		}

	}

	public void getUserTransactions() {
		for (Transaction trans : Login.currentClient.getTransactions()) {
			feedback.displayMessageToUser(trans.toString(), "out");
		}
	}

}
