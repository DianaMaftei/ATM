package service;
/**
*
*@author diana.maftei[at]gmail.com
*/
import java.util.ArrayList;

import entities.Admin;
import entities.Client;

public class Database {

	private ArrayList<Client> clientsList = new ArrayList<>();
	private ArrayList<Admin> adminsList = new ArrayList<>();
	
	public Database(){
		initializeDatabase();
	}

	public void initializeDatabase() {
		adminsList.add(new Admin("admin1", "adm1nPass"));
		clientsList.add(new Client("diana", "0000"));
		clientsList.add(new Client("anca", "1111"));
		clientsList.add(new Client("dorian", "6666"));		
	}

	public ArrayList<Client> getClientsList() {
		return clientsList;
	}

	public ArrayList<Admin> getAdminsList() {
		return adminsList;
	}
}
