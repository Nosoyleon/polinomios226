package menu;

public enum POLY_OPTION {
	EXIT, INSERT_POLY, INSERT_MONO, DELETE, SHOW_FORMA, SHOW_POLY, EVALUATE_WITH_VALUE, SUM_POLY, MULTIPLY_POLY;

	public POLY_OPTION getFromInt(int index) {
		switch (index) {
		case 0:
			return EXIT;
		case 1:
			return INSERT_POLY;
		case 2:
			return INSERT_MONO;
		case 3:
			return DELETE;
		case 4:
			return SHOW_FORMA;
		case 5:
			return SHOW_POLY;
		case 6:
			return EVALUATE_WITH_VALUE;
		case 7: 
			return SUM_POLY;
		case 8:
			return MULTIPLY_POLY;


		default:
			throw new IllegalArgumentException("Unexpected value: " + index);
		}

	}
}
