package service;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

public class Feedback {
	private Properties properties;
	private InputStream messagesStream;
	
	//TODO move all user interaction here?
	
	HashMap<String, String> messages = new HashMap<String, String>();
		
		
	public Feedback() {
		try {
			messagesStream = new FileInputStream("messagesToUser.properties");
			properties = new Properties();
			properties.load(messagesStream);
		} catch (Exception e) {
			System.out.println("Eeeek");
		}
		for (String key : properties.stringPropertyNames()) {
			messages.put(key, properties.getProperty(key));
		}
	}

	public void displayMessageToUser(String message){
		System.out.println(messages.get(message));
	}
	
	public void displayAdminMenu() {
		System.out.println("Choose what you want to do. Type the corresponding number: \n\n"
				+ "\t (1) Shut down machine for maintenance. \n"
				+ "\t (2) Add new user. \n"
				+ "\t (3) Make an inactive account active. \n"
				+ "\t (4) Exit. \n");
	}
	
	public void displayClientMenu() {
		System.out.println("Choose what you want to do. Type the corresponding number: \n\n"
				+ "\t (1) Check balance. \n" 
				+ "\t (2) Deposit money. \n" 
				+ "\t (3) Withdraw money. \n"
				+ "\t (4) Check past transactions. \n" 
				+ "\t (5) Change password. \n"
				+ "\t (6) Exit. \n");
	}
}
