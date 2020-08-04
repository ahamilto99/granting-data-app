package ca.gc.tri_agency.granting_data.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.gc.tri_agency.granting_data.model.SystemFundingCycle;
import ca.gc.tri_agency.granting_data.model.SystemFundingOpportunity;
import ca.gc.tri_agency.granting_data.model.file.FundingCycleDatasetRow;
import ca.gc.tri_agency.granting_data.model.projection.SystemFundingCycleProjection;
import ca.gc.tri_agency.granting_data.repo.SystemFundingCycleRepository;
import ca.gc.tri_agency.granting_data.service.SystemFundingCycleService;

@Service
public class SystemFundingCycleServiceImpl implements SystemFundingCycleService {

	private SystemFundingCycleRepository sfcRepo;

	@Autowired
	public SystemFundingCycleServiceImpl(SystemFundingCycleRepository sfcRepo) {
		this.sfcRepo = sfcRepo;
	}

	@Override
	public SystemFundingCycle findSystemFundingCycleById(Long id) {
		return sfcRepo.findById(id).orElseThrow(() -> new DataRetrievalFailureException("That System Funding Cycle does not exist"));
	}

	@Override
	public List<SystemFundingCycle> findAllSystemFundingCycles() {
		return sfcRepo.findAll();
	}

	@Override
	public List<SystemFundingCycle> findSFCsBySFOid(Long sfoId) {
		return sfcRepo.findBySystemFundingOpportunityId(sfoId);
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

	@Transactional(readOnly = true)
	@Override
	public List<SystemFundingCycleProjection> findSystemFundingCyclesByLinkedFundingOpportunity(Long foId) {
		return sfcRepo.findForBrowseViewFO(foId);
	}

}
