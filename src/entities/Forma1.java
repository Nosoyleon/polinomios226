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

	// Inserta coef*x^exp. Si el exponente ya existe, se suman los coeficientes
	public void insertMonomial(int coef, int exp) {
		if (exp > this.vector[0]) {
			// Exponente mayor al grado: se crea un vector más grande y se copian los
			// coeficientes a su nueva posición (newDU - exp)
			int newDU = exp + 1;
			int[] newVector = new int[newDU + 1];
			newVector[0] = exp;

			for (int i = 1; i <= this.DU; i++) {
				int oldExp = this.DU - i;
				newVector[newDU - oldExp] = this.vector[i];
			}

			this.DU = newDU;
			this.vector = newVector;
		}

		this.vector[this.DU - exp] += coef;

		removeLeadingZeros();
	}

	public boolean deleteMonomial(int exp) {
		if (exp < 0 || exp > this.vector[0] || this.vector[this.DU - exp] == 0) {
			return false;
		}

		this.vector[this.DU - exp] = 0;

		removeLeadingZeros();
		return true;
	}

	// Si el término de mayor grado quedó en 0 (ej: se insertó -3x^5 sobre 3x^5),
	// se reduce el grado hasta el primer coeficiente distinto de 0
	private void removeLeadingZeros() {
		int zeros = 0;
		while (zeros < this.DU - 1 && this.vector[zeros + 1] == 0) {
			zeros++;
		}

		if (zeros == 0) {
			return;
		}

		int newDU = this.DU - zeros;
		int[] newVector = new int[newDU + 1];
		newVector[0] = newDU - 1;

		for (int i = 1; i <= newDU; i++) {
			newVector[i] = this.vector[i + zeros];
		}

		this.DU = newDU;
		this.vector = newVector;
	}

	public Forma1 sum(Forma1 other) {
		int degree = Math.max(this.vector[0], other.vector[0]);
		Forma1 result = new Forma1(degree);

		for (int i = 1; i <= result.DU; i++) {
			int exp = result.DU - i;

			if (exp <= this.vector[0]) {
				result.vector[i] += this.vector[this.DU - exp];
			}
			if (exp <= other.vector[0]) {
				result.vector[i] += other.vector[other.DU - exp];
			}
		}

		result.removeLeadingZeros();
		return result;
	}

	public Forma1 multiply(Forma1 other) {
		int degree = this.vector[0] + other.vector[0];
		Forma1 result = new Forma1(degree);

		for (int i = 1; i <= this.DU; i++) {
			if (this.vector[i] != 0) {
				int expA = this.DU - i;

				for (int j = 1; j <= other.DU; j++) {
					if (other.vector[j] != 0) {
						int expB = other.DU - j;

						int exp = expA + expB;
						result.vector[result.DU - exp] += this.vector[i] * other.vector[j];
					}
				}
			}
		}

		result.removeLeadingZeros();
		return result;
	}

	public double evaluate(double x) {
		double result = 0;

		for (int i = 1; i <= this.DU; i++) {
			int exp = this.DU - i;
			result += this.vector[i] * Math.pow(x, exp);
		}

		return result;
	}

	public String toPolynomialString() {
		StringBuilder sb = new StringBuilder();

		for (int i = 1; i <= this.DU; i++) {
			int coef = this.vector[i];
			int exp = this.DU - i;

			if (coef != 0) {
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
