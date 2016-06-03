package entity;

import java.util.ArrayList;

public class Materie {
	private String nume;
	private String profesor;
	private ArrayList<Nota> note;
	private ArrayList<Absenta> absente;
	
	public Materie(String nume, String profesor, ArrayList<Nota> note, ArrayList<Absenta> absente) {
		this.nume = nume;
		this.profesor = profesor;
		this.note = note;
		this.absente = absente;
	}

	public String getNume() {
		return nume;
	}

	public void setNume(String nume) {
		this.nume = nume;
	}

	public String getProfesor() {
		return profesor;
	}

	public void setProfesor(String profesor) {
		this.profesor = profesor;
	}

	public ArrayList<Nota> getNote() {
		return note;
	}


	public ArrayList<Absenta> getAbsente() {
		return absente;
	}

}
