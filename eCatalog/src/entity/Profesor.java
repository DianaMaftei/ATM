package entity;

public class Profesor extends User{
	public String materie;

	public Profesor(String nume, String userName, String parola, String materie) {
		super(nume, userName, parola);
		this.materie = materie;
	}

	public String getMaterie() {
		return materie;
	}

	public void setMaterie(String materie) {
		this.materie = materie;
	}
	
	

}
