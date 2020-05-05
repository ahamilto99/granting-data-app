package ca.gc.tri_agency.granting_data.service;

import ca.gc.tri_agency.granting_data.ldap.ADUser;
import ca.gc.tri_agency.granting_data.model.FundingOpportunity;

public interface RestrictedDataService {
	FundingOpportunity saveFundingOpportunity(FundingOpportunity targetUpdate);

	void setFoLeadContributor(long foId, String leadUserDn);

	void setFoLeadContributor(long foId, ADUser adUser);

}
