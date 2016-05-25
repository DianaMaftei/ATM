
public class ExercisesMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
/*		NumarComplex numarUnu = new NumarComplex(1, 4);
		NumarComplex numarDoi = new NumarComplex(2, 3);
		
		Operatii prima = new Operatii();
		
		float adunare = prima.adunare(numarUnu, numarDoi);
		
		float inmultire = prima.inmultire(numarUnu, numarDoi);
		
		System.out.println(adunare);
		System.out.println(inmultire);*/
		
		//////////////////////////////////////////////////////////////////////////////////
		
		
		Carte fave = new Carte("HHGTTG", "Douglas Adams", "no idea", 800);	
		Carte secondFave = new Carte("Sth", "someone", "Polirom", 300);
		
		System.out.println(Verificari.verificaDubluExemplar(fave, secondFave));
		
		Carte next = fave;
		System.out.println(Verificari.verificaDubluExemplar(fave, next));
		
		System.out.println(Verificari.verificaGrosime(fave, secondFave));
		
	}

}
