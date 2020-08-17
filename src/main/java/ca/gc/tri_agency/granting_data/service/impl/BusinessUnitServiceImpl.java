package ca.gc.tri_agency.granting_data.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.envers.RevisionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.gc.tri_agency.granting_data.model.Agency;
import ca.gc.tri_agency.granting_data.model.BusinessUnit;
import ca.gc.tri_agency.granting_data.model.auditing.UsernameRevisionEntity;
import ca.gc.tri_agency.granting_data.model.projection.BusinessUnitProjection;
import ca.gc.tri_agency.granting_data.repo.BusinessUnitRepository;
import ca.gc.tri_agency.granting_data.security.SecurityUtils;
import ca.gc.tri_agency.granting_data.security.annotations.AdminOnly;
import ca.gc.tri_agency.granting_data.service.ApplicationParticipationService;
import ca.gc.tri_agency.granting_data.service.AuditService;
import ca.gc.tri_agency.granting_data.service.BusinessUnitService;
import ca.gc.tri_agency.granting_data.service.MemberRoleService;

@Service
public class BusinessUnitServiceImpl implements BusinessUnitService {

	private BusinessUnitRepository buRepo;

	private MemberRoleService mrService;

	private ApplicationParticipationService apService;

	private AuditService auditService;

	@Autowired
	public BusinessUnitServiceImpl(BusinessUnitRepository buRepo, MemberRoleService mrService, ApplicationParticipationService apService,
			AuditService auditService) {
		this.buRepo = buRepo;
		this.mrService = mrService;
		this.apService = apService;
		this.auditService = auditService;
	}

	@Override
	public BusinessUnit findBusinessUnitById(Long id) {
		return buRepo.findById(id).orElseThrow(() -> new DataRetrievalFailureException("BusinessUnit id=" + id + " does not exist"));
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
	@Override
	public List<String[]> findBusinessUnitRevisionsById(Long buId) {
		return convertAuditResults(auditService.findRevisionsForOneBU(buId));
	}

	@AdminOnly
	@Override
	public List<String[]> findAllBusinessUnitRevisions() {
		return convertAuditResults(auditService.findRevisionsForAllBUs());
	}

	@Transactional(readOnly = true)
	@Override
	public Map<String, Long> findEdiAppPartDataForAuthorizedBUMember(Long buId) throws AccessDeniedException {
		if (mrService.checkIfCurrentUserEdiAuthorized(buId) == false) {
			throw new AccessDeniedException(
					SecurityUtils.getCurrentUsername() + " does not have permission to view EDI data for BU id=" + buId);
		}

		Long[] ediArr = apService.findAppPartEdiDataForBu(buId);

		Map<String, Long> ediMap = new HashMap<>();
		ediMap.put("numIndigenousApps", ediArr[0]);
		ediMap.put("numVisMinorityApps", ediArr[1]);
		ediMap.put("numDisabledApps", ediArr[2]);
		ediMap.put("numFemaleApps", ediArr[3]);
		ediMap.put("numMaleApps", ediArr[4]);
		ediMap.put("numNonBinaryApps", ediArr[5]);
		ediMap.put("numApps", ediArr[6]);

		return ediMap;
	}

	@Override
	public BusinessUnitProjection findBusinessUnitName(Long buId) {
		return buRepo.findName(buId).orElseThrow(() -> new DataRetrievalFailureException("BusinessUnit id=" + buId + " does not exist"));
	}

	@Override
	public List<BusinessUnitProjection> findResultsForBrowseViewBU(Long buId) {
		List<BusinessUnitProjection> buProjections = buRepo.findForViewBU(buId);

		if (buProjections.isEmpty()) {
			throw new DataRetrievalFailureException("BusinessUnit id=" + buId + " does not exist");
		}

		return buProjections;
	}

	@Override
	public BusinessUnit findBusinessUnitWithAgency(Long buId) {
		BusinessUnit bu =  buRepo.findWithAgency(buId);
		
		if (bu == null) {
				throw new DataRetrievalFailureException("BusinessUnit id=" + buId + " does not exist");
		}
		
		return bu;
	}

}
