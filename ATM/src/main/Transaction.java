package main;

import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Transaction {
	private Date transactionDate;
	private String transactionType;
	private double ammount;
	private double balance;
	
	static Scanner input = new Scanner(System.in);
	
	public Transaction(Date transactionDate, String transactionType, double ammount, double balance) {
		super();
		this.transactionDate = transactionDate;
		this.transactionType = transactionType;
		this.ammount = ammount;
		this.balance = balance;
	}
	
	public void withdraw(Account account){
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
		
		account.getBalance();
		account.setBalance(1);
	}
	
	public void deposit(Account account){
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
		account.getBalance();
		account.setBalance(1);
	}
	
	//TODO store transactions
	
	//TODO get transactions
}
