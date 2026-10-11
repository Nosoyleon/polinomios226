package utils;

public class PolyUtils {
	public static int[] parsePolynomial(String input) {
		char[] chars = input.replace(" ", "").toCharArray();

		if (chars.length == 0) {
			throw new IllegalArgumentException("Polinomio vacío");
		}

		// Cada signo + o - (excepto el primero) inicia un nuevo término
		int terms = 1;
		for (int i = 1; i < chars.length; i++) {
			if (chars[i] == '+' || chars[i] == '-') {
				terms++;
			}
		}

		int[] pairs = new int[terms * 2];
		int pairIndex = 0;
		int i = 0;

		while (i < chars.length) {
			// Signo
			int sign = 1;
			if (chars[i] == '+' || chars[i] == '-') {
				sign = chars[i] == '-' ? -1 : 1;
				i++;
			}

			// Coeficiente
			int coef = 0;
			boolean hasCoef = false;
			while (i < chars.length && Character.isDigit(chars[i])) {
				coef = coef * 10 + (chars[i] - '0');
				hasCoef = true;
				i++;
			}

			// Exponente
			int exp = 0;
			if (i < chars.length && (chars[i] == 'x' || chars[i] == 'X')) {
				i++;
				if (!hasCoef) {
					coef = 1; // "x" o "-x"
					hasCoef = true;
				}
				exp = 1; // "2x" sin "^"

				if (i < chars.length && chars[i] == '^') {
					i++;
					exp = 0;
					boolean hasExp = false;
					while (i < chars.length && Character.isDigit(chars[i])) {
						exp = exp * 10 + (chars[i] - '0');
						hasExp = true;
						i++;
					}
					if (!hasExp) {
						throw new IllegalArgumentException("Falta el exponente después de '^'");
					}
				}
			}

			if (!hasCoef) {
				throw new IllegalArgumentException("Término inválido en la posición " + i);
			}

			// Después de un término solo puede venir otro signo o el final
			if (i < chars.length && chars[i] != '+' && chars[i] != '-') {
				throw new IllegalArgumentException("Carácter inválido '" + chars[i] + "' en la posición " + i);
			}

			pairs[pairIndex++] = sign * coef;
			pairs[pairIndex++] = exp;
		}

		return pairs;
	}


	public static void sortByExponentDesc(int[] pairs) {
		for (int i = 2; i < pairs.length; i += 2) {
			int coef = pairs[i];
			int exp = pairs[i + 1];
			int j = i - 2;

			while (j >= 0 && pairs[j + 1] < exp) {
				pairs[j + 2] = pairs[j];
				pairs[j + 3] = pairs[j + 1];
				j -= 2;
			}

			pairs[j + 2] = coef;
			pairs[j + 3] = exp;
		}
	}

	public static String pairsToString(int[] pairs) {
		StringBuilder sb = new StringBuilder("[");
		for (int i = 0; i < pairs.length; i++) {
			sb.append(pairs[i]);
			if (i < pairs.length - 1) {
				sb.append(", ");
			}
		}
		return sb.append("]").toString();
	}

	// Agrega un término (coef != 0) al texto del polinomio, ej: "+3x^2", "-x", "-10"
	public static void appendTerm(StringBuilder sb, int coef, int exp) {
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
