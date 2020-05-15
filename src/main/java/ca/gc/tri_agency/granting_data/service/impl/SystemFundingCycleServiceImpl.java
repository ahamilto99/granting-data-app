package ca.gc.tri_agency.granting_data.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.LongStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.gc.tri_agency.granting_data.model.SystemFundingCycle;
import ca.gc.tri_agency.granting_data.model.SystemFundingOpportunity;
import ca.gc.tri_agency.granting_data.model.file.FundingCycleDatasetRow;
import ca.gc.tri_agency.granting_data.repo.SystemFundingCycleRepository;
import ca.gc.tri_agency.granting_data.service.SystemFundingCycleService;
import ca.gc.tri_agency.granting_data.service.SystemFundingOpportunityService;

@Service
public class SystemFundingCycleServiceImpl implements SystemFundingCycleService {

	private SystemFundingCycleRepository sfcRepo;

	private SystemFundingOpportunityService sfoService; 
	
	@Autowired
	public SystemFundingCycleServiceImpl(SystemFundingCycleRepository sfcRepo, SystemFundingOpportunityService sfoService) {
		this.sfcRepo = sfcRepo;
		this.sfoService = sfoService;
	}

	@Override
	public SystemFundingCycle findSystemFundingCycleById(Long id) {
		return sfcRepo.findById(id)
				.orElseThrow(() -> new DataRetrievalFailureException("That System Funding Cycle does not exist"));
	}

	@Override
	public List<SystemFundingCycle> findAllSystemFundingCycles() {
		return sfcRepo.findAll();
	}

	@Override
	public List<SystemFundingCycle> findSFCsBySFOid(Long sfoId) {
		return sfcRepo.findBySystemFundingOpportunityId(sfoId);
	}

	@Override
	public SystemFundingCycle registerSystemFundingCycle(FundingCycleDatasetRow row, SystemFundingOpportunity targetSfo) {
		SystemFundingCycle retval = new SystemFundingCycle();
		retval.setFiscalYear(row.getCompetitionYear());
		retval.setExtId(row.getFoCycle());
		retval.setSystemFundingOpportunity(targetSfo);
		retval.setNumAppsReceived(row.getNumReceivedApps());
		retval = sfcRepo.save(retval);
		return retval;
	}

	@Transactional(readOnly = true)
	@Override
	public List<SystemFundingCycle> findSystemFundingCyclesByLinkedFundingOpportunity(Long foId) {
		LongStream sfoIds = sfoService.findSystemFundingOpportunitiesByLinkedFOid(foId).stream().mapToLong(SystemFundingOpportunity::getId);
		
		List<SystemFundingCycle> linkedSFCs = new ArrayList<>();
		sfoIds.forEach(sfoId -> linkedSFCs.addAll(findSFCsBySFOid(sfoId)));

		return linkedSFCs;
	}

}
