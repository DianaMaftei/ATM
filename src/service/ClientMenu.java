package service;

import java.util.Scanner;

public class ClientMenu {

	private Scanner userInput = new Scanner(System.in);
	private BalanceService balanceManagement = new BalanceService();
	private AccountService accountManagement = new AccountService();
	private String optionUser;
	private Feedback feedback = new Feedback();

	public void RunClientMenu() {
		feedback.displayClientMenu();
		
		optionUser = userInput.next();
		switch (optionUser) {
		case "1":
			balanceManagement.checkBalance();
			break;
		case "2":
			balanceManagement.depositMoney();
			break;
		case "3":
			balanceManagement.withdrawMoney();
			break;
		case "4":
			balanceManagement.getUserTransactions();
			break;
		case "5":
			accountManagement.changePassWord();
			break;
		case "6":
			feedback.displayMessageToUser("Have a nice day!", "out");
			AtmMain.setUserLoggedIn(false);			
			break;
		default:
			System.out.println("Please type a valid command.");
			break;
		}
	}



}