package ca.gc.tri_agency.granting_data.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Service;

import ca.gc.tri_agency.granting_data.model.FundingOpportunity;
import ca.gc.tri_agency.granting_data.repo.FundingOpportunityRepository;
import ca.gc.tri_agency.granting_data.security.annotations.AdminOnly;
import ca.gc.tri_agency.granting_data.service.DataAccessService;

@Service
public class DataAccessServiceImpl implements DataAccessService {

	@Autowired
	private FundingOpportunityRepository foRepo;

	@Override
	public List<FundingOpportunity> getAllFundingOpportunities() {
		return foRepo.findAll();
	}

	@Override
	public FundingOpportunity getFundingOpportunity(long id) {
		return foRepo.findById(id)
				.orElseThrow(() -> new DataRetrievalFailureException("That Funding Opportunity does not exist"));
	}

	@Override
	public List<FundingOpportunity> getFoByNameEn(String nameEn) {
		return foRepo.findAllByNameEn(nameEn);
	}

	@Override
	public List<FundingOpportunity> getAgencyFundingOpportunities(long id) {
		return foRepo.findAllByLeadAgencyId(id);
	}

	@AdminOnly
	@Override
	public void createFo(FundingOpportunity fo) {
		foRepo.save(fo);

	}

}
