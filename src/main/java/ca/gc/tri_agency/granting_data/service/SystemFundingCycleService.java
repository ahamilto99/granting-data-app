package ca.gc.tri_agency.granting_data.service;

import java.util.List;

import ca.gc.tri_agency.granting_data.model.SystemFundingCycle;
import ca.gc.tri_agency.granting_data.model.SystemFundingOpportunity;
import ca.gc.tri_agency.granting_data.model.file.FundingCycleDatasetRow;
import ca.gc.tri_agency.granting_data.model.projection.SystemFundingCycleProjection;

public interface SystemFundingCycleService {
	
	SystemFundingCycle findSystemFundingCycleById(Long id);
	
	List<SystemFundingCycleProjection> findAllSystemFundingCycleExtIds();

	List<SystemFundingCycle> findSFCsBySFOid(Long sfoId);
	
	SystemFundingCycle registerSystemFundingCycle(FundingCycleDatasetRow row, SystemFundingOpportunity targetSfo);
	
	List<SystemFundingCycleProjection> findSystemFundingCyclesByLinkedFundingOpportunity(Long foId);
	
}
