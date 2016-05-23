
public class Operatii {
	
	public float adunare(NumarComplex numar1, NumarComplex numar2){
		return numar1.getReal() + numar2.getReal() + numar1.getImaginar() + numar2.getImaginar(); 
	}
	
	public float inmultire(NumarComplex numar1, NumarComplex numar2){

		float first = numar1.getReal() * numar2.getReal();
		float outer = numar1.getReal() * numar2.getImaginar();
		float inner = numar1.getImaginar() * numar2.getReal(); 
		float last = numar1.getImaginar() * numar2.getImaginar();
		return first + outer + inner + last;
		
				
	}

}
