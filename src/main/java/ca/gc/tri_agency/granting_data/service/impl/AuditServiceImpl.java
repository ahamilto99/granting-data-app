package ca.gc.tri_agency.granting_data.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditEntity;
import org.hibernate.envers.query.AuditQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.gc.tri_agency.granting_data.model.BusinessUnit;
import ca.gc.tri_agency.granting_data.model.FundingOpportunity;
import ca.gc.tri_agency.granting_data.model.MemberRole;
import ca.gc.tri_agency.granting_data.model.SystemFundingOpportunity;
import ca.gc.tri_agency.granting_data.security.annotations.AdminOnly;
import ca.gc.tri_agency.granting_data.service.AuditService;

@AdminOnly
@Service
@Transactional(readOnly = true)
public class AuditServiceImpl implements AuditService {

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> findRevisionsForAllFOs() {
		AuditReader auditReader = AuditReaderFactory.get(em);

		AuditQuery auditQuery = auditReader.createQuery().forRevisionsOfEntity(FundingOpportunity.class, false, true);
		auditQuery.addOrder(AuditEntity.revisionProperty("id").asc());

		return auditQuery.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> findRevisionsForOneFO(Long foId) {
		AuditReader auditReader = AuditReaderFactory.get(em);

		AuditQuery auditQuery = auditReader.createQuery().forRevisionsOfEntity(FundingOpportunity.class, false, true);
		auditQuery.add(AuditEntity.id().eq(foId));
		auditQuery.addOrder(AuditEntity.revisionProperty("id").asc());

		return auditQuery.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> findRevisionsForAllMRs() {
		AuditReader auditReader = AuditReaderFactory.get(em);

		AuditQuery auditQuery = auditReader.createQuery().forRevisionsOfEntity(MemberRole.class, false, true);
		auditQuery.addOrder(AuditEntity.revisionProperty("id").asc());
		return auditQuery.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> findRevisionsForOneMR(Long mrId) {
		AuditReader auditReader = AuditReaderFactory.get(em);

		AuditQuery auditQuery = auditReader.createQuery().forRevisionsOfEntity(MemberRole.class, false, true);
		auditQuery.add(AuditEntity.id().eq(mrId));
		auditQuery.addOrder(AuditEntity.revisionProperty("id").asc());

		return auditQuery.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> findRevisionsForAllSFOs() {
		AuditReader auditReader = AuditReaderFactory.get(em);

		AuditQuery auditQuery = auditReader.createQuery().forRevisionsOfEntity(SystemFundingOpportunity.class, false, true);
		auditQuery.addOrder(AuditEntity.revisionProperty("id").asc());

		return auditQuery.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> findRevisionsForOneSFO(Long sfoId) {
		AuditReader auditReader = AuditReaderFactory.get(em);

		AuditQuery auditQuery = auditReader.createQuery().forRevisionsOfEntity(SystemFundingOpportunity.class, false, true);
		auditQuery.add(AuditEntity.id().eq(sfoId));
		auditQuery.addOrder(AuditEntity.revisionProperty("id").asc());

		return auditQuery.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> findRevisionsForAllBUs() {
		AuditReader auditReader = AuditReaderFactory.get(em);

		AuditQuery auditQuery = auditReader.createQuery().forRevisionsOfEntity(BusinessUnit.class, false, true);
		auditQuery.addOrder(AuditEntity.revisionProperty("id").asc());

		return auditQuery.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> findRevisionsForOneBU(Long buId) {
		AuditReader auditReader = AuditReaderFactory.get(em);

		AuditQuery auditQuery = auditReader.createQuery().forRevisionsOfEntity(BusinessUnit.class, false, true);
		auditQuery.add(AuditEntity.id().eq(buId));
		auditQuery.addOrder(AuditEntity.revisionProperty("id").asc());

		return auditQuery.getResultList();
	}

}
