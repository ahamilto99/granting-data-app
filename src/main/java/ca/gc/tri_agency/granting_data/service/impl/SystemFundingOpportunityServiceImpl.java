package ca.gc.tri_agency.granting_data.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.envers.RevisionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Service;

import ca.gc.tri_agency.granting_data.model.FundingOpportunity;
import ca.gc.tri_agency.granting_data.model.GrantingSystem;
import ca.gc.tri_agency.granting_data.model.SystemFundingOpportunity;
import ca.gc.tri_agency.granting_data.model.auditing.UsernameRevisionEntity;
import ca.gc.tri_agency.granting_data.model.file.FundingCycleDatasetRow;
import ca.gc.tri_agency.granting_data.model.projection.SystemFundingOpportunityProjection;
import ca.gc.tri_agency.granting_data.model.util.Utility;
import ca.gc.tri_agency.granting_data.repo.SystemFundingOpportunityRepository;
import ca.gc.tri_agency.granting_data.security.annotations.AdminOnly;
import ca.gc.tri_agency.granting_data.service.AuditService;
import ca.gc.tri_agency.granting_data.service.FundingOpportunityService;
import ca.gc.tri_agency.granting_data.service.SystemFundingOpportunityService;

@Service
public class SystemFundingOpportunityServiceImpl implements SystemFundingOpportunityService {

	private static final String ENTITY_TYPE = "SystemFundingOpportunity";

	private SystemFundingOpportunityRepository sfoRepo;

	private FundingOpportunityService foService;

	private AuditService auditService;

	@Autowired
	public SystemFundingOpportunityServiceImpl(SystemFundingOpportunityRepository sfoRepo, FundingOpportunityService foService,
			AuditService auditService) {
		this.sfoRepo = sfoRepo;
		this.foService = foService;
		this.auditService = auditService;
	}

	@Override
	public SystemFundingOpportunity findSystemFundingOpportunityById(Long id) {
		return sfoRepo.findById(id).orElseThrow(() -> new DataRetrievalFailureException(Utility.returnNotFoundMsg(ENTITY_TYPE, id)));
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
		SystemFundingOpportunity sfo = findSystemFundingOpportunityById(sfoId);
		FundingOpportunity fo = foService.findFundingOpportunityById(foId);
		
		sfo.setLinkedFundingOpportunity(fo);
		
		return 1;
	}

	@AdminOnly
	@Transactional
	@Override
	public int unlinkSystemFundingOpportunity(Long sfoId, Long foId) {
		SystemFundingOpportunity sfo = findSystemFundingOpportunityById(sfoId);

		if (sfo.getLinkedFundingOpportunity().getId() != foId) {
			throw new DataRetrievalFailureException(
					"SystemFundingOpportunity id=" + sfoId + " is not linked with Funding Opportunity id=" + foId);
		}

		sfo.setLinkedFundingOpportunity(null);

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

			auditArrList.add(new String[] { sfo.getId().toString(), revEntity.getUsername(), revType.toString(), sfo.getExtId(),
					sfo.getNameEn(), sfo.getNameFr(),
					(sfo.getGrantingSystem() != null) ? sfo.getGrantingSystem().getId().toString() : null,
					(sfo.getLinkedFundingOpportunity() != null) ? sfo.getLinkedFundingOpportunity().getId().toString()
							: null,
					revEntity.getRevTimestamp().toString() });
		});

		return auditArrList;
	}

	@AdminOnly
	@Override
	public List<String[]> findAllSystemFundingOpportunityRevisions() {
		return convertAuditResults(auditService.findRevisionsForAllSFOs());
	}

	@AdminOnly
	@Override
	public List<String[]> findSystemFundingOpportunityRevisionById(Long sfoId) {
		return convertAuditResults(auditService.findRevisionsForOneSFO(sfoId));
	}

	@Override
	public List<SystemFundingOpportunity> findSFOsByLinkedFundingOpportunityBusinessUnitIdIn(List<Long> targetBuIds) {
		return sfoRepo.findByLinkedFundingOpportunityBusinessUnitIdIn(targetBuIds);
	}

	@Override
	public SystemFundingOpportunityProjection findSystemFundingOpportunityAndLinkedFOName(Long sfoId) {
		return sfoRepo.findSFOAndFOName(sfoId)
				.orElseThrow(() -> new DataRetrievalFailureException(Utility.returnNotFoundMsg(ENTITY_TYPE, sfoId)));

	}

	@Override
	public List<SystemFundingOpportunityProjection> findAllSystemFundingOpportunitiesAndLinkedFONameAndGSysName() {
		return sfoRepo.findAllSFOsAndFONameAndGSysName();
	}

	@Override
	public SystemFundingOpportunityProjection findSystemFundingOpportunityNameAndLinkedFOName(Long sfoId) {
		return sfoRepo.findSFONameAndFOName(sfoId)
				.orElseThrow(() -> new DataRetrievalFailureException("Either: " + Utility.returnNotFoundMsg(ENTITY_TYPE, sfoId)
						+ " or " + ENTITY_TYPE + " id=" + sfoId + " does not have a linked FundingOpportunity"));
	}

}
