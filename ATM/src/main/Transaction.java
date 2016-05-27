package main;

//import java.util.Date;

public class Transaction {
	//private Date transactionDate;
	private String transactionType;
	private double amount;
	private double balance;
	
	public Transaction(String transactionType, double amount, double balance) {
		this.transactionType = transactionType;
		this.amount = amount;
		this.balance = balance;
	}
	
	public String toString(){
		return String.format("Transaction Type: %s, Amount: %.2f, Balance: %.2f", transactionType, amount, balance);
	}

}
