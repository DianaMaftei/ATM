
public class Carte {
	private String titlu;
	private String autor;
	private String editura;
	private int numarDePagini;
	
	public Carte(String titlu, String autor, String editura, int numarDePagini) {
		super();
		this.titlu = titlu;
		this.autor = autor;
		this.editura = editura;
		this.numarDePagini = numarDePagini;
	}

	public String getTitlu() {
		return titlu;
	}

	public String getAutor() {
		return autor;
	}

	public String getEditura() {
		return editura;
	}

	public int getNumarDePagini() {
		return numarDePagini;
	}

	
}
