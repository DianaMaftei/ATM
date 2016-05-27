package main;

import java.util.Scanner;

public class CommandsMenu {

	private Scanner userInput = new Scanner(System.in);
	private AccountService accountService = new AccountService();
	private String optionUser;

	public void displayUserMenu() {
		System.out.println();
		System.out.println("Choose what you want to do. Type the corresponding number: \n\n"
				+ "\t (1) Check balance. \n"
				+ "\t (2) Deposit money. \n"
				+ "\t (3) Withdraw money. \n"
				+ "\t (4) Check past transactions. \n"
				+ "\t (5) Change password.");
	}

	public void displayAdminMenu() {
		System.out.println();
		System.out.println("Choose what you want to do. Type the corresponding number: \n\n"
				+ "\t (1) Shut down machine for maintenance. \n"
				+ "\t (2) Add new user. \n"
				+ "\t (3) Make an inactive account active.");
	}

	// perform commands based on the user inputed option
	public void optionsMenuUser() {
		optionUser = userInput.next().toUpperCase();
		switch (optionUser) {
		case "1":
			accountService.checkBalance();
			break;
		case "2":
			accountService.depositMoney();
			break;
		case "3":
			accountService.withdrawMoney();
			break;
		case "4":
			accountService.getUserTransactions();
			break;
		case "5":
			accountService.changePassWord();
			break;
		default:
			System.out.println("Please type a valid command.");
			break;
		}
	}

	public void optionsMenuAdmin(){
		optionUser = userInput.next().toUpperCase();
		switch (optionUser){
		case "1":
			accountService.shutDownAtm();
			break;
		case "2":
			accountService.addNewUser();
			break;
		case "3":
			accountService.reactivateAccount();
			break;
		default:
			System.out.println("Please type a valid command.");
			break;
		}
}

}