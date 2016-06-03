package entity;

public abstract class User {
	protected String nume;
	protected String userName;
	protected String parola;
	
	public User(String nume, String userName, String parola) {
		this.nume = nume;
		this.userName = userName;
		this.parola = parola;
	}

	public String getNume() {
		return nume;
	}

	public String getParola() {
		return parola;
	}

	public String getUserName() {
		return userName;
	}
	
	
	
	
}
