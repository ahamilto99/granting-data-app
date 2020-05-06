package ca.gc.tri_agency.granting_data.service;

import java.util.List;

import ca.gc.tri_agency.granting_data.model.SystemFundingOpportunity;

public interface SystemFundingOpportunityService {

	SystemFundingOpportunity findSystemFundingOpportunityById(Long id);
	
	List<SystemFundingOpportunity> findAllSystemFundingOpportunities();
	
	List<SystemFundingOpportunity> findSystemFundingOpportunitiesByLinkedFOid(Long foId);
	
	List<SystemFundingOpportunity> findSystemFundingOpportunitiesByExtId(String extId);
	
	List<SystemFundingOpportunity> findSystemFundingOpportunitiesByNameEn(String nameEn);
	
}
