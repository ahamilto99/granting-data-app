package ca.gc.tri_agency.granting_data.service.impl;

import java.util.List;

import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Service;

import ca.gc.tri_agency.granting_data.model.Role;
import ca.gc.tri_agency.granting_data.repo.RoleRepository;
import ca.gc.tri_agency.granting_data.security.annotations.AdminOnly;
import ca.gc.tri_agency.granting_data.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	private RoleRepository roleRepo;

	public RoleServiceImpl(RoleRepository roleRepo) {
		this.roleRepo = roleRepo;
	}

	@Override
	public Role findRoleById(Long id) {
		return roleRepo.findById(id).orElseThrow(() -> new DataRetrievalFailureException("That Role does not exist"));
	}

	@Override
	public List<Role> findAllRoles() {
		return roleRepo.findAll();
	}

	@AdminOnly
	@Override
	public Role saveRole(Role role) {
		return roleRepo.save(role);
	}

}
