package ca.gc.tri_agency.granting_data.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.gc.tri_agency.granting_data.ldap.ADUser;
import ca.gc.tri_agency.granting_data.ldap.ADUserService;
import ca.gc.tri_agency.granting_data.model.FundingOpportunity;
import ca.gc.tri_agency.granting_data.repo.FundingOpportunityRepository;
import ca.gc.tri_agency.granting_data.security.annotations.AdminOnly;
import ca.gc.tri_agency.granting_data.service.FundingOpportunityService;

@Service
public class FundingOpportunityServiceImpl implements FundingOpportunityService {

	private FundingOpportunityRepository foRepo;
	
	private ADUserService aduService;

	@Autowired
	public FundingOpportunityServiceImpl(FundingOpportunityRepository foRepo, ADUserService aduService) {
		this.foRepo = foRepo;
		this.aduService = aduService;
	}

	@Override
	public FundingOpportunity findFundingOpportunityById(Long foId) {
		return foRepo.findById(foId)
				.orElseThrow(() -> new DataRetrievalFailureException("That Funding Opportunity does not exist"));
	}

	@Override
	public List<FundingOpportunity> findAllFundingOpportunities() {
		return foRepo.findAll();
	}

	@Override
	public List<FundingOpportunity> findFundingOpportunitiesByNameEn(String nameEn) {
		return foRepo.findByNameEn(nameEn);
	}

	@Override
	public List<FundingOpportunity> findFundingOpportunitiesByLeadAgencyId(Long leadAgencyId) {
		return foRepo.findByLeadAgencyId(leadAgencyId);
	}

	@Override
	public List<FundingOpportunity> findFundingOpportunitiesByBusinessUnitId(Long buId) {
		return foRepo.findByBusinessUnitId(buId);
	}

	@AdminOnly
	@Override
	public FundingOpportunity saveFundingOpportunity(FundingOpportunity fo) {
		return foRepo.save(fo);
	}

	@AdminOnly
	@Transactional
	@Override
	public void setFundingOpportunityLeadContributor(Long foId, String dn) {
		ADUser adUser = aduService.findADUserByDn(dn);
		
		FundingOpportunity fo = findFundingOpportunityById(foId);
		fo.setProgramLeadDn(dn);
		fo.setProgramLeadName(adUser.getFullName());
	}

}
