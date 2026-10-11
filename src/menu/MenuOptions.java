package menu;

import javax.swing.JOptionPane;

public class MenuOptions {
	public void mainOptions() {

		String exitControl = "running...";

		while (exitControl != null) {

			String userInput = JOptionPane.showInputDialog(null,
					"(1) - Menú Forma 1.\n" + "(2) - Menú Forma 2.\n" + "(3) - Menú Forma 3.\n"
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
}
