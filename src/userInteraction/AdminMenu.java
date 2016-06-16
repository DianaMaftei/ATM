package userInteraction;

import service.AdminService;
import service.AtmMain;

/**
*
*@author diana.maftei[at]gmail.com
*/
public class AdminMenu {
	private AdminService adminControls = new AdminService();
	private UserInterface feedback = new UserInterface();
	
	public void runAdminMenu(String optionUser) {
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
