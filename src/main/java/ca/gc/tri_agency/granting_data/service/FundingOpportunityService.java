package ca.gc.tri_agency.granting_data.service;

import java.util.List;

import ca.gc.tri_agency.granting_data.model.BusinessUnit;
import ca.gc.tri_agency.granting_data.model.FundingOpportunity;

public interface FundingOpportunityService {
	
	FundingOpportunity findFundingOpportunityById(Long foId);
	
	List<FundingOpportunity> findAllFundingOpportunities();
	
	FundingOpportunity findFundingOpportunityByNameEn(String nameEn);
	
	FundingOpportunity findFundingOpportunityByLeadAgencyId(Long leadAgencyId);
	
	FundingOpportunity findFundingOpportunityByBusinessUnit(BusinessUnit bu);

}
