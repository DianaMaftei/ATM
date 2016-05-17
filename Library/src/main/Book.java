//mod book
package main;

public class Book {
	
	private final String NAME;
	private final String AUTHOR;
	private final String PUBLISHING_HOUSE;
	private final String ISBN;
	private final String YEAR;
	
	//exemplare existente in baza de date / disponibile pt imprumut
	private int existing;
	private int available;
		
	
	public Book(String name, String author, String publishingHouse, String isbn, String year) {
		super();
		this.NAME = name;
		this.AUTHOR = author;
		this.PUBLISHING_HOUSE = publishingHouse;
		this.ISBN = isbn;
		this.YEAR = year;
	}
	
	public String getName() {
		return NAME;
	}
	public String getAuthor() {
		return AUTHOR;
	}
	public String getPublishingHouse() {
		return PUBLISHING_HOUSE;
	}
	public String getIsbn() {
		return ISBN;
	}
	public String getYEAR() {
		return YEAR;
	}

	public int getExisting() {
		return existing;
	}

	public void setExisting(int existing) {
		this.existing = existing;
	}

	public int getAvailable() {
		return available;
	}

	public void setAvailable(int available) {
		this.available = available;
	}
	
	
	

}
