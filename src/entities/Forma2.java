package entities;

import java.util.Arrays;

import utils.PolyUtils;

public class Forma2 {
	private int DU, vector[];

	public Forma2(int pairsLength) {
		super();
		this.DU = pairsLength;
		this.vector = new int[this.DU + 1];
		this.vector[0] = this.DU / 2;
	}

	public void fillFromPairs(int[] pairs) {
		for (int k = 0; k < pairs.length; k++) {
			this.vector[k + 1] = pairs[k];
		}

		removeZeroTerms();
	}

	public void insertMonomial(int coef, int exp) {
		if (coef != 0) {
			int k = findPosition(exp);

			if (k < this.DU && this.vector[k + 1] == exp) {
				this.vector[k] += coef;

				if (this.vector[k] == 0) {
					removeTermAt(k);
				}
			} else {
				insertTermAt(k, coef, exp);
			}
		}
	}

	public boolean deleteMonomial(int exp) {
		int k = findPosition(exp);

		if (k >= this.DU || this.vector[k + 1] != exp) {
			return false;
		}

		removeTermAt(k);
		return true;
	}

	public Forma2 sum(Forma2 other) {
		int[] pairs = new int[this.DU + other.DU];
		int n = 0;
		int i = 1;
		int j = 1;

		while (i < this.DU || j < other.DU) {
			int coef;
			int exp;

			if (j >= other.DU || (i < this.DU && this.vector[i + 1] > other.vector[j + 1])) {
				coef = this.vector[i];
				exp = this.vector[i + 1];
				i += 2;
			} else if (i >= this.DU || other.vector[j + 1] > this.vector[i + 1]) {
				coef = other.vector[j];
				exp = other.vector[j + 1];
				j += 2;
			} else {
				// Mismo exponente en ambos: se suman los coeficientes
				coef = this.vector[i] + other.vector[j];
				exp = this.vector[i + 1];
				i += 2;
				j += 2;
			}

			if (coef != 0) {
				pairs[n] = coef;
				pairs[n + 1] = exp;
				n += 2;
			}
		}

		Forma2 result = new Forma2(n);
		result.fillFromPairs(Arrays.copyOf(pairs, n));
		return result;
	}

	public Forma2 multiply(Forma2 other) {
		Forma2 result = new Forma2(0);

		for (int i = 1; i < this.DU; i += 2) {
			for (int j = 1; j < other.DU; j += 2) {
				int coef = this.vector[i] * other.vector[j];
				int exp = this.vector[i + 1] + other.vector[j + 1];
				result.insertMonomial(coef, exp);
			}
		}

		return result;
	}

	public static Forma2 multiplyForma3AndForma1(Forma3 forma3, Forma1 forma1) {
		Forma2 result = new Forma2(0);
		int[] vector1 = forma1.getVector();
		int du1 = forma1.getDU();
		Nodo p = forma3.getPunta();

		while (p != null) {
			for (int i = 1; i <= du1; i++) {
				if (vector1[i] != 0) {
					int exp1 = du1 - i;
					result.insertMonomial(p.getCoe() * vector1[i], p.getExp() + exp1);
				}
			}

			p = p.getLiga();
		}

		return result;
	}

	public double evaluate(double x) {
		double result = 0;

		for (int k = 1; k < this.DU; k += 2) {
			result += this.vector[k] * Math.pow(x, this.vector[k + 1]);
		}

		return result;
	}

	public String toPolynomialString() {
		StringBuilder sb = new StringBuilder();

		for (int k = 1; k < this.DU; k += 2) {
			if (this.vector[k] != 0) {
				PolyUtils.appendTerm(sb, this.vector[k], this.vector[k + 1]);
			}
		}

		return sb.length() == 0 ? "0" : sb.toString();
	}

	public String toFormaString() {
		return "Forma 2 -> DU: " + this.DU + ", Vector: " + Arrays.toString(this.vector);
	}

	private int findPosition(int exp) {
		int k = 1;

		while (k < this.DU && this.vector[k + 1] > exp) {
			k += 2;
		}

		return k;
	}

	// Crea un vector 2 posiciones más grande con el nuevo par en la posición k
	private void insertTermAt(int k, int coef, int exp) {
		int[] newVector = new int[this.DU + 3];

		for (int p = 1; p < k; p++) {
			newVector[p] = this.vector[p];
		}

		newVector[k] = coef;
		newVector[k + 1] = exp;

		for (int p = k; p <= this.DU; p++) {
			newVector[p + 2] = this.vector[p];
		}

		this.DU += 2;
		this.vector = newVector;
		this.vector[0] = this.DU / 2;
	}

	// Crea un vector 2 posiciones más pequeño sin el par de la posición k
	private void removeTermAt(int k) {
		int[] newVector = new int[this.DU - 1];

		for (int p = 1; p < k; p++) {
			newVector[p] = this.vector[p];
		}

		for (int p = k + 2; p <= this.DU; p++) {
			newVector[p - 2] = this.vector[p];
		}

		this.DU -= 2;
		this.vector = newVector;
		this.vector[0] = this.DU / 2;
	}

	private void removeZeroTerms() {
		int k = 1;

		while (k < this.DU) {
			if (this.vector[k] == 0) {
				removeTermAt(k);
			} else {
				k += 2;
			}
		}
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
