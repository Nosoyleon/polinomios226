package entities;

import utils.PolyUtils;

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

	public void fillFromPairs(int[] pairs) {
		Nodo last = null;

		for (int k = 0; k < pairs.length; k += 2) {
			if (pairs[k] != 0) {
				Nodo newNode = new Nodo(pairs[k], pairs[k + 1]);

				if (Punta == null) {
					Punta = newNode;
				} else {
					last.setLiga(newNode);
				}

				last = newNode;
			}
		}
	}

	public void insertMonomial(int coef, int exp) {
		if (coef != 0) {
			Nodo prev = null;
			Nodo p = Punta;

			while (p != null && p.getExp() > exp) {
				prev = p;
				p = p.getLiga();
			}

			if (p != null && p.getExp() == exp) {
				p.setCoe(p.getCoe() + coef);

				if (p.getCoe() == 0) {
					unlink(prev, p);
				}
			} else {
				// El nuevo nodo va entre prev y p
				Nodo newNode = new Nodo(coef, exp);
				newNode.setLiga(p);

				if (prev == null) {
					Punta = newNode;
				} else {
					prev.setLiga(newNode);
				}
			}
		}
	}


	public boolean deleteMonomial(int exp) {
		Nodo prev = null;
		Nodo p = Punta;

		while (p != null && p.getExp() > exp) {
			prev = p;
			p = p.getLiga();
		}

		if (p == null || p.getExp() != exp) {
			return false;
		}

		unlink(prev, p);
		return true;
	}


	public Forma3 sum(Forma3 other) {
		Forma3 result = new Forma3();
		Nodo p = this.Punta;
		Nodo q = other.Punta;

		while (p != null || q != null) {
			int coef;
			int exp;

			if (q == null || (p != null && p.getExp() > q.getExp())) {
				coef = p.getCoe();
				exp = p.getExp();
				p = p.getLiga();
			} else if (p == null || q.getExp() > p.getExp()) {
				coef = q.getCoe();
				exp = q.getExp();
				q = q.getLiga();
			} else {
				// Mismo exponente en ambas: se suman los coeficientes
				coef = p.getCoe() + q.getCoe();
				exp = p.getExp();
				p = p.getLiga();
				q = q.getLiga();
			}

			if (coef != 0) {
				result.InsertarFinal(coef, exp);
			}
		}

		return result;
	}

	public Forma3 multiply(Forma3 other) {
		Forma3 result = new Forma3();
		Nodo p = this.Punta;

		while (p != null) {
			Nodo q = other.Punta;

			while (q != null) {
				result.insertMonomial(p.getCoe() * q.getCoe(), p.getExp() + q.getExp());
				q = q.getLiga();
			}

			p = p.getLiga();
		}

		return result;
	}

	public double evaluate(double x) {
		double result = 0;
		Nodo p = Punta;

		while (p != null) {
			result += p.getCoe() * Math.pow(x, p.getExp());
			p = p.getLiga();
		}

		return result;
	}

	// Saca el nodo p de la lista ligando su anterior (o Punta) con el siguiente de
	// p
	private void unlink(Nodo prev, Nodo p) {
		if (prev == null) {
			Punta = p.getLiga();
		} else {
			prev.setLiga(p.getLiga());
		}
	}

	public String toPolynomialString() {
		StringBuilder sb = new StringBuilder();
		Nodo p = Punta;

		while (p != null) {
			PolyUtils.appendTerm(sb, p.getCoe(), p.getExp());
			p = p.getLiga();
		}

		return sb.length() == 0 ? "0" : sb.toString();
	}

	public String toFormaString() {
		StringBuilder sb = new StringBuilder("Forma 3 -> Punta");
		Nodo p = Punta;

		while (p != null) {
			sb.append(" -> [").append(p.getCoe()).append("|").append(p.getExp()).append("]");
			p = p.getLiga();
		}

		return sb.append(" -> null").toString();
	}

}
