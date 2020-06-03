package ca.gc.tri_agency.granting_data.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.transaction.Transactional;

import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.RevisionType;
import org.hibernate.envers.query.AuditEntity;
import org.hibernate.envers.query.AuditQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Service;

import ca.gc.tri_agency.granting_data.model.FundingOpportunity;
import ca.gc.tri_agency.granting_data.model.GrantingSystem;
import ca.gc.tri_agency.granting_data.model.SystemFundingOpportunity;
import ca.gc.tri_agency.granting_data.model.auditing.UsernameRevisionEntity;
import ca.gc.tri_agency.granting_data.model.file.FundingCycleDatasetRow;
import ca.gc.tri_agency.granting_data.repo.SystemFundingOpportunityRepository;
import ca.gc.tri_agency.granting_data.security.annotations.AdminOnly;
import ca.gc.tri_agency.granting_data.service.FundingOpportunityService;
import ca.gc.tri_agency.granting_data.service.SystemFundingOpportunityService;

@Service
public class SystemFundingOpportunityServiceImpl implements SystemFundingOpportunityService {

	private SystemFundingOpportunityRepository sfoRepo;

	private FundingOpportunityService foService;

	@PersistenceUnit
	private EntityManagerFactory emf;

	@Autowired
	public SystemFundingOpportunityServiceImpl(SystemFundingOpportunityRepository sfoRepo, FundingOpportunityService foService) {
		this.sfoRepo = sfoRepo;
		this.foService = foService;
	}

	@Override
	public SystemFundingOpportunity findSystemFundingOpportunityById(Long id) {
		return sfoRepo.findById(id).orElseThrow(
				() -> new DataRetrievalFailureException("That System Funding Opportunity does not exist"));
	}

	@Override
	public List<SystemFundingOpportunity> findAllSystemFundingOpportunities() {
		return sfoRepo.findAll();
	}

	@Override
	public List<SystemFundingOpportunity> findSystemFundingOpportunitiesByLinkedFOid(Long foId) {
		return sfoRepo.findByLinkedFundingOpportunityId(foId);
	}

	@Override
	public List<SystemFundingOpportunity> findSystemFundingOpportunitiesByExtId(String extId) {
		return sfoRepo.findByExtId(extId);
	}

	@Override
	public List<SystemFundingOpportunity> findSystemFundingOpportunitiesByNameEn(String nameEn) {
		return sfoRepo.findByNameEn(nameEn);
	}

	@AdminOnly
	@Transactional
	@Override
	public int linkSystemFundingOpportunity(Long sfoId, Long foId) {
		SystemFundingOpportunity systemFo = findSystemFundingOpportunityById(sfoId);
		FundingOpportunity fo = foService.findFundingOpportunityById(foId);
		systemFo.setLinkedFundingOpportunity(fo);
		saveSystemFundingOpportunity(systemFo);
		return 1;
	}

	@AdminOnly
	@Transactional
	@Override
	public int unlinkSystemFundingOpportunity(Long sfoId, Long foId) {
		SystemFundingOpportunity systemFo = findSystemFundingOpportunityById(sfoId);
		FundingOpportunity fo = foService.findFundingOpportunityById(foId);
		if (systemFo.getLinkedFundingOpportunity() != fo) {
			throw new DataRetrievalFailureException(
					"System Funding Opportunity is not linked with that Funding Opportunity");
		}
		systemFo.setLinkedFundingOpportunity(null);
		saveSystemFundingOpportunity(systemFo);
		return 1;
	}

	@AdminOnly
	@Override
	public SystemFundingOpportunity registerSystemFundingOpportunity(FundingCycleDatasetRow row, GrantingSystem targetSystem) {
		SystemFundingOpportunity retval = new SystemFundingOpportunity();
		retval.setExtId(row.getFoCycle());
		retval.setNameEn(row.getProgramNameEn());
		retval.setNameFr(row.getProgramNameFr());
		retval.setGrantingSystem(targetSystem);
		retval = saveSystemFundingOpportunity(retval);
		return retval;
	}

	@AdminOnly
	@Override
	public SystemFundingOpportunity saveSystemFundingOpportunity(SystemFundingOpportunity sfo) {
		return sfoRepo.save(sfo);
	}

	private List<String[]> convertAuditResults(List<Object[]> revisionList) {
		List<String[]> auditArrList = new ArrayList<>();

		revisionList.forEach(objArr -> {
			SystemFundingOpportunity sfo = (SystemFundingOpportunity) objArr[0];
			UsernameRevisionEntity revEntity = (UsernameRevisionEntity) objArr[1];
			RevisionType revType = (RevisionType) objArr[2];

			auditArrList.add(new String[] { sfo.getId().toString(), revEntity.getUsername(), revType.toString(),
					sfo.getNameEn(), sfo.getNameFr(), sfo.getExtId(),
					(sfo.getGrantingSystem() != null) ? sfo.getGrantingSystem().getId().toString() : null,
					(sfo.getLinkedFundingOpportunity() != null)
							? sfo.getLinkedFundingOpportunity().getId().toString()
							: null,
					revEntity.getRevTimestamp().toString() });
		});

		return auditArrList;
	}

	@AdminOnly
	@SuppressWarnings("unchecked")
	@Override
	public List<String[]> findAllSystemFundingOpportunityRevisions() {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		AuditReader auditReader = AuditReaderFactory.get(em);

		AuditQuery auditQuery = auditReader.createQuery().forRevisionsOfEntity(SystemFundingOpportunity.class, false, true);
		auditQuery.addOrder(AuditEntity.revisionProperty("id").asc());
		List<Object[]> revisionList = auditQuery.getResultList();

		em.getTransaction().commit();
		em.close();

		return convertAuditResults(revisionList);
	}

	@AdminOnly
	@SuppressWarnings("unchecked")
	@Override
	public List<String[]> findSystemFundingOpportunityRevisionById(Long sfoId) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		AuditReader auditReader = AuditReaderFactory.get(em);

		AuditQuery auditQuery = auditReader.createQuery().forRevisionsOfEntity(SystemFundingOpportunity.class, false, true);
		auditQuery.add(AuditEntity.id().eq(sfoId));
		auditQuery.addOrder(AuditEntity.revisionProperty("id").asc());
		List<Object[]> revisionList = auditQuery.getResultList();

		em.getTransaction().commit();
		em.close();

		return convertAuditResults(revisionList);
	}

}
