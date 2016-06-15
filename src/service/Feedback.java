package service;

public class Feedback {
	
	//TODO rewrite - consider HashMap with keyword and string message to print
	//eg: INCORRECT_PASS - "Incorrect password, please try again."
	
	//TODO move all user interaction here?
	
	public void displayMessageToUser(String message, String typeOfMessage){
		if ("out".equals(typeOfMessage)){
			System.out.println(message);
		}else if("err".equals(typeOfMessage)){
			System.err.println(message);
		}
		
	}
	
	public void displayAdminMenu() {
		displayMessageToUser("Choose what you want to do. Type the corresponding number: \n\n"
				+ "\t (1) Shut down machine for maintenance. \n"
				+ "\t (2) Add new user. \n"
				+ "\t (3) Make an inactive account active. \n"
				+ "\t (4) Exit. \n", "out");
	}
	
	public void displayClientMenu() {
		displayMessageToUser("Choose what you want to do. Type the corresponding number: \n\n"
				+ "\t (1) Check balance. \n" 
				+ "\t (2) Deposit money. \n" 
				+ "\t (3) Withdraw money. \n"
				+ "\t (4) Check past transactions. \n" 
				+ "\t (5) Change password. \n"
				+ "\t (6) Exit. \n", "out");
	}
}
