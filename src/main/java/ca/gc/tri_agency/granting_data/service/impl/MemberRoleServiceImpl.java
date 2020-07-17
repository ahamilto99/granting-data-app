package ca.gc.tri_agency.granting_data.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.RevisionType;
import org.hibernate.envers.query.AuditEntity;
import org.hibernate.envers.query.AuditQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import ca.gc.tri_agency.granting_data.model.MemberRole;
import ca.gc.tri_agency.granting_data.model.auditing.UsernameRevisionEntity;
import ca.gc.tri_agency.granting_data.repo.MemberRoleRepository;
import ca.gc.tri_agency.granting_data.security.SecurityUtils;
import ca.gc.tri_agency.granting_data.security.annotations.AdminOnly;
import ca.gc.tri_agency.granting_data.service.MemberRoleService;

@Service
public class MemberRoleServiceImpl implements MemberRoleService {

	@PersistenceUnit
	private EntityManagerFactory emf;

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

	private List<String[]> convertAuditResults(List<Object[]> revisionList) {
		List<String[]> auditedArrList = new ArrayList<>();

		revisionList.forEach(rev -> {
			MemberRole mr = (MemberRole) rev[0];
			UsernameRevisionEntity revEntity = (UsernameRevisionEntity) rev[1];
			RevisionType revType = (RevisionType) rev[2];

			auditedArrList.add(new String[] { mr.getId().toString(), revEntity.getUsername(), revType.toString(), mr.getUserLogin(),
					(mr.getEdiAuthorized() != null) ? mr.getEdiAuthorized().toString().toUpperCase() : null,
					(mr.getRole() != null) ? mr.getRole().getId().toString() : null,
					(mr.getBusinessUnit() != null) ? mr.getBusinessUnit().getId().toString() : null,
					revEntity.getRevTimestamp().toString() });
		});

		return auditedArrList;
	}

	@Override
	@AdminOnly
	@SuppressWarnings("unchecked")
	public List<String[]> findMemberRoleRevisionsById(Long mrId) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		AuditReader auditReader = AuditReaderFactory.get(em);

		AuditQuery auditQuery = auditReader.createQuery().forRevisionsOfEntity(MemberRole.class, false, true);
		auditQuery.add(AuditEntity.id().eq(mrId));
		auditQuery.addOrder(AuditEntity.revisionProperty("id").asc());
		List<Object[]> revisionList = auditQuery.getResultList();

		em.getTransaction().commit();
		em.close();

		return convertAuditResults(revisionList);
	}

	@Override
	@AdminOnly
	@SuppressWarnings("unchecked")
	public List<String[]> findAllMemberRoleRevisions() {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		AuditReader auditReader = AuditReaderFactory.get(em);

		AuditQuery auditQuery = auditReader.createQuery().forRevisionsOfEntity(MemberRole.class, false, true);
		auditQuery.addOrder(AuditEntity.revisionProperty("id").asc());
		List<Object[]> revisionList = auditQuery.getResultList();

		em.getTransaction().commit();
		em.close();

		return convertAuditResults(revisionList);
	}

	@Override
	public List<MemberRole> findMRsByUserLoginAndEdiAuthorizedTrue(String userLogin) {
		return mrRepo.findByUserLoginAndEdiAuthorizedTrue(userLogin);
	}

	@Override
	public void checkIfCurrentUserEdiAuthorized(Long buId) throws AccessDeniedException {
		try {
			mrRepo.findEdiAuthorizedByUserLoginBuId(SecurityUtils.getCurrentUsername(), buId).getId();
		} catch (NullPointerException npe) {
			throw new AccessDeniedException("The logged-in user does not have permission to view the EDI data for the"
					+ " Business Unit with id=" + buId.toString());
		}
	}

}
