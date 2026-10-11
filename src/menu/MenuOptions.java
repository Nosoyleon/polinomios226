package menu;

import javax.swing.JOptionPane;

import entities.Forma1;
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
							+ "(4) - Mostrar Forma.\n" + "(5) - Mostrar polinomio.\n"
							+ "(6) - Evaluar con un valor.\n" + "(7) - Sumar polinomios.\n"
							+ "(8) - Multiplicar polinomios.\n" + "\n(0) - Volver.",
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
					Forma1 forma1 = readForma1("Ingrese el nuevo polinomio (reemplaza al actual):");

					if (forma1 != null) {
						Main.PolyForma1 = forma1;
					}
					break;
				case INSERT_MONO:
					String monoInput = JOptionPane.showInputDialog(null, "Ingrese el monomio (ej: 4x^3, -x, 7):",
							"Insertar monomio", -1);

					if (monoInput == null) {
						System.out.println("Ingreso cancelado.");
						break;
					}

					try {
						int[] pair = PolyUtils.parsePolynomial(monoInput);

						if (pair.length != 2) {
							throw new IllegalArgumentException("Ingrese un solo término");
						}

						Main.PolyForma1.insertMonomial(pair[0], pair[1]);
						System.out.println("Monomio insertado: " + monoInput + " -> "
								+ Main.PolyForma1.toPolynomialString());
					} catch (IllegalArgumentException e) {
						JOptionPane.showMessageDialog(null, "Monomio inválido: " + e.getMessage(), "Error", 2);
					}
					break;
				case DELETE:
					String expInput = JOptionPane.showInputDialog(null,
							"Polinomio: " + Main.PolyForma1.toPolynomialString()
									+ "\nIngrese el exponente del monomio a eliminar:",
							"Eliminar monomio", -1);

					if (expInput == null) {
						System.out.println("Eliminación cancelada.");
						break;
					}

					try {
						int exp = Integer.parseInt(expInput.trim());

						if (Main.PolyForma1.deleteMonomial(exp)) {
							System.out.println("Monomio con exponente " + exp + " eliminado -> "
									+ Main.PolyForma1.toPolynomialString());
						} else {
							JOptionPane.showMessageDialog(null, "No existe un término con exponente " + exp, "Error", 2);
						}
					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(null, "Exponente inválido", "Error", 2);
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
					String xInput = JOptionPane.showInputDialog(null,
							"Polinomio: " + Main.PolyForma1.toPolynomialString() + "\nIngrese el valor de x:",
							"Evaluar polinomio", -1);

					if (xInput == null) {
						System.out.println("Evaluación cancelada.");
						break;
					}

					try {
						double x = Double.parseDouble(xInput.trim().replace(',', '.'));
						double result = Main.PolyForma1.evaluate(x);

						String message = "P(" + formatNumber(x) + ") = " + formatNumber(result);
						System.out.println(message);
						JOptionPane.showMessageDialog(null, message, "Evaluar polinomio", 1);
					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(null, "Valor de x inválido", "Error", 2);
					}
					break;
				default:
					System.out.println("Opción " + optionValue + " aún no implementada.");
					break;
				}

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Opción incorrecta", "Error", 2);
			}
		}
	}

	// Pide un polinomio al usuario y lo convierte a Forma 1. Devuelve null si se cancela o es inválido
	private Forma1 readForma1(String message) {
		String polyInput = JOptionPane.showInputDialog(null, message, "Polinomio Forma 1", -1);

		if (polyInput == null) {
			System.out.println("Ingreso cancelado.");
			return null;
		}

		System.out.println("Polinomio ingresado: " + polyInput);

		try {
			int[] pairs = PolyUtils.parsePolynomial(polyInput);
			PolyUtils.sortByExponentDesc(pairs);
			System.out.println("Pares ordenados [coef, exp]: " + PolyUtils.pairsToString(pairs));

			// Tras ordenar, el primer exponente es el grado del polinomio
			Forma1 forma1 = new Forma1(pairs[1]);
			forma1.fillFromPairs(pairs);
			return forma1;
		} catch (IllegalArgumentException e) {
			JOptionPane.showMessageDialog(null, "Polinomio inválido: " + e.getMessage(), "Error", 2);
			return null;
		}
	}

	private String currentValuesHeader() {
		return "Valores actuales:\n"
				+ "  Forma 1: " + (Main.PolyForma1 == null ? "null" : Main.PolyForma1.toPolynomialString()) + "\n"
				+ "  Forma 2: " + (Main.PolyForma2 == null ? "null" : "(sin implementar)") + "\n"
				+ "  Forma 3: " + (Main.PolyForma3 == null ? "null" : "(sin implementar)") + "\n"
				+ "  Suma (F1): " + (Main.ResultSum == null ? "null" : Main.ResultSum.toPolynomialString()) + "\n"
				+ "  Multiplicación (F2): " + (Main.ResultMultiplication == null ? "null" : "(sin implementar)")
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
