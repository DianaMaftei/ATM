package entity;

import java.util.ArrayList;

public class Student extends User{
	
	private ArrayList<Materie> materii;

	public Student(String nume, String userName, String parola) {
		super(nume, userName, parola);
		this.materii = new ArrayList<>();
	}

	public ArrayList<Materie> getMaterii() {
		return materii;
	}

	public void setMaterii(ArrayList<Materie> materii) {
		this.materii = materii;
	}

	@Override
	public String toString() {
		return this.nume;
	}

	
	
	
}
