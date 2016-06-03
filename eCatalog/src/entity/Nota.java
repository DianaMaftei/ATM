package entity;

public class Nota {
	private int valoare;
	private String data;
	
	public Nota(int valoare, String data) {
		this.valoare = valoare;
		this.data = data;
	}
	
	public int getValoare() {
		return valoare;
	}
	
	public void setValoare(int valoare) {
		this.valoare = valoare;
	}
	
	public String getData() {
		return data;
	}

	public String toString(){
		return "" + data + ": " + valoare;
	}
}
