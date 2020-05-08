package ca.gc.tri_agency.granting_data.service;

import java.util.List;

import ca.gc.tri_agency.granting_data.model.FundingOpportunity;

public interface DataAccessService {

	List<FundingOpportunity> getAllFundingOpportunities();

	List<FundingOpportunity> getFoByNameEn(String nameEn);

	List<FundingOpportunity> getAgencyFundingOpportunities(long id);

	void createFo(FundingOpportunity fo);

}
