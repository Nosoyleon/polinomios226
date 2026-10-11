package polinomios;

import entities.Forma1;
import entities.Forma2;
import entities.Forma3;
import menu.MenuOptions;

public class Main {
	
	// Ejemplos:
	// 3x^2-18x^5+2x-10
	// 2x+3x^2-18x^5-10
	// -10+3x^2-18x^5+2x
	// 10+3x^2-18x^5+2x
	// 10-3x^2-18x^5+2x
	// 3x^2+18x^7+2x-10+6x^4
	// 3x^2-18x^5+x-10

	
	public static Forma1 PolyForma1 = null;
	public static Forma2 PolyForma2 = null;
	public static Forma3 PolyForma3 = null;
	public static Forma1 ResultSum = null;
	public static Forma2 ResultMultiplication = null;

	public static void main(String[] args) {
		MenuOptions menu = new MenuOptions();
		System.out.println("## POLINOMIOS ###");

		menu.mainOptions();

		System.out.println("--Finalizado--");

	}
}
