package ca.gc.tri_agency.granting_data.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.RevisionType;
import org.hibernate.envers.query.AuditQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.data.history.Revision;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.gc.tri_agency.granting_data.model.MemberRole;
import ca.gc.tri_agency.granting_data.model.auditing.UsernameRevisionEntity;
import ca.gc.tri_agency.granting_data.repo.MemberRoleRepository;
import ca.gc.tri_agency.granting_data.security.annotations.AdminOnly;
import ca.gc.tri_agency.granting_data.service.MemberRoleService;

@Service
public class MemberRoleServiceImpl implements MemberRoleService {

	@PersistenceContext
	private EntityManager em;

	private MemberRoleRepository mrRepo;

	@Autowired
	public MemberRoleServiceImpl(MemberRoleRepository memberRoleRepo) {
		this.mrRepo = memberRoleRepo;
	}

	@Override
	public MemberRole findMemberRoleById(Long id) {
		return mrRepo.findById(id).orElseThrow(() -> new DataRetrievalFailureException("That Member Role does not exist"));
	}

	@Override
	public List<MemberRole> findAllMemberRoles() {
		return mrRepo.findAll();
	}

	@Override
	@AdminOnly
	public MemberRole saveMemberRole(MemberRole memberRole) {
		return mrRepo.save(memberRole);
	}

	@Override
	@AdminOnly
	public void deleteMemberRole(Long id) {
		mrRepo.deleteById(id);
	}

	@Override
	public List<MemberRole> findMemberRolesByBusinessUnitId(Long buId) {
		return mrRepo.findByBusinessUnitIdOrderByUserLogin(buId);
	}

	private String[] createAuditedMemberRoleStrArr(MemberRole mr, String revType, UsernameRevisionEntity revEntity,
			String roleName, String buName) {
		switch (revType) {
		case "ADD":
			revType = "INSERT";
			break;
		case "MOD":
			revType = "UPDATE";
			break;
		case "DEL":
			revType = "DELETE";
		}

		return new String[] { revEntity.getUsername(), revType, mr.getUserLogin(),
				(mr.getEdiAuthorized() != null) ? mr.getEdiAuthorized().toString().toUpperCase() : null, roleName,
				buName, revEntity.getRevTimestamp().toString() };
	}

	@Override
	@AdminOnly
	@Transactional(readOnly = true)
	public List<String[]> findMemberRoleRevisionsById(Long mrId) {
		List<String[]> auditedArrList = new ArrayList<>();

		List<Revision<Long, MemberRole>> revisionList = mrRepo.findRevisions(mrId).getContent();
		revisionList.forEach(rev -> {
			MemberRole mr = rev.getEntity();
			String revType = rev.getMetadata().getRevisionType().toString();
			UsernameRevisionEntity revEntity = (UsernameRevisionEntity) rev.getMetadata().getDelegate();

			String roleName;
			try {
				roleName = mr.getRole().getLocalizedAttribute("name");
			} catch (NullPointerException npe) {
				roleName = null;
			}

			String buName;
			try {
				buName = mr.getBusinessUnit().getLocalizedAttribute("name");
			} catch (NullPointerException npe) {
				buName = null;
			}

			auditedArrList.add(createAuditedMemberRoleStrArr(mr, revType, revEntity, roleName, buName));
		});

		auditedArrList.sort(Comparator.comparing(strArr -> strArr[strArr.length - 1]));
		return auditedArrList;
	}

	@Override
	@AdminOnly
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<String[]> findAllMemberRoleRevisions() {
		AuditReader auditReader = AuditReaderFactory.get(em);
		AuditQuery auditQuery = auditReader.createQuery().forRevisionsOfEntity(MemberRole.class, false, true);

		List<String[]> auditedArrList = new ArrayList<>();

		List<Object[]> revisionList = auditQuery.getResultList();
		revisionList.forEach(rev -> {
			MemberRole mr = (MemberRole) rev[0];
			UsernameRevisionEntity revEntity = (UsernameRevisionEntity) rev[1];
			RevisionType revType = (RevisionType) rev[2];

			String roleName;
			try {
				roleName = mr.getRole().getLocalizedAttribute("name");
			} catch (NullPointerException npe) {
				roleName = null;
			}

			String buName;
			try {
				buName = mr.getBusinessUnit().getLocalizedAttribute("name");
			} catch (NullPointerException npe) {
				buName = null;
			}

			auditedArrList.add(createAuditedMemberRoleStrArr(mr, revType.toString(), revEntity, roleName, buName));
		});

		auditedArrList.sort(Comparator.comparing(strArr -> strArr[strArr.length - 1]));
		return auditedArrList;
	}

}
