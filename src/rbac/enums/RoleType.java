package rbac.enums;

public enum RoleType {

	ADMIN(1),
	MANAGER(2),
	DEVELOPER(3);

	private Integer index = 0;

	RoleType(Integer index) {
		this.index = index;
	}

	public static RoleType get(Integer index) {
		if ((index < 1) || (index > values().length)) {
			return null;
		}
		return values()[index - 1];
	}

}
