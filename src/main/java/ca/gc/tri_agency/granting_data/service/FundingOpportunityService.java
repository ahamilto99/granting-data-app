package ca.gc.tri_agency.granting_data.service;

import java.util.List;
import java.util.Map;

import ca.gc.tri_agency.granting_data.form.FundingOpportunityFilterForm;
import ca.gc.tri_agency.granting_data.model.Agency;
import ca.gc.tri_agency.granting_data.model.FundingOpportunity;
import ca.gc.tri_agency.granting_data.model.GrantingSystem;

public interface FundingOpportunityService {
	
	FundingOpportunity findFundingOpportunityById(Long foId);
	
	List<FundingOpportunity> findAllFundingOpportunities();
	
	List<FundingOpportunity> findFundingOpportunitiesByNameEn(String nameEn);
	
	List<FundingOpportunity> findFundingOpportunitiesByBusinessUnitId(Long buId);
	
	FundingOpportunity saveFundingOpportunity(FundingOpportunity fo);
	
	List<FundingOpportunity> getFilteredFundingOpportunities(FundingOpportunityFilterForm filter,
			Map<Long, GrantingSystem> applyMap, Map<Long, List<GrantingSystem>> awardMap);

	List<FundingOpportunity> findFundingOpportunitiesByAgency(Agency agency);
	
	List<String[]> findFundingOpportunityRevisionsById(Long foId);

	List<String[]> findAllFundingOpportunitiesRevisions();

	List<String[]> findGoldenListTableResults();
	
}
