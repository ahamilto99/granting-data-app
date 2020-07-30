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
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.gc.tri_agency.granting_data.model.MemberRole;
import ca.gc.tri_agency.granting_data.model.auditing.UsernameRevisionEntity;
import ca.gc.tri_agency.granting_data.model.projection.MemberRoleProjection;
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

	@Transactional(readOnly = true)
	@Override
	public List<MemberRole> findMRsByUserLoginAndEdiAuthorizedTrue(String userLogin) {
		return mrRepo.findByUserLoginAndEdiAuthorizedTrue(userLogin);
	}

	@Transactional(readOnly = true)
	@Override
	public boolean checkIfCurrentUserEdiAuthorized(Long buId) {
//		Can't use SecurityUtils' hasRole(...) b/c tests don't mock an LDAP user, i.e. tests fail when
//		using that method b/c we can't cast a User object to a LdapUserDetails object.
		if (SecurityContextHolder.getContext().getAuthentication().getAuthorities()
				.contains(new SimpleGrantedAuthority("ROLE_MDM ADMIN"))) {
			// an admin user can access the EDI data for all BUs
			return true;
		}

		MemberRoleProjection mrProjection = mrRepo.findEdiAuthorizedByUserLoginBuId(SecurityUtils.getCurrentUsername(), buId);

		return mrProjection != null ? mrProjection.getEdiAuthorized() : false;
	}

	@Transactional(readOnly = true)
	@Override
	public boolean checkIfCurrentUserCanCreateFCs(String userLogin, Long foId) {
		if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(
				new SimpleGrantedAuthority("ROLE_MDM ADMIN"))) {
			return true;
		}
		
		return mrRepo.findIfCanCreateFC(userLogin, foId) != null;
	}

}
