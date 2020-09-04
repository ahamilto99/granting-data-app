package ca.gc.tri_agency.granting_data.service;

import java.util.List;

import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.security.access.AccessDeniedException;

import ca.gc.tri_agency.granting_data.model.FundingCycle;
import ca.gc.tri_agency.granting_data.model.projection.FundingCycleProjection;

public interface FundingCycleService {

	FundingCycle findFundingCycleById(Long id) throws DataRetrievalFailureException;

	List<FundingCycle> findAllFundingCycles();

	List<FundingCycle> findFundingCyclesByFundingOpportunityId(Long foId);

	List<FundingCycleProjection> findFundingCyclesByFiscalYearId(Long fyId);

	FundingCycle saveFundingCycle(FundingCycle fc) throws AccessDeniedException;

	List<FundingCycleProjection> findFCsForBrowseViewFO(Long foId);

	void deleteFundingCycleId(Long fcId) throws AccessDeniedException;

	FundingCycleProjection findFundingCycleForConfirmDeleteFC(Long fcId) throws AccessDeniedException;

	List<FundingCycleProjection> findFundingCyclesForCalendar(long plusMinusMonth);

}
