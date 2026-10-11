package polinomios;

import javax.swing.JOptionPane;

import entities.Forma1;
import entities.Forma2;
import entities.Forma3;
import menu.MenuOptions;

public class Main {
	public static void main(String[] args) {
		MenuOptions menu = new MenuOptions();
		String exitControl = "running...";

		Forma1 PolyForma1 = null;
		Forma2 PolyForma2 = null;
		Forma3 PolyForma3 = null;
		Forma1 ResultSum = null;
		Forma2 ResultMultiplication = null;

		System.out.println("## POLINOMIOS ###");

		menu.mainOptions();

		System.out.println("--Finalizado--");

	}
}
