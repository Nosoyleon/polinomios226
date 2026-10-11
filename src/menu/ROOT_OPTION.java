package menu;

public enum ROOT_OPTION {
	EXIT, FORMA1_MENU, FORMA2_MENU, FORMA3_MENU, FORMA2_PLUS_FORMA3, FORMA3_TIMES_FORMA1;

	public ROOT_OPTION getFromInt(int index) {
		switch (index) {
		case 0:
			return EXIT;
		case 1:
			return FORMA1_MENU;
		case 2:
			return FORMA2_MENU;
		case 3:
			return FORMA3_MENU;
		case 4:
			return FORMA2_PLUS_FORMA3;
		case 5:
			return FORMA3_TIMES_FORMA1;

		default:
			throw new IllegalArgumentException("Unexpected value: " + index);
		}

	}
}
