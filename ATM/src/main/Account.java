package main;

import java.util.ArrayList;

public class Account {
	private String name;
	private String password;
	private double balance;
	private boolean activeAccount;
	private ArrayList<Transaction> transactionsList;
	private Login login = new Login(); //????????????????????????????????????????

	public Account(String name, String password) {
		this.name = name;
		this.password = password;
		this.balance = 0.0;
		this.activeAccount = true;
		this.transactionsList = new ArrayList<>();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
		login.getUserCurrentlyLoggedIn().getTransactions().add(newTransaction);  //??????????????????????????????
	}

	public ArrayList<Transaction> getTransactions() {
		return transactionsList;
	}

}
