package main;

import java.util.ArrayList;

public class Database {

	private ArrayList<Account> clientsList = new ArrayList<>();
	private ArrayList<Admin> adminsList = new ArrayList<>();

	public Database() {
		adminsList.add(new Admin("admin1", "1234"));
		clientsList.add(new Account("diana", "0000"));
		clientsList.add(new Account("anca", "1111"));
		clientsList.add(new Account("dorian", "999"));
	}

	public ArrayList<Account> getClientsList() {
		return clientsList;
	}

	public ArrayList<Admin> getAdminsList() {
		return adminsList;
	}
}
