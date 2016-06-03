package Service;

import entity.Database;

public class eCatalogMain {
	public static Database currentDatabase;
	public static StudentService studentService;
	public static ProfesorService profesorService;

	public static void main(String[] args) {
		currentDatabase = new Database();
		Login meniu = new Login();
		meniu.afiseazaMeniuLogare();
		meniu.doLogin();
		
		
/*		studentService = new StudentService();
		studentService.afiseazaNote("mate");
		studentService.afiseazaAbsente("mate");
		studentService.afiseazaMedie("mate");
		
		studentService.afiseazaNote("desen");
		studentService.afiseazaAbsente("desen");
		studentService.afiseazaMedie("desen");*/
		
		profesorService = new ProfesorService();
		//profesorService.adaugaNota();
		profesorService.motiveazaAbsenta();

	}

}
