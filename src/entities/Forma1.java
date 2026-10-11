package entities;

import java.util.Arrays;

public class Forma1 {

	private int DU, vector[];
	
	public Forma1(int grado) {
		super();
		this.DU = grado + 1;
		this.vector = new int[this.DU + 1];
		this.vector[0] = grado;
	}

	public void fillFromPairs(int[] pairs) {
		for (int k = 0; k < pairs.length; k += 2) {
			int coef = pairs[k];
			int exp = pairs[k + 1];
			this.vector[this.DU - exp] = coef;
		}
	}

	public String toPolynomialString() {
		StringBuilder sb = new StringBuilder();

		for (int i = 1; i <= this.DU; i++) {
			int coef = this.vector[i];
			int exp = this.DU - i;

			if (coef == 0) {
				continue;
			}

			// Signo: el primer término solo lleva "-" si es negativo
			if (coef < 0) {
				sb.append("-");
			} else if (sb.length() > 0) {
				sb.append("+");
			}

			int absCoef = Math.abs(coef);

			// El coeficiente 1 se omite cuando hay x (ej: "x^3" en vez de "1x^3")
			if (absCoef != 1 || exp == 0) {
				sb.append(absCoef);
			}

			if (exp >= 1) {
				sb.append("x");
			}
			if (exp > 1) {
				sb.append("^").append(exp);
			}
		}

		return sb.length() == 0 ? "0" : sb.toString();
	}

	public String toFormaString() {
		return "Forma 1 -> DU: " + this.DU + ", Vector: " + Arrays.toString(this.vector);
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
