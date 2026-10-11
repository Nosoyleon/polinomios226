package entities;

public class Forma3 {
	private Nodo Punta;

	public Forma3() {
		super();
		Punta = null;
	}

	public Nodo getPunta() {
		return Punta;
	}

	public void setPunta(Nodo punta) {
		Punta = punta;
	}

	public void CrearPolinomio(String vectorString[]) {
		int i = 0;
		while (vectorString[i] != null) {
			InsertarFinal(Integer.parseInt(vectorString[i]), Integer.parseInt(vectorString[i + 1]));
			i = i + 2;
		}
	}

	public void InsertarFinal(int coe, int exp) {
		Nodo head, newNode = new Nodo(coe, exp);

		if (Punta == null) {
			Punta = newNode;
		} else {
			head = Punta;

			while (head.getLiga() != null) {
				head = head.getLiga();
			}

			head.setLiga(newNode);
		}

	}

}
