package main;

import java.util.ArrayList;

public class Database {

	public static ArrayList<Account> accounts;
	public static ArrayList<Transaction> transactions;
	public static String test;
	
	{
		accounts.add(new Account("admin1", "1234", "admin"));
		accounts.add(new Account("diana", "0000", "user"));
		accounts.add(new Account("anca", "1111", "user"));
		
	}
}
