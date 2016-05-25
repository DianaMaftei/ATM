
public class Verificari {
	
	
	public static boolean verificaDubluExemplar(Carte carte1, Carte carte2){
		return carte1 == carte2;
	}
	
	public static String verificaGrosime(Carte carte1, Carte carte2){
		if (carte1.getNumarDePagini() >= carte2.getNumarDePagini()){
			return carte1.getAutor();
		}
		return carte2.getAutor();
		
		
	}
}
