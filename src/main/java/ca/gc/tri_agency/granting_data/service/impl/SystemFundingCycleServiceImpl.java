package ca.gc.tri_agency.granting_data.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Service;

import ca.gc.tri_agency.granting_data.model.SystemFundingCycle;
import ca.gc.tri_agency.granting_data.model.SystemFundingOpportunity;
import ca.gc.tri_agency.granting_data.model.file.FundingCycleDatasetRow;
import ca.gc.tri_agency.granting_data.repo.SystemFundingCycleRepository;
import ca.gc.tri_agency.granting_data.repo.SystemFundingOpportunityRepository;
import ca.gc.tri_agency.granting_data.service.SystemFundingCycleService;

@Service
public class SystemFundingCycleServiceImpl implements SystemFundingCycleService {

	@Autowired
	private SystemFundingOpportunityRepository sfoRepo; // TODO: REFACTOR SystemFundingOpportunity

	private SystemFundingCycleRepository sfcRepo;

	@Autowired
	public SystemFundingCycleServiceImpl(SystemFundingCycleRepository sfcRepo) {
		this.sfcRepo = sfcRepo;
	}

	@Override
	public SystemFundingCycle findSystemFundingCycleById(Long id) {
		return sfcRepo.findById(id)
				.orElseThrow(() -> new DataRetrievalFailureException("That System Funding Cycle does not exist"));
	}

	@Override
	public List<SystemFundingCycle> findAllSystemFundingCycles() {
		return sfcRepo.findAll();
	}

	/*
	 * FIXME: CONCEPT OF `1 TO `MANY `ON `IS` DUE` TO `CGSM. `CHANGED `THIS FUNCTION `TO RETURN `LIST,
	 * `BUT `THIS` NEEDS` MORE` REFACTORING AS IT DOES 3 DB CALLS or more. THIS NEEDS ANALYSIS
	 */
	@Override
	public List<SystemFundingCycle> findSFCsByFOid(Long foId) {
		ArrayList<SystemFundingCycle> retval = new ArrayList<SystemFundingCycle>();
		List<SystemFundingOpportunity> sysFos = sfoRepo.findByLinkedFundingOpportunityId(foId);
		for (SystemFundingOpportunity sfo : sysFos) {
			List<SystemFundingCycle> sfoFcs = sfcRepo.findBySystemFundingOpportunityId(sfo.getId());
			retval.addAll(sfoFcs);
		}
		return retval;
	}

	@Override
	public SystemFundingCycle registerSystemFundingCycle(FundingCycleDatasetRow row, SystemFundingOpportunity targetSfo) {
		SystemFundingCycle retval = new SystemFundingCycle();
		retval.setFiscalYear(row.getCompetitionYear());
		retval.setExtId(row.getFoCycle());
		retval.setSystemFundingOpportunity(targetSfo);
		retval.setNumAppsReceived(row.getNumReceivedApps());
		retval = sfcRepo.save(retval);
		return retval;
	}

}
