package ca.gc.tri_agency.granting_data.service;

import java.util.List;

import ca.gc.tri_agency.granting_data.model.SystemFundingCycle;
import ca.gc.tri_agency.granting_data.model.SystemFundingOpportunity;
import ca.gc.tri_agency.granting_data.model.file.FundingCycleDatasetRow;

public interface SystemFundingCycleService {
	
	SystemFundingCycle findSystemFundingCycleById(Long id);
	
	List<SystemFundingCycle> findAllSystemFundingCycles();

	List<SystemFundingCycle> findSFCsBySFOid(Long foId);
	
//	List<SystemFundingCycle> findSFCsByExtIdAndSFOid(String extId, Long sfoId);
	
	SystemFundingCycle registerSystemFundingCycle(FundingCycleDatasetRow row, SystemFundingOpportunity targetSfo);
	
	
	
}
