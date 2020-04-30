package ca.gc.tri_agency.granting_data.service;

import java.util.List;
import java.util.Map;

import ca.gc.tri_agency.granting_data.model.FundingCycle;

public interface FundingCycleService {

	FundingCycle findFundingCycleById(Long id);
	
	List<FundingCycle> findAllFundingCycles();
	
	List<FundingCycle> findFundingCyclesByFundingOpportunityId(Long foId);
	
	List<FundingCycle> findFundingCyclesByFiscalYearId(Long fyId);
	
	Map<String, List<FundingCycle>> findMonthlyFundingCyclesMapByDate(long plusMinusMonth);
}
