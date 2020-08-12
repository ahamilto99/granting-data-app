package ca.gc.tri_agency.granting_data.service;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.security.access.AccessDeniedException;

import ca.gc.tri_agency.granting_data.model.FundingCycle;
import ca.gc.tri_agency.granting_data.model.projection.FundingCycleProjection;

public interface FundingCycleService {

	FundingCycle findFundingCycleById(Long id) throws DataRetrievalFailureException;

	List<FundingCycle> findAllFundingCycles();

	List<FundingCycle> findFundingCyclesByFundingOpportunityId(Long foId);

	List<FundingCycleProjection> findFundingCyclesByFiscalYearId(Long fyId);

//	Map<String, List<FundingCycle>> findMonthlyFundingCyclesMapByDate(long plusMinusMonth);

	List<FundingCycle> findMonthlyFundingCyclesByStartDate(int plusMinusMonth);

	List<FundingCycle> findMonthlyFundingCyclesByEndDate(int plusMinusMonth);

	List<FundingCycle> findMonthlyFundingCyclesByStartDateLOI(int plusMinusMonth);

	List<FundingCycle> findMonthlyFundingCyclesByEndDateLOI(int plusMinusMonth);

	List<FundingCycle> findMonthlyFundingCyclesByStartDateNOI(int plusMinusMonth);

	List<FundingCycle> findMonthlyFundingCyclesByEndDateNOI(int plusMinusMonth);

	FundingCycle saveFundingCycle(FundingCycle fc) throws AccessDeniedException;

	Map<Long, FundingCycle> findFundingCyclesByFundingOpportunityMap();

	List<FundingCycleProjection> findFCsForBrowseViewFO(Long foId);

	void deleteFundingCycle(Long fcId) throws AccessDeniedException;

	FundingCycleProjection findFundingCycleForConfirmDeleteFC(Long fcId) throws AccessDeniedException;

	List<FundingCycleProjection> findFundingCyclesForCalendar(long plusMinusMonth);

}
