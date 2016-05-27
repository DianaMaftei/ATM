package main;

import java.util.Scanner;

public class CommandsMenu {
	
	private Scanner userInput = new Scanner(System.in);
	
	// bonus - freeze account if pass incorrect more than 3 times
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


}
