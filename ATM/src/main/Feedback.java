package main;

public class Feedback {
	
	static final String CODE_SUCCESS = "SUCCESS";
	static final String CODE_USER = "WRONG_USER";
	static final String CODE_PASS = "INVALID_PASS";
	static final String CODE_FREEZE = "FREEZE";
	static final String CODE_FROZEN = "FROZEN";
	
	// display a message to the user based on the result of their input
	
	public String feedbackToUser(String code) {
		switch (code) {
		case CODE_SUCCESS:
			System.out.println("You have been successfully logged in.");
			break;

		case CODE_PASS:
			System.out.println("Incorrect password, please try again.");
			break;

		case CODE_USER:
			System.out.println("Invalid user, please try again.");
			break;

		case CODE_FREEZE:
			System.out.println("You have reached your maximum number of tries, your account has been frozen.");
			break;

		case CODE_FROZEN:
			System.out.println("Your account is frozen, contact your local bank.");
			break;

		default:
			break;
		}
		return CODE_SUCCESS;
	}

}
