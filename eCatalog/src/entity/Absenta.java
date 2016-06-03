package entity;

public class Absenta {

	private String data;
	private boolean absent;
	
	public Absenta(String data) {
		this.data = data;
		this.absent = true;
	}

	public String getData() {
		return data;
	}

	public boolean isAbsent() {
		return absent;
	}

	public void setAbsent(boolean valoare) {
		this.absent = valoare;
	}
	
	
	public String toString(){
		return data;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return data.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if((obj instanceof Absenta) && data.equals(((Absenta)obj).getData())){
			return true;
		}
		return false;
	}


	
	
	
}
