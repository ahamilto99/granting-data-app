package ca.gc.tri_agency.granting_data.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Service;

import ca.gc.tri_agency.granting_data.model.FundingCycle;
import ca.gc.tri_agency.granting_data.repo.FundingCycleRepository;
import ca.gc.tri_agency.granting_data.service.FundingCycleService;

@Service
public class FundingCycleServiceImpl implements FundingCycleService {

	private FundingCycleRepository fcRepo;
	
	@Autowired
	public FundingCycleServiceImpl(FundingCycleRepository fcRepo) {
		this.fcRepo = fcRepo;
	}
	
	@Override
	public FundingCycle findFundingCycleById(Long id) {
		return fcRepo.findById(id).orElseThrow(() -> new DataRetrievalFailureException("That Funding Cycle does not exist"));
	}

	@Override
	public List<FundingCycle> findAllFundingCycles() {
		return fcRepo.findAll();
	}

}
