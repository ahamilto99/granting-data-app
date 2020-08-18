package ca.gc.tri_agency.granting_data.service;

import java.util.List;

import ca.gc.tri_agency.granting_data.model.MemberRole;

public interface MemberRoleService {

	MemberRole findMemberRoleById(Long id);
	
	List<MemberRole> findAllMemberRoles();
	
	MemberRole saveMemberRole(MemberRole memberRole);
	
	void deleteMemberRole(Long id);
	
	List<MemberRole> findMemberRolesByBusinessUnitId(Long id);

	List<String[]> findMemberRoleRevisionsById(Long mrId);

	List<String[]> findAllMemberRoleRevisions();
	
	List<MemberRole> findMRsByUserLoginAndEdiAuthorizedTrue(String userLogin);

	boolean checkIfCurrentUserEdiAuthorized(Long buId);
	
	boolean checkIfCurrentUserCanCreateFC(Long foId);

	boolean checkIfCurrentUserCanUpdateDeleteFC(Long fcId);
	
}
