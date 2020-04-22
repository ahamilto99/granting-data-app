package ca.gc.tri_agency.granting_data.service;

import javax.validation.Valid;

import ca.gc.tri_agency.granting_data.ldap.ADUser;
import ca.gc.tri_agency.granting_data.model.FundingCycle;
import ca.gc.tri_agency.granting_data.model.FundingOpportunity;
import ca.gc.tri_agency.granting_data.model.GrantingCapability;

public interface RestrictedDataService {
	FundingOpportunity saveFundingOpportunity(FundingOpportunity targetUpdate);

	void setFoLeadContributor(long foId, String leadUserDn);

	void setFoLeadContributor(long foId, ADUser adUser);

	FundingCycle createOrUpdateFundingCycle(FundingCycle command);

	GrantingCapability createGrantingCapability(@Valid GrantingCapability command);

	FundingCycle updateFc(FundingCycle command, FundingCycle target);

}
