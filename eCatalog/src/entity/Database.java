package entity;

import java.util.ArrayList;

import Service.eCatalogMain;

public class Database {

	private ArrayList<User> useri;

	public Database() {

		useri = new ArrayList<>();

		initDatabase();

	}

	public ArrayList<User> getUseri() {
		return useri;
	}

	public void initDatabase() {
		Student diana = new Student("Maftei Diana", "dianaM", "0000");
		useri.add(diana);
		Student anca = new Student("Stefanescu Anca", "ancaS", "0000");
		useri.add(anca);
		Profesor dorian = new Profesor("Pavus Dorian", "dodo", "1111", "DESEN");
		useri.add(dorian);

		ArrayList<Materie> materiiDiana = new ArrayList<Materie>();

		materiiDiana.add(new Materie("MATE", "Stefanescu Liviu", new ArrayList<>(), new ArrayList<>()));
		materiiDiana.add(new Materie("SPORT", "Vasiliu Cornel", new ArrayList<>(), new ArrayList<>()));
		materiiDiana.add(new Materie("DESEN", "Popescu Irina", new ArrayList<>(), new ArrayList<>()));

		diana.setMaterii(materiiDiana);

		ArrayList<Materie> materiiAnca = new ArrayList<Materie>();
		
		materiiAnca.add(new Materie("ROMANA", "Spiridon Elena", new ArrayList<>(), new ArrayList<>()));
		materiiAnca.add(new Materie("ENGLEZA", "Buciu Raluca", new ArrayList<>(), new ArrayList<>()));
		materiiAnca.add(new Materie("DESEN", "Popescu Dragos", new ArrayList<>(), new ArrayList<>()));
		materiiAnca.add(new Materie("ISTORIE", "Tanasescu Virginia", new ArrayList<>(), new ArrayList<>()));
		
		anca.setMaterii(materiiAnca);

		diana.getMaterii().get(0).getAbsente().add(new Absenta("1/06"));
		diana.getMaterii().get(0).getAbsente().add(new Absenta("8/06"));
		diana.getMaterii().get(0).getAbsente().add(new Absenta("10/06"));
		diana.getMaterii().get(0).getAbsente().get(2).setAbsent(false);
		diana.getMaterii().get(0).getNote().add(new Nota(5, "25/05"));
		diana.getMaterii().get(0).getNote().add(new Nota(8, "2/06"));
		
		diana.getMaterii().get(1).getAbsente().add(new Absenta("1/06"));
		diana.getMaterii().get(1).getNote().add(new Nota(9, "10/05"));
		diana.getMaterii().get(1).getNote().add(new Nota(8, "5/02"));
		
		diana.getMaterii().get(2).getNote().add(new Nota(10, "25/05"));
		diana.getMaterii().get(2).getNote().add(new Nota(8, "16/04"));
		diana.getMaterii().get(2).getNote().add(new Nota(10, "10/05"));
		diana.getMaterii().get(2).getNote().add(new Nota(9, "1/06"));
		
		anca.getMaterii().get(0).getAbsente().add(new Absenta("1/06"));
		anca.getMaterii().get(0).getNote().add(new Nota(10, "22/05"));
		anca.getMaterii().get(0).getNote().add(new Nota(10, "2/06"));
		
		anca.getMaterii().get(1).getAbsente().add(new Absenta("7/05"));
		anca.getMaterii().get(1).getNote().add(new Nota(9, "5/03"));
		anca.getMaterii().get(1).getNote().add(new Nota(10, "30/04"));
		
		anca.getMaterii().get(2).getAbsente().add(new Absenta("1/06"));
		anca.getMaterii().get(2).getAbsente().add(new Absenta("5/05"));
		anca.getMaterii().get(2).getNote().add(new Nota(6, "21/03"));
				
		anca.getMaterii().get(3).getNote().add(new Nota(10, "4/03"));
		anca.getMaterii().get(3).getNote().add(new Nota(10, "19/04"));
		anca.getMaterii().get(3).getNote().add(new Nota(10, "27/05"));
		
	}

	public ArrayList<Student> getStudentsList(){
		ArrayList<Student> listaStudenti = new ArrayList<Student>();
		for(int i = 0; i < eCatalogMain.currentDatabase.getUseri().size(); i++){
			if(eCatalogMain.currentDatabase.getUseri().get(i) instanceof Student){
				listaStudenti.add((Student) eCatalogMain.currentDatabase.getUseri().get(i));
			}
		}
		return listaStudenti;
	}
}
