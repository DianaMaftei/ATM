
public class Verificari {
	public boolean verificaDubluExemplar(Carte carte1, Carte carte2){
		return carte1.equals(carte2);
	}
	
	public double verificaGrosime(Carte carte1, Carte carte2){
		return Math.max(carte1.getNumarDePagini(), carte2.getNumarDePagini());
		
	}
}
