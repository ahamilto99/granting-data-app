package ca.gc.tri_agency.granting_data.service;

import java.util.List;

import ca.gc.tri_agency.granting_data.model.MemberRole;

public interface MemberRoleService {

	MemberRole findMemberRoleById(Long id);
	
	List<MemberRole> findAllMemberRoles();
	
	MemberRole saveMemberRole(MemberRole memberRole);
	
	void deleteMemberRole(Long id);
	
}
