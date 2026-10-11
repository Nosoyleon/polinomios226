package entities;

public class Nodo {
	private int Coe, Exp;
	private Nodo Liga;

	public Nodo(int coe, int exp) {
		super();
		Coe = coe;
		Exp = exp;
		Liga = null;
	}

	public int getCoe() {
		return Coe;
	}

	public void setCoe(int coe) {
		Coe = coe;
	}

	public int getExp() {
		return Exp;
	}

	public void setExp(int exp) {
		Exp = exp;
	}

	public Nodo getLiga() {
		return Liga;
	}

	public void setLiga(Nodo liga) {
		Liga = liga;
	}

}
