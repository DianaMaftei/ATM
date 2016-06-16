package service;

import java.util.Scanner;

public class AdminMenu {
	
	//TODO consider polymorphism - one common menu for client and admin
	
	private Scanner userInput = new Scanner(System.in);
	private AdminService adminControls = new AdminService();
	private String optionUser;
	private Feedback feedback = new Feedback();
	
	public void RunAdminMenu() {
		feedback.displayAdminMenu();
		
		optionUser = userInput.next();
		switch (optionUser) {
		case "1":
			adminControls.shutDownAtm();
			break;
		case "2":
			adminControls.addNewUser();
			break;
		case "3":
			adminControls.reactivateAccount();
			break;
		case "4":
			feedback.displayMessageToUser("GOODBYE");
			AtmMain.setUserLoggedIn(false);
			break;
		default:
			feedback.displayMessageToUser("VALID_COMMAND");
			break;
		}
	}
}
