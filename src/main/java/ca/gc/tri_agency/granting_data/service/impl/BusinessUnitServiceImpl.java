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

import ca.gc.tri_agency.granting_data.model.Agency;
import ca.gc.tri_agency.granting_data.model.BusinessUnit;
import ca.gc.tri_agency.granting_data.model.auditing.UsernameRevisionEntity;
import ca.gc.tri_agency.granting_data.repo.BusinessUnitRepository;
import ca.gc.tri_agency.granting_data.security.annotations.AdminOnly;
import ca.gc.tri_agency.granting_data.service.BusinessUnitService;

@Service
public class BusinessUnitServiceImpl implements BusinessUnitService {

	private BusinessUnitRepository buRepo;

	@PersistenceContext
	private EntityManager em;

	@Autowired
	public BusinessUnitServiceImpl(BusinessUnitRepository buRepo) {
		this.buRepo = buRepo;
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

	private String[] createAuditedBusinessUnitStringArr(BusinessUnit bu, String revType, UsernameRevisionEntity revEntity) {
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
		
		return new String[] { revEntity.getUsername(), revType, bu.getNameEn(), bu.getNameFr(), bu.getAcronymEn(),
				bu.getAcronymFr(), revEntity.getRevTimestamp().toString() };
	}

	@AdminOnly
	@Override
	public List<String[]> findBusinessUnitRevisionsById(Long buId) {
		List<String[]> auditedArrList = new ArrayList<>();

		List<Revision<Long, BusinessUnit>> revisionList = buRepo.findRevisions(buId).getContent();
		if (!revisionList.isEmpty()) {
			revisionList.forEach(rev -> {
				BusinessUnit bu = rev.getEntity();
				UsernameRevisionEntity revEntity = (UsernameRevisionEntity) rev.getMetadata().getDelegate();
				auditedArrList.add(createAuditedBusinessUnitStringArr(bu,
						rev.getMetadata().getRevisionType().toString(), revEntity));
			});
		}

		auditedArrList.sort(Comparator.comparing(strArr -> strArr[6])); // sorts by timestamp
		return auditedArrList;
	}

	@AdminOnly
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	@Override
	public List<String[]> findAllBusinessUnitRevisions() {
		AuditReader auditReader = AuditReaderFactory.get(em);
		AuditQuery auditQuery = auditReader.createQuery().forRevisionsOfEntity(BusinessUnit.class, false, true);

		List<String[]> auditedArrList = new ArrayList<>();

		List<Object[]> revisionList = auditQuery.getResultList();
		revisionList.forEach(objArr -> {
			BusinessUnit bu = (BusinessUnit) objArr[0];
			UsernameRevisionEntity revEntity = (UsernameRevisionEntity) objArr[1];
			RevisionType revType = (RevisionType) objArr[2];
			auditedArrList.add(createAuditedBusinessUnitStringArr(bu, revType.toString(), revEntity));
		});

		auditedArrList.sort(Comparator.comparing(strArr -> strArr[6]));
		return auditedArrList;
	}

}
