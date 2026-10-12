package menu;

import javax.swing.JOptionPane;

import entities.Forma1;
import entities.Forma2;
import entities.Forma3;
import polinomios.Main;
import utils.PolyUtils;

public class MenuOptions {
	public void mainOptions() {

		String exitControl = "running...";

		while (exitControl != null) {

			String userInput = JOptionPane.showInputDialog(null,
					currentValuesHeader() + "(1) - Menú Forma 1.\n" + "(2) - Menú Forma 2.\n" + "(3) - Menú Forma 3.\n"
							+ "(4) - Sumar Forma 2 + Forma 3 = Forma 1.\n"
							+ "(5) - Multiplicar Forma 3 * Forma 1 = Forma 2.\n" + "\n(0) - Salir.",
					"Que desea hacer con el Árbol?", -1);

			try {
				int optionInt = userInput == null ? 0 : Integer.parseInt(userInput);

				ROOT_OPTION optionValue = ROOT_OPTION.EXIT;
				optionValue = optionValue.getFromInt(optionInt);

				switch (optionValue) {
				// Left -> Root -> Right
				case FORMA1_MENU:
					System.out.println("MOSTRANDO MENU FORMA 1...");

					if (Main.PolyForma1 == null) {
						Forma1 forma1 = readForma1("No hay un polinomio en Forma 1.\nIngrese el polinomio:");

						if (forma1 == null) {
							break;
						}

						Main.PolyForma1 = forma1;
					}

					forma1Options();

					System.out.println();
					break;

				case FORMA2_MENU:
					System.out.println("MOSTRANDO MENU FORMA 2...");

					if (Main.PolyForma2 == null) {
						Forma2 forma2 = readForma2("No hay un polinomio en Forma 2.\nIngrese el polinomio:");

						if (forma2 == null) {
							break;
						}

						Main.PolyForma2 = forma2;
					}

					forma2Options();

					System.out.println();
					break;

				case FORMA3_MENU:
					System.out.println("MOSTRANDO MENU FORMA 3...");

					if (Main.PolyForma3 == null) {
						Forma3 forma3 = readForma3("No hay un polinomio en Forma 3.\nIngrese el polinomio:");

						if (forma3 == null) {
							break;
						}

						Main.PolyForma3 = forma3;
					}

					forma3Options();

					System.out.println();
					break;

				case EXIT:
					exitControl = null;
					break;
				default:
					break;

				}

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Opción incorrecta", "Error", 2);
			}

		}
	}

	public void forma1Options() {

		boolean running = true;

		while (running) {

			System.out.println(Main.PolyForma1.toFormaString());

			String userInput = JOptionPane.showInputDialog(null,
					"(1) - Insertar polinomio.\n" + "(2) - Insertar monomio.\n" + "(3) - Eliminar monomio.\n"
							+ "(4) - Mostrar Forma.\n" + "(5) - Mostrar polinomio.\n" + "(6) - Evaluar con un valor.\n"
							+ "(7) - Sumar polinomios.\n" + "(8) - Multiplicar polinomios.\n" + "\n(0) - Volver.",
					"Menú Forma 1", -1);

			try {
				int optionInt = userInput == null ? 0 : Integer.parseInt(userInput);

				POLY_OPTION optionValue = POLY_OPTION.EXIT;
				optionValue = optionValue.getFromInt(optionInt);

				switch (optionValue) {
				case EXIT:
					running = false;
					break;
				case INSERT_POLY:
					Forma1 newPoly = readForma1("Ingrese el nuevo polinomio (reemplaza al actual):");

					if (newPoly != null) {
						Main.PolyForma1 = newPoly;
					}
					break;
				case INSERT_MONO:
					int[] pair = readMonomial();

					if (pair != null) {
						Main.PolyForma1.insertMonomial(pair[0], pair[1]);
						System.out.println("Monomio insertado -> " + Main.PolyForma1.toPolynomialString());
					}
					break;
				case DELETE:
					Integer exp = readExponent(Main.PolyForma1.toPolynomialString());

					if (exp != null) {
						if (Main.PolyForma1.deleteMonomial(exp)) {
							System.out.println("Monomio con exponente " + exp + " eliminado -> "
									+ Main.PolyForma1.toPolynomialString());
						} else {
							JOptionPane.showMessageDialog(null, "No existe un término con exponente " + exp, "Error",
									2);
						}
					}
					break;
				case SHOW_FORMA:
					System.out.println(Main.PolyForma1.toFormaString());
					JOptionPane.showMessageDialog(null, Main.PolyForma1.toFormaString(), "Mostrar Forma 1", 1);
					break;
				case SHOW_POLY:
					System.out.println("Polinomio Forma 1: " + Main.PolyForma1.toPolynomialString());
					JOptionPane.showMessageDialog(null, Main.PolyForma1.toPolynomialString(), "Polinomio Forma 1", 1);
					break;
				case EVALUATE_WITH_VALUE:
					Double x = readX(Main.PolyForma1.toPolynomialString());

					if (x != null) {
						String message = "P(" + formatNumber(x) + ") = " + formatNumber(Main.PolyForma1.evaluate(x));
						System.out.println(message);
						JOptionPane.showMessageDialog(null, message, "Evaluar polinomio", 1);
					}
					break;
				case SUM_POLY:
					Forma1 addend = readForma1("Polinomio: " + Main.PolyForma1.toPolynomialString()
							+ "\nIngrese el monomio o polinomio a sumar:");

					if (addend != null) {
						String before = Main.PolyForma1.toPolynomialString();
						Main.PolyForma1 = Main.PolyForma1.sum(addend);

						String sumMessage = "(" + before + ") + (" + addend.toPolynomialString() + ") = "
								+ Main.PolyForma1.toPolynomialString();
						System.out.println(sumMessage);
						JOptionPane.showMessageDialog(null, sumMessage, "Sumar polinomios", 1);
					}
					break;
				case MULTIPLY_POLY:
					Forma1 factor = readForma1("Polinomio: " + Main.PolyForma1.toPolynomialString()
							+ "\nIngrese el monomio o polinomio a multiplicar:");

					if (factor != null) {
						String beforeMultiply = Main.PolyForma1.toPolynomialString();
						Main.PolyForma1 = Main.PolyForma1.multiply(factor);

						String multiplyMessage = "(" + beforeMultiply + ") * (" + factor.toPolynomialString() + ") = "
								+ Main.PolyForma1.toPolynomialString();
						System.out.println(multiplyMessage);
						JOptionPane.showMessageDialog(null, multiplyMessage, "Multiplicar polinomios", 1);
					}
					break;
				default:
					break;
				}

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Opción incorrecta", "Error", 2);
			}
		}
	}

	public void forma2Options() {

		boolean running = true;

		while (running) {

			System.out.println(Main.PolyForma2.toFormaString());

			String userInput = JOptionPane.showInputDialog(null,
					"(1) - Insertar polinomio.\n" + "(2) - Insertar monomio.\n" + "(3) - Eliminar monomio.\n"
							+ "(4) - Mostrar Forma.\n" + "(5) - Mostrar polinomio.\n" + "(6) - Evaluar con un valor.\n"
							+ "(7) - Sumar polinomios.\n" + "(8) - Multiplicar polinomios.\n" + "\n(0) - Volver.",
					"Menú Forma 2", -1);

			try {
				int optionInt = userInput == null ? 0 : Integer.parseInt(userInput);

				POLY_OPTION optionValue = POLY_OPTION.EXIT;
				optionValue = optionValue.getFromInt(optionInt);

				switch (optionValue) {
				case EXIT:
					running = false;
					break;
				case INSERT_POLY:
					Forma2 newPoly = readForma2("Ingrese el nuevo polinomio (reemplaza al actual):");

					if (newPoly != null) {
						Main.PolyForma2 = newPoly;
					}
					break;
				case INSERT_MONO:
					int[] pair = readMonomial();

					if (pair != null) {
						Main.PolyForma2.insertMonomial(pair[0], pair[1]);
						System.out.println("Monomio insertado -> " + Main.PolyForma2.toPolynomialString());
					}
					break;
				case DELETE:
					Integer exp = readExponent(Main.PolyForma2.toPolynomialString());

					if (exp != null) {
						if (Main.PolyForma2.deleteMonomial(exp)) {
							System.out.println("Monomio con exponente " + exp + " eliminado -> "
									+ Main.PolyForma2.toPolynomialString());
						} else {
							JOptionPane.showMessageDialog(null, "No existe un término con exponente " + exp, "Error",
									2);
						}
					}
					break;
				case SHOW_FORMA:
					System.out.println(Main.PolyForma2.toFormaString());
					JOptionPane.showMessageDialog(null, Main.PolyForma2.toFormaString(), "Mostrar Forma 2", 1);
					break;
				case SHOW_POLY:
					System.out.println("Polinomio Forma 2: " + Main.PolyForma2.toPolynomialString());
					JOptionPane.showMessageDialog(null, Main.PolyForma2.toPolynomialString(), "Polinomio Forma 2", 1);
					break;
				case EVALUATE_WITH_VALUE:
					Double x = readX(Main.PolyForma2.toPolynomialString());

					if (x != null) {
						String message = "P(" + formatNumber(x) + ") = " + formatNumber(Main.PolyForma2.evaluate(x));
						System.out.println(message);
						JOptionPane.showMessageDialog(null, message, "Evaluar polinomio", 1);
					}
					break;
				case SUM_POLY:
					Forma2 addend = readForma2("Polinomio: " + Main.PolyForma2.toPolynomialString()
							+ "\nIngrese el monomio o polinomio a sumar:");

					if (addend != null) {
						String before = Main.PolyForma2.toPolynomialString();
						Main.PolyForma2 = Main.PolyForma2.sum(addend);

						String sumMessage = "(" + before + ") + (" + addend.toPolynomialString() + ") = "
								+ Main.PolyForma2.toPolynomialString();
						System.out.println(sumMessage);
						JOptionPane.showMessageDialog(null, sumMessage, "Sumar polinomios", 1);
					}
					break;
				case MULTIPLY_POLY:
					Forma2 factor = readForma2("Polinomio: " + Main.PolyForma2.toPolynomialString()
							+ "\nIngrese el monomio o polinomio a multiplicar:");

					if (factor != null) {
						String beforeMultiply = Main.PolyForma2.toPolynomialString();
						Main.PolyForma2 = Main.PolyForma2.multiply(factor);

						String multiplyMessage = "(" + beforeMultiply + ") * (" + factor.toPolynomialString() + ") = "
								+ Main.PolyForma2.toPolynomialString();
						System.out.println(multiplyMessage);
						JOptionPane.showMessageDialog(null, multiplyMessage, "Multiplicar polinomios", 1);
					}
					break;
				default:
					break;
				}

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Opción incorrecta", "Error", 2);
			}
		}
	}

	public void forma3Options() {

		boolean running = true;

		while (running) {

			System.out.println(Main.PolyForma3.toFormaString());

			String userInput = JOptionPane.showInputDialog(null,
					"(1) - Insertar polinomio.\n" + "(2) - Insertar monomio.\n" + "(3) - Eliminar monomio.\n"
							+ "(4) - Mostrar Forma.\n" + "(5) - Mostrar polinomio.\n" + "(6) - Evaluar con un valor.\n"
							+ "(7) - Sumar polinomios.\n" + "(8) - Multiplicar polinomios.\n" + "\n(0) - Volver.",
					"Menú Forma 3", -1);

			try {
				int optionInt = userInput == null ? 0 : Integer.parseInt(userInput);

				POLY_OPTION optionValue = POLY_OPTION.EXIT;
				optionValue = optionValue.getFromInt(optionInt);

				switch (optionValue) {
				case EXIT:
					running = false;
					break;
				case INSERT_POLY:
					Forma3 newPoly = readForma3("Ingrese el nuevo polinomio (reemplaza al actual):");

					if (newPoly != null) {
						Main.PolyForma3 = newPoly;
					}
					break;
				case INSERT_MONO:
					int[] pair = readMonomial();

					if (pair != null) {
						Main.PolyForma3.insertMonomial(pair[0], pair[1]);
						System.out.println("Monomio insertado -> " + Main.PolyForma3.toPolynomialString());
					}
					break;
				case DELETE:
					Integer exp = readExponent(Main.PolyForma3.toPolynomialString());

					if (exp != null) {
						if (Main.PolyForma3.deleteMonomial(exp)) {
							System.out.println("Monomio con exponente " + exp + " eliminado -> "
									+ Main.PolyForma3.toPolynomialString());
						} else {
							JOptionPane.showMessageDialog(null, "No existe un término con exponente " + exp, "Error",
									2);
						}
					}
					break;
				case SHOW_FORMA:
					System.out.println(Main.PolyForma3.toFormaString());
					JOptionPane.showMessageDialog(null, Main.PolyForma3.toFormaString(), "Mostrar Forma 3", 1);
					break;
				case SHOW_POLY:
					System.out.println("Polinomio Forma 3: " + Main.PolyForma3.toPolynomialString());
					JOptionPane.showMessageDialog(null, Main.PolyForma3.toPolynomialString(), "Polinomio Forma 3", 1);
					break;
				case EVALUATE_WITH_VALUE:
					Double x = readX(Main.PolyForma3.toPolynomialString());

					if (x != null) {
						String message = "P(" + formatNumber(x) + ") = " + formatNumber(Main.PolyForma3.evaluate(x));
						System.out.println(message);
						JOptionPane.showMessageDialog(null, message, "Evaluar polinomio", 1);
					}
					break;
				case SUM_POLY:
					Forma3 addend = readForma3("Polinomio: " + Main.PolyForma3.toPolynomialString()
							+ "\nIngrese el monomio o polinomio a sumar:");

					if (addend != null) {
						String before = Main.PolyForma3.toPolynomialString();
						Main.PolyForma3 = Main.PolyForma3.sum(addend);

						String sumMessage = "(" + before + ") + (" + addend.toPolynomialString() + ") = "
								+ Main.PolyForma3.toPolynomialString();
						System.out.println(sumMessage);
						JOptionPane.showMessageDialog(null, sumMessage, "Sumar polinomios", 1);
					}
					break;
				case MULTIPLY_POLY:
					Forma3 factor = readForma3("Polinomio: " + Main.PolyForma3.toPolynomialString()
							+ "\nIngrese el monomio o polinomio a multiplicar:");

					if (factor != null) {
						String beforeMultiply = Main.PolyForma3.toPolynomialString();
						Main.PolyForma3 = Main.PolyForma3.multiply(factor);

						String multiplyMessage = "(" + beforeMultiply + ") * (" + factor.toPolynomialString() + ") = "
								+ Main.PolyForma3.toPolynomialString();
						System.out.println(multiplyMessage);
						JOptionPane.showMessageDialog(null, multiplyMessage, "Multiplicar polinomios", 1);
					}
					break;
				default:
					break;
				}

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Opción incorrecta", "Error", 2);
			}
		}
	}

	// Pide un monomio (ej: 4x^3, -x, 7) y lo devuelve como [coef, exp]. Devuelve
	// null si se
	// cancela o es inválido
	private int[] readMonomial() {
		String monoInput = JOptionPane.showInputDialog(null, "Ingrese el monomio (ej: 4x^3, -x, 7):",
				"Insertar monomio", -1);

		if (monoInput == null) {
			System.out.println("Ingreso cancelado.");
			return null;
		}

		try {
			int[] pair = PolyUtils.parsePolynomial(monoInput);

			if (pair.length != 2) {
				throw new IllegalArgumentException("Ingrese un solo término");
			}

			System.out.println("Monomio ingresado: " + monoInput);
			return pair;
		} catch (IllegalArgumentException e) {
			JOptionPane.showMessageDialog(null, "Monomio inválido: " + e.getMessage(), "Error", 2);
			return null;
		}
	}

	// Pide el exponente del monomio a eliminar. Devuelve null si se cancela o es
	// inválido
	private Integer readExponent(String polynomial) {
		String expInput = JOptionPane.showInputDialog(null,
				"Polinomio: " + polynomial + "\nIngrese el exponente del monomio a eliminar:", "Eliminar monomio", -1);

		if (expInput == null) {
			System.out.println("Eliminación cancelada.");
			return null;
		}

		try {
			return Integer.parseInt(expInput.trim());
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Exponente inválido", "Error", 2);
			return null;
		}
	}

	private Double readX(String polynomial) {
		String xInput = JOptionPane.showInputDialog(null, "Polinomio: " + polynomial + "\nIngrese el valor de x:",
				"Evaluar polinomio", -1);

		if (xInput == null) {
			System.out.println("Evaluación cancelada.");
			return null;
		}

		try {
			return Double.parseDouble(xInput.trim().replace(',', '.'));
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Valor de x inválido", "Error", 2);
			return null;
		}
	}

	private Forma1 readForma1(String message) {
		int[] pairs = readSortedPairs(message, "Polinomio Forma 1");

		if (pairs == null) {
			return null;
		}

		// Tras ordenar, el primer exponente es el grado del polinomio
		Forma1 forma1 = new Forma1(pairs[1]);
		forma1.fillFromPairs(pairs);
		return forma1;
	}

	private Forma3 readForma3(String message) {
		int[] pairs = readSortedPairs(message, "Polinomio Forma 3");

		if (pairs == null) {
			return null;
		}

		Forma3 forma3 = new Forma3();
		forma3.fillFromPairs(pairs);
		return forma3;
	}

	private Forma2 readForma2(String message) {
		int[] pairs = readSortedPairs(message, "Polinomio Forma 2");

		if (pairs == null) {
			return null;
		}

		Forma2 forma2 = new Forma2(pairs.length);
		forma2.fillFromPairs(pairs);
		return forma2;
	}

	private int[] readSortedPairs(String message, String title) {
		String polyInput = JOptionPane.showInputDialog(null, message, title, -1);

		if (polyInput == null) {
			System.out.println("Ingreso cancelado.");
			return null;
		}

		System.out.println("Polinomio ingresado: " + polyInput);

		try {
			int[] pairs = PolyUtils.parsePolynomial(polyInput);
			PolyUtils.sortByExponentDesc(pairs);
			System.out.println("Pares ordenados [coef, exp]: " + PolyUtils.pairsToString(pairs));
			return pairs;
		} catch (IllegalArgumentException e) {
			JOptionPane.showMessageDialog(null, "Polinomio inválido: " + e.getMessage(), "Error", 2);
			return null;
		}
	}

	private String currentValuesHeader() {
		return "Valores actuales:\n" + "  Forma 1: "
				+ (Main.PolyForma1 == null ? "null" : Main.PolyForma1.toPolynomialString()) + "\n" + "  Forma 2: "
				+ (Main.PolyForma2 == null ? "null" : Main.PolyForma2.toPolynomialString()) + "\n" + "  Forma 3: "
				+ (Main.PolyForma3 == null ? "null" : Main.PolyForma3.toPolynomialString()) + "\n" + "  Suma (F1): "
				+ (Main.ResultSum == null ? "null" : Main.ResultSum.toPolynomialString()) + "\n"
				+ "  Multiplicación (F2): "
				+ (Main.ResultMultiplication == null ? "null" : Main.ResultMultiplication.toPolynomialString())
				+ "\n\n";
	}

	// Muestra enteros sin ".0" (ej: 5 en vez de 5.0)
	private String formatNumber(double value) {
		if (value == Math.rint(value) && !Double.isInfinite(value)) {
			return String.valueOf((long) value);
		}
		return String.valueOf(value);
	}
}
