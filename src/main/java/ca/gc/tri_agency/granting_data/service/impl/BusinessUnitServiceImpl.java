package ca.gc.tri_agency.granting_data.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
import org.springframework.stereotype.Service;

import ca.gc.tri_agency.granting_data.model.Agency;
import ca.gc.tri_agency.granting_data.model.BusinessUnit;
import ca.gc.tri_agency.granting_data.model.auditing.UsernameRevisionEntity;
import ca.gc.tri_agency.granting_data.repo.BusinessUnitRepository;
import ca.gc.tri_agency.granting_data.security.annotations.AdminOnly;
import ca.gc.tri_agency.granting_data.service.BusinessUnitService;
import ca.gc.tri_agency.granting_data.service.MemberRoleService;

@Service
public class BusinessUnitServiceImpl implements BusinessUnitService {

	private BusinessUnitRepository buRepo;

	private MemberRoleService mrService;

	@PersistenceUnit
	private EntityManagerFactory emf;

	@Autowired
	public BusinessUnitServiceImpl(BusinessUnitRepository buRepo, MemberRoleService mrService) {
		this.buRepo = buRepo;
		this.mrService = mrService;
	}

	@Override
	public BusinessUnit findBusinessUnitById(Long id) {
		return buRepo.findById(id).orElseThrow(() -> new DataRetrievalFailureException("That Business Unit does not exist"));
	}

	@Override
	public List<BusinessUnit> findAllBusinessUnits() {
		return buRepo.findAll();
	}

	@Override
	public List<BusinessUnit> findAllBusinessUnitsByAgency(Agency agency) {
		return buRepo.findAllByAgency(agency);
	}

	@AdminOnly
	@Override
	public BusinessUnit saveBusinessUnit(BusinessUnit bu) {
		return buRepo.save(bu);
	}

	private List<String[]> convertAuditResults(List<Object[]> revisionList) {
		List<String[]> auditedArrList = new ArrayList<>();

		revisionList.forEach(objArr -> {
			BusinessUnit bu = (BusinessUnit) objArr[0];
			UsernameRevisionEntity revEntity = (UsernameRevisionEntity) objArr[1];
			RevisionType revType = (RevisionType) objArr[2];
			auditedArrList.add(new String[] { bu.getId().toString(), revEntity.getUsername(), revType.toString(), bu.getNameEn(),
					bu.getNameFr(), bu.getAcronymEn(), bu.getAcronymFr(), revEntity.getRevTimestamp().toString() });
		});

		return auditedArrList;
	}

	@AdminOnly
	@SuppressWarnings("unchecked")
	@Override
	public List<String[]> findBusinessUnitRevisionsById(Long buId) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		AuditReader auditReader = AuditReaderFactory.get(em);
		AuditQuery auditQuery = auditReader.createQuery().forRevisionsOfEntity(BusinessUnit.class, false, true);
		auditQuery.add(AuditEntity.id().eq(buId));
		auditQuery.addOrder(AuditEntity.revisionProperty("id").asc());

		List<Object[]> revisionList = auditQuery.getResultList();

		em.getTransaction().commit();
		em.close();

		return convertAuditResults(revisionList);
	}

	@AdminOnly
	@SuppressWarnings("unchecked")
	@Override
	public List<String[]> findAllBusinessUnitRevisions() {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		AuditReader auditReader = AuditReaderFactory.get(em);
		AuditQuery auditQuery = auditReader.createQuery().forRevisionsOfEntity(BusinessUnit.class, false, true);
		auditQuery.addOrder(AuditEntity.revisionProperty("id").asc());

		List<Object[]> revisionList = auditQuery.getResultList();

		em.getTransaction().commit();
		em.close();

		return convertAuditResults(revisionList);
	}

	@Override
	public Map<String, Long> findEdiAppPartDataForAuthorizedBUMember(Long buId) {
//		TODO: COMPLETE IMPLEMENTATION
		return null;
	}

}
