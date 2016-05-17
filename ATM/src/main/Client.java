package main;

public class Client {

	// final variable for client name, which should not be changed
	private final String CLIENT_NAME;
	private String clientPassword;
	private double clientBalance;

	// constructor

	public Client(String clientName, String clientAccount, double clientBalance) {
		super();
		this.CLIENT_NAME = clientName;
		this.clientPassword = clientAccount;
		this.clientBalance = clientBalance;
	}

	// setters and getters

	public String getClientName() {
		return CLIENT_NAME;
	}

	public String getClientPassword() {
		return clientPassword;
	}

	private void setClientPassword(String clientPassword) {
		this.clientPassword = clientPassword;
	}

	public double getClientBalance() {
		return clientBalance;
	}

	private void setClientBalance(double clientBalance) {
		this.clientBalance = clientBalance;
	}

	// withdraw cash from client account
	public void withdrawMoney(double sumToWithdraw) {
		if (sumToWithdraw > 0) { // check if the sum is not negative
			if ((clientBalance - sumToWithdraw) > 0) {
				setClientBalance(clientBalance - sumToWithdraw);
			} else {
				System.out.println("The sum you are trying to withdraw is larger than your current balance.");
			}
		} else {
			System.out.println("Please insert a valid sum.");
		}
	}

	// deposit money into client account
	public void depositMoney(double sumToDeposit) {
		if (sumToDeposit > 0) { // check if the sum is not negative
			setClientBalance(sumToDeposit + clientBalance);
		} else {
			System.out.println("Please insert a valid sum.");
		}
	}

}
