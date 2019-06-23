package rbac.enums;

public enum ActionType {

	READ(1),
	READ_AND_WRITE(3),
	ALL(6);

	private Integer index = 0;

	ActionType(Integer index) {
		this.index = index;
	}

	public static ActionType get(Integer index) {
		if ((index < 1) || (index > values().length)) {
			return null;
		}

		return values()[index - 1];
	}
}
