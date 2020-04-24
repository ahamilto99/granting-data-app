package ca.gc.tri_agency.granting_data.service;

import java.util.List;

import ca.gc.tri_agency.granting_data.model.Role;

public interface RoleService {

	Role findRoleById(Long id);
	
	List<Role> findAllRoles();
	
	Role saveRole(Role role);
}
