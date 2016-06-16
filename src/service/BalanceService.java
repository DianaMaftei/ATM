package service;
/**
*
*@author diana.maftei[at]gmail.com
*/
import java.util.InputMismatchException;
import java.util.Scanner;

import entities.Transaction;
import userInteraction.UserInterface;

public class BalanceService {

	private Scanner userInput = new Scanner(System.in);
	private UserInterface feedback = new UserInterface();

	public void checkBalance() {
		System.out.println(String.format("Your balance is: %.2f.\n", Login.currentClient.getBalance()));
	}

	public void withdrawMoney() {
		if (Login.currentClient.getBalance() <= 0.0) {
			feedback.displayMessageToUser("NO_FUNDS");
			return;
		}
		try {
			feedback.displayMessageToUser("WITHDRAW_AMOUNT");
			double sumToWithdraw = userInput.nextDouble();

			if (sumToWithdraw > 0) { // check if the sum is not negative
				if ((Login.currentClient.getBalance() - sumToWithdraw) >= 0) {
					Login.currentClient.setBalance(Login.currentClient.getBalance() - sumToWithdraw);
					System.out.println(String.format("You have withdrawn %.2f. Your current balance is %.2f.\n",
							sumToWithdraw, Login.currentClient.getBalance()));
					// store transactions
					Login.currentClient.storeUserTransactions(Login.currentClient, "Withdrew", sumToWithdraw,
							Login.currentClient.getBalance());
				} else {
					feedback.displayMessageToUser("INSUFFICIENT_FUNDS");
				}
			} else {
				feedback.displayMessageToUser("INVALID_AMOUNT");
			}
		} catch (InputMismatchException e) {
			userInput.nextLine();
			feedback.displayMessageToUser("INVALID_AMOUNT");
		}
	}

	public void depositMoney() {
		try {
			feedback.displayMessageToUser("DEPOSIT_AMOUNT");
			double sumToDeposit = userInput.nextDouble();
			if (sumToDeposit > 0) { // check if the sum is not negative
				Login.currentClient.setBalance(Login.currentClient.getBalance() + sumToDeposit);
				System.out.println(String.format("You have deposited %.2f. Your current balance is %.2f.\n",
						sumToDeposit, Login.currentClient.getBalance()));
				// store transactions
				Login.currentClient.storeUserTransactions(Login.currentClient, "Deposited", sumToDeposit,
						Login.currentClient.getBalance());
			} else {
				feedback.displayMessageToUser("INVALID_AMOUNT");
			}
		} catch (InputMismatchException e) {
			userInput.nextLine();
			feedback.displayMessageToUser("INVALID_AMOUNT");
		}
	}

	public void getUserTransactions() {
		for (Transaction userTransaction : Login.currentClient.getTransactions()) {
			System.out.println(userTransaction.toString());
		}
	}

}
