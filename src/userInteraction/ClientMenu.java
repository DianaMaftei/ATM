package userInteraction;

import service.AccountService;
import service.AtmMain;
import service.BalanceService;

/**
*
*@author diana.maftei[at]gmail.com
*/
public class ClientMenu {
	private BalanceService balanceManagement;
	private AccountService accountManagement;
	private UserInterface userInterface;

	public ClientMenu() {
		balanceManagement = new BalanceService();
		accountManagement = new AccountService();
		userInterface = new UserInterface();
	}

	public void runClientMenu(String userOption) {
		switch (userOption) {
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
			accountManagement.changeUserPin();
			break;
		case "6":
			userInterface.displayMessageToUser("GOODBYE");
			AtmMain.setUserLoggedIn(false);
			break;
		default:
			userInterface.displayMessageToUser("VALID_COMMAND");
			break;
		}
	}
}