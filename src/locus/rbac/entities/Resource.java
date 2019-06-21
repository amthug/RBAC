package locus.rbac.entities;

import java.util.List;

import locus.rbac.enums.RoleType;

public class Resource {

	private Integer id;
	private String name;
	private List<RoleType> accessRoles;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<RoleType> getAccessRoles() {
		return accessRoles;
	}

	public void setAccessRoles(List<RoleType> accessRoles) {
		this.accessRoles = accessRoles;
	}

}
