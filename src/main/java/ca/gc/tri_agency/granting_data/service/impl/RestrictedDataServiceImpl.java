package ca.gc.tri_agency.granting_data.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import ca.gc.tri_agency.granting_data.ldap.ADUser;
import ca.gc.tri_agency.granting_data.ldap.ADUserService;
import ca.gc.tri_agency.granting_data.model.FundingCycle;
import ca.gc.tri_agency.granting_data.model.FundingOpportunity;
import ca.gc.tri_agency.granting_data.repo.FundingCycleRepository;
import ca.gc.tri_agency.granting_data.repo.FundingOpportunityRepository;
import ca.gc.tri_agency.granting_data.security.SecurityUtils;
import ca.gc.tri_agency.granting_data.security.annotations.AdminOnly;
import ca.gc.tri_agency.granting_data.service.RestrictedDataService;

@Service
public class RestrictedDataServiceImpl implements RestrictedDataService {

	@Autowired
	private FundingOpportunityRepository foRepo;
	@Autowired
	private FundingCycleRepository fcRepo;
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

	@Override
	public FundingCycle createOrUpdateFundingCycle(FundingCycle command) {
		FundingCycle retval = null;
		if (SecurityUtils.hasRole("MDM ADMIN")
				|| command.getFundingOpportunity().getProgramLeadDn().compareTo(SecurityUtils.getLdapUserDn()) == 0) {
			retval = fcRepo.save(command);
		} else {
			throw new AccessDeniedException("un-authorized to add cycles to funding opportunity");
		}
		return retval;
	}

	@Override
	public FundingCycle updateFc(FundingCycle command, FundingCycle target) {
		target.setFiscalYear(command.getFiscalYear());
		target.setEndDate(command.getEndDate());
		target.setExpectedApplications(command.getExpectedApplications());
		target.setIsOpen(command.getIsOpen());
		target.setStartDate(command.getStartDate());
		return fcRepo.save(target);

	}

}
