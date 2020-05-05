package ca.gc.tri_agency.granting_data.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Service;

import ca.gc.tri_agency.granting_data.ldap.ADUser;
import ca.gc.tri_agency.granting_data.ldap.ADUserService;
import ca.gc.tri_agency.granting_data.model.FundingOpportunity;
import ca.gc.tri_agency.granting_data.repo.FundingOpportunityRepository;
import ca.gc.tri_agency.granting_data.security.annotations.AdminOnly;
import ca.gc.tri_agency.granting_data.service.RestrictedDataService;

@Service
public class RestrictedDataServiceImpl implements RestrictedDataService {

	@Autowired
	private FundingOpportunityRepository foRepo;
	@Autowired
	private ADUserService adUserService;

	@Override
	@AdminOnly
	public FundingOpportunity saveFundingOpportunity(FundingOpportunity targetUpdate) {
		return foRepo.save(targetUpdate);
	}

	@Override
	@AdminOnly
	public void setFoLeadContributor(long foId, String leadUserDn) {
		if (leadUserDn == null) {
			// return;??
		}
		FundingOpportunity foToUpdate = foRepo.findById(foId)
				.orElseThrow(() -> new DataRetrievalFailureException("That Funding Opportunity does not exist"));
		;
		ADUser person = adUserService.findADUserByDn(leadUserDn);
		foToUpdate.setProgramLeadName(person.getFullName());
		foToUpdate.setProgramLeadDn(leadUserDn);
		foRepo.save(foToUpdate);
	}

	@Override
	public void setFoLeadContributor(long foId, ADUser adUser) {
		FundingOpportunity foToUpdate = foRepo.findById(foId)
				.orElseThrow(() -> new DataRetrievalFailureException("That Funding Opportunity does not exist"));
		foToUpdate.setProgramLeadDn(adUser.getDn().toString());
		foToUpdate.setProgramLeadName(adUser.getFullName());
		foRepo.save(foToUpdate);
	}

}
