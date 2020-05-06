package ca.gc.tri_agency.granting_data.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Service;

import ca.gc.tri_agency.granting_data.model.SystemFundingOpportunity;
import ca.gc.tri_agency.granting_data.repo.SystemFundingOpportunityRepository;
import ca.gc.tri_agency.granting_data.service.SystemFundingOpportunityService;

@Service
public class SystemFundingOpportunityServiceImpl implements SystemFundingOpportunityService {

	private SystemFundingOpportunityRepository sfoRepo;

	@Autowired
	public SystemFundingOpportunityServiceImpl(SystemFundingOpportunityRepository sfoRepo) {
		this.sfoRepo = sfoRepo;
	}

	@Override
	public SystemFundingOpportunity findSystemFundingOpportunityById(Long id) {
		return sfoRepo.findById(id).orElseThrow(
				() -> new DataRetrievalFailureException("That System Funding Opportunity does not exist"));
	}

	@Override
	public List<SystemFundingOpportunity> findAllSystemFundingOpportunities() {
		return sfoRepo.findAll();
	}

	@Override
	public List<SystemFundingOpportunity> findSystemFundingOpportunitiesByLinkedFOid(Long foId) {
		return sfoRepo.findByLinkedFundingOpportunityId(foId);
	}

	@Override
	public List<SystemFundingOpportunity> findSystemFundingOpportunitiesByExtId(String extId) {
		return sfoRepo.findByExtId(extId);
	}

	@Override
	public List<SystemFundingOpportunity> findSystemFundingOpportunitiesByNameEn(String nameEn) {
		return sfoRepo.findByNameEn(nameEn);
	}

}
