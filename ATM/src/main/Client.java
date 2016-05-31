package main;

import java.util.ArrayList;

public class Client {
	private String name;
	private String pinNo;
	private double balance;
	private boolean activeAccount;
	private ArrayList<Transaction> transactionsList;

	public Client(String name, String pinNo) {
		this.name = name;
		this.pinNo = pinNo;
		this.balance = 0.0;
		this.activeAccount = true;
		this.transactionsList = new ArrayList<>();
	}

	public String pinNo() {
		return pinNo;
	}

	public void pinNo(String pinNo) {
		this.pinNo = pinNo;
	}

	public double getBalance() {
		return balance;
	}
	
	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getName() {
		return name;
	}

	public boolean isActiveAccount() {
		return activeAccount;
	}

	public void setActiveAccount(boolean activeAccount) {
		this.activeAccount = activeAccount;
	}

	public void storeUserTransactions(String typeOfTransaction, double sumTransacted, double currentBalance){
		Transaction newTransaction = new Transaction(typeOfTransaction, sumTransacted, currentBalance);
		Login.currentClient.getTransactions().add(newTransaction);  
	}

	public ArrayList<Transaction> getTransactions() {
		return transactionsList;
	}

}
