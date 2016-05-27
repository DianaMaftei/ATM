package main;

public class Feedback {
	
	static final String COD_SUCCESS = "SUCCESS";
	static final String COD_USER = "WRONG_USER";
	static final String COD_PASS = "INVALID_PASS";
	static final String COD_FREEZE = "FREEZE";
	static final String COD_FROZEN = "FROZEN";
	
	// display a message to the user based on the result of their input
	
	public String feedbackToUser(String code) {
		switch (code) {
		case COD_SUCCESS:
			System.out.println("You have been successfully logged in.");
			break;

		case COD_PASS:
			System.out.println("Incorrect password, please try again.");
			break;

		case COD_USER:
			System.out.println("Invalid user, please try again.");
			break;

		case COD_FREEZE:
			System.out.println("You have reached your maximum number of tries, your account has been frozen.");
			break;

		case COD_FROZEN:
			System.out.println("Your account is frozen, contact your local bank.");
			break;

		default:
			break;
		}
		return COD_SUCCESS;
	}

}
