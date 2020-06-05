package ca.gc.tri_agency.granting_data.service;

import java.util.List;

import ca.gc.tri_agency.granting_data.model.GrantingSystem;
import ca.gc.tri_agency.granting_data.model.SystemFundingOpportunity;
import ca.gc.tri_agency.granting_data.model.file.FundingCycleDatasetRow;

public interface SystemFundingOpportunityService {

	SystemFundingOpportunity findSystemFundingOpportunityById(Long id);
	
	List<SystemFundingOpportunity> findAllSystemFundingOpportunities();
	
	List<SystemFundingOpportunity> findSystemFundingOpportunitiesByLinkedFOid(Long foId);
	
	List<SystemFundingOpportunity> findSystemFundingOpportunitiesByExtId(String extId);
	
	List<SystemFundingOpportunity> findSystemFundingOpportunitiesByNameEn(String nameEn);
	
	int linkSystemFundingOpportunity(Long sfoId, Long foId);
	
	int unlinkSystemFundingOpportunity(Long sfoId, Long foId);
	
	SystemFundingOpportunity registerSystemFundingOpportunity(FundingCycleDatasetRow row, GrantingSystem targetSystem);
	
	SystemFundingOpportunity saveSystemFundingOpportunity(SystemFundingOpportunity sfo);

	List<String[]> findAllSystemFundingOpportunityRevisions();

	List<String[]> findSystemFundingOpportunityRevisionById(Long sfoId);
	
}
