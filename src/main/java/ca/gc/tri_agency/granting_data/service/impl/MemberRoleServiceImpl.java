package ca.gc.tri_agency.granting_data.service.impl;

import java.util.List;

import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Service;

import ca.gc.tri_agency.granting_data.model.MemberRole;
import ca.gc.tri_agency.granting_data.repo.MemberRoleRepository;
import ca.gc.tri_agency.granting_data.security.annotations.AdminOnly;
import ca.gc.tri_agency.granting_data.service.MemberRoleService;

@Service
public class MemberRoleServiceImpl implements MemberRoleService {

	private MemberRoleRepository memberRoleRepo;

	public MemberRoleServiceImpl(MemberRoleRepository memberRoleRepo) {
		this.memberRoleRepo = memberRoleRepo;
	}

	@Override
	public MemberRole findMemberRoleById(Long id) {
		return memberRoleRepo.findById(id)
				.orElseThrow(() -> new DataRetrievalFailureException("That Member Role does not exist"));
	}

	@Override
	public List<MemberRole> findAllMemberRoles() {
		return memberRoleRepo.findAll();
	}

	@Override
	@AdminOnly
	public MemberRole saveMemberRole(MemberRole memberRole) {
		return memberRoleRepo.save(memberRole);
	}

	@Override
	@AdminOnly
	public void deleteMemberRole(Long id) {
		memberRoleRepo.deleteById(id);
	}

}
