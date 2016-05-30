package main;

import java.util.Date;
import java.text.*;

public class Transaction {
	private Date transactionDate = new Date();
	private String date;
	private String transactionType;
	private double amount;
	private double balance;
	
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd - HH:mm:ss");
	
	public Transaction(String transactionType, double amount, double balance) {
		this.date = dateFormat.format(transactionDate);
		this.transactionType = transactionType;
		this.amount = amount;
		this.balance = balance;
	}
	
	@Override
	public String toString(){
		return String.format("Date: %s - , : %s, %.2f. Balance remaining: %.2f", date, transactionType, amount, balance);
	}

}
