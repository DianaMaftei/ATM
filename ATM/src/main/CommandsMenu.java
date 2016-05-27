package main;

import java.util.Scanner;

public class CommandsMenu {

	private static Scanner userInput = new Scanner(System.in);
	private static AccountService accountService;
	private static String optionUser;

	// bonus - freeze account if pass incorrect more than 3 times
	public static void displayUserMenu() {
		System.out.println("Choose what you want to do. Type the corresponding number: ");
		System.out.println("\t (1) Check balance.");
		System.out.println("\t (2) Deposit money.");
		System.out.println("\t (3) Withdraw money.");
		System.out.println("\t (4) Check past transactions.");
		System.out.println("\t (5) Change password.");
	}

	public static void displayAdminMenu() {
		System.out.println("Choose what you want to do. Type the corresponding number: ");
		System.out.println("\t (1) Shut down machine for maintenance.");
		System.out.println("\t (2) Add new user.");
		System.out.println("\t (3) Make an inactive account active.");
	}

	// perform commands based on the user inputed option
	public static void optionsMenuUser() {
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

	public static void optionsMenuAdmin(){
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