package ca.gc.tri_agency.granting_data.service;

import java.util.List;

import ca.gc.tri_agency.granting_data.model.FundingCycle;

public interface FundingCycleService {

	FundingCycle findFundingCycleById(Long id);
	
	List<FundingCycle> findAllFundingCycles();
}
