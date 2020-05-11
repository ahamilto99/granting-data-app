package ca.gc.tri_agency.granting_data.service;

import java.util.List;

import ca.gc.tri_agency.granting_data.model.FundingOpportunity;

public interface FundingOpportunityService {
	
	FundingOpportunity findFundingOpportunityById(Long foId);
	
	List<FundingOpportunity> findAllFundingOpportunities();
	
	List<FundingOpportunity> findFundingOpportunitiesByNameEn(String nameEn);
	
	List<FundingOpportunity> findFundingOpportunitiesByLeadAgencyId(Long leadAgencyId);
	
	List<FundingOpportunity> findFundingOpportunitiesByBusinessUnitId(Long buId);
	
	FundingOpportunity saveFundingOpportunity(FundingOpportunity fo);
	
	void setFundingOpportunityLeadContributor(Long foId, String dn);

}
