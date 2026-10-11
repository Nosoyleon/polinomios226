package entities;

public class Forma1 {

	private int DU, vector[];
	
	public Forma1(int grado) {
		super();
		this.DU = grado + 1;
		this.vector = new int[this.DU + 1];
		this.vector[0] = grado;
	}

	public int getDU() {
		return DU;
	}

	public void setDU(int dU) {
		DU = dU;
	}

	public int[] getVector() {
		return vector;
	}

	public void setVector(int[] vector) {
		this.vector = vector;
	}


}
