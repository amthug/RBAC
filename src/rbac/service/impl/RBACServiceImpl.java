package rbac.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import rbac.entities.Resource;
import rbac.entities.User;
import rbac.enums.ActionType;
import rbac.enums.RoleType;

public class RBACServiceImpl {

	public static void main(String[] args) {
		
		Map<RoleType, ActionType> roleAccess = mapRoleAndAccess();

		Map<String, User> users = getUsers();
		
		Map<String, Resource> resources = getResources();
		
		
		// Success case
		useCase1(roleAccess, users, resources);
		
		// Failure case
		useCase2(roleAccess, users, resources);
		
	}

	private static void useCase2(Map<RoleType, ActionType> roleAccess, Map<String, User> users,
			Map<String, Resource> resources) {
		
		// Use case 2 - user->Stuart, actionType->Write, resource->CodeBase
		Resource codeBase = resources.get("CodeBase");
		List<RoleType> rolesMatchingResources = users.get("Stuart").getRoles().stream()
				.filter(role -> codeBase.getAccessRoles().contains(role)).collect(Collectors.toList());

		for (RoleType role : rolesMatchingResources) {
			if (roleAccess.get(role).equals(ActionType.READ_AND_WRITE)) {
				System.out.println("Stuart has WRITE access in CodeBase.");
				return;
			}
		}
		System.out.println("Stuart has no WRITE access in CodeBase.");
	}

	private static void useCase1(Map<RoleType, ActionType> roleAccess, Map<String, User> users,
			Map<String, Resource> resources) {
		
		// Use case 1 - user->Alice, actionType->Delete, resource->Directories
		Resource directories = resources.get("Directories");
		List<RoleType> rolesMatchingResources = users.get("Alice").getRoles().stream()
				.filter(role -> directories.getAccessRoles().contains(role)).collect(Collectors.toList());

		for (RoleType role : rolesMatchingResources) {
			if (roleAccess.get(role).equals(ActionType.ALL)) {
				System.out.println("Alice has DELETE access in Directories.");
				return;
			}
		}
		System.out.println("Alice has no DELETE access in Directories.");
	}

	private static Map<String, Resource> getResources() {
		Map<String, Resource> resources = new HashMap<>();
		
		// resource - contract -> admin, manager
		Resource contract = createResource(1, "Contract", Arrays.asList(new RoleType[] { RoleType.get(1), RoleType.get(2) }));
		resources.put("Contract", contract);

		// resource - codeBase -> manager, developer
		Resource codeBase = createResource(2, "CodeBase", Arrays.asList(new RoleType[] { RoleType.get(2), RoleType.get(3) }));
		resources.put("CodeBase", codeBase);
		
		// resource - directories -> admin
		Resource directories = createResource(3, "Directories", Arrays.asList(new RoleType[] { RoleType.get(1) }));
		resources.put("Directories", directories);
		
		return resources;
	}

	private static Map<String, User> getUsers() {
		Map<String, User> users = new HashMap<>();
		
		// user alice -> admin
		User alice = createUser(1, "Alice", "Alice@20", Arrays.asList(new RoleType[] { RoleType.get(1) }));
		users.put("Alice", alice);

		// user bob -> manager, developer
		User bob = createUser(2, "Bob", "Bob@20", Arrays.asList(new RoleType[] { RoleType.get(2), RoleType.get(3) }));
		users.put("Bob", bob);

		// user stuart -> developer
		User stuart = createUser(3, "Stuart", "Stuart@20", Arrays.asList(new RoleType[] { RoleType.get(3) }));
		users.put("Stuart", stuart);

		return users;
	}
	
	private static Map<RoleType, ActionType> mapRoleAndAccess() {
		// Mapping between roles and accesses
		Map<RoleType, ActionType> roleAccess = new HashMap<>();
		roleAccess.put(RoleType.get(1), ActionType.ALL); // Admin -> Read, Write, Delete
		roleAccess.put(RoleType.get(2), ActionType.READ_AND_WRITE); // Manager -> Read, Write
		roleAccess.put(RoleType.get(3), ActionType.READ); // Developer -> Read
		return roleAccess;
	}

	private static User createUser(Integer id, String userName, String password, List<RoleType> roles) {
		User user = new User();
		user.setId(id);
		user.setUserName(userName);
		user.setPassword(password);
		user.setRoles(roles);
		return user;
	}

	private static Resource createResource(Integer id, String name, List<RoleType> roles) {
		Resource resource = new Resource();
		resource.setId(id);
		resource.setName(name);
		resource.setAccessRoles(roles);
		return resource;
	}

}
