package ca.gc.tri_agency.granting_data.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Service;

import ca.gc.tri_agency.granting_data.model.FundingOpportunity;
import ca.gc.tri_agency.granting_data.model.GrantingSystem;
import ca.gc.tri_agency.granting_data.model.SystemFundingOpportunity;
import ca.gc.tri_agency.granting_data.model.file.FundingCycleDatasetRow;
import ca.gc.tri_agency.granting_data.repo.SystemFundingOpportunityRepository;
import ca.gc.tri_agency.granting_data.security.annotations.AdminOnly;
import ca.gc.tri_agency.granting_data.service.FundingOpportunityService;
import ca.gc.tri_agency.granting_data.service.SystemFundingOpportunityService;

@Service
public class SystemFundingOpportunityServiceImpl implements SystemFundingOpportunityService {

	private SystemFundingOpportunityRepository sfoRepo;
	
	private FundingOpportunityService foService;

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
	
}
