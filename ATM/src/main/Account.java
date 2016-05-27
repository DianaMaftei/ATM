package main;

import java.util.ArrayList;
import java.util.Scanner;

import main.Login;

public class Account {
	private String name;
	private String password;
	private double balance;
	private String accountType;
	private boolean activeAccount;
	private ArrayList<Transaction> transactions;


	public Account(String name, String password, String accountType) {
		this.name = name;
		this.password = password;
		this.balance = 0.0;
		this.accountType = accountType;
		this.activeAccount = true;
		this.transactions = new ArrayList<>();
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

	public String getAccountType() {
		return accountType;
	}

	public boolean isActiveAccount() {
		return activeAccount;
	}

	public void setActiveAccount(boolean activeAccount) {
		this.activeAccount = activeAccount;
	}

	public ArrayList<Transaction> getTransactions() {
		return transactions;
	}

}
